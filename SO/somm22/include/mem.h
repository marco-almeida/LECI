/**
 * \defgroup mem Memory Management (mem)
 *
 * \details
 *   Initially, the memory is empty, meaning that no process address space is hosted there.
 *   Thus, memory can be seen as a single region with a given dimension.
 *   In order for a process to be executed, a block of memory must be assigned to it,
 *   which is used to host its address space.
 *   For that, the initial empty region is divided into two blocks, one
 *   assigned to the process and another with the remaining empty part.
 *   To more processes have their address spaces hosted in memory, the empty part is
 *   successively divided.
 *
 *   A running process eventually terminates its execution.
 *   Then, its assigned block of memory can be released, becoming available to other processes.
 *   But, the most likely, such block is not contiguous to an empty region,
 *   creating a hole in the memory.
 *   However, if the released block is contiguous to one or two holes, a merging must take place.
 *
 *   Thus, one can see the memory as a set of used blocks plus a set of available
 *   blocks, managing it with two lists:
 *   - a list of blocks allocated and assigned to processes;
 *   - a list of available (not used) blocks.
 *
 *   Both lists must be sorted in such a way to facilitate their manipulation.
 *
 *   <b>It is up to the implementer to decide how to store these lists</b>.
 *
 *   The following policies must be available for the allocation algorithm:
 *   - <b>first fit</b>, in which the first, big enough block is used;
 *   - <b>next fit</b>, similar to the previous, but starting the search in the last used block;
 *   - <b>best fit</b>, in which the smallest, big enough block is used;
 *   - <b>worst fit</b>, in which the largest block is used.
 *
 *   In any case, if bigger than the size required, the block used must be divided into two,
 *   being the lowest part assigned to the process and the remaining part kept as an empty block.
 *   In order to minimize its external fragmentation, memory is managed in chunks bigger
 *   than a single byte.
 *   This means that the amount of memory assigned to the process is the round up
 *   of the required amount to the chunk size.
 *
 *   The interface of this module is predefined, being composed of the following functions:
 *   <table>
 *   <tr> <th> \c function <th align="center"> function ID <th>role
 *   <tr> <td> \c memSetBinaryMode() <td align="center"> 400 <td> Enable/Disable the binary version of \c mem functions;
 *   <tr> <td> \c memInit() <td align="center"> 401 <td> initializes the support internal data structure;
 *   <tr> <td> \c memLog() <td align="center"> 402 <td> log the internal state of the lists, sorted in ascending order of memory address
 *   <tr> <td> \c memPrint() <td align="center"> 402 <td> prints the internal state of the lists, sorted in ascending order of memory address
 *   <tr> <td> \c memAlloc() <td align="center"> 403 <td> forwards to the appropriate allocation function, base on the active allocation policy;
 *   <tr> <td> \c memFirstFitAlloc() <td align="center"> 404 <td> allocates a block of memory of the required size, using the first fit algorithm;
 *   <tr> <td> \c memNextFitAlloc() <td align="center"> 405 <td> allocates a block of memory of the required size, using the next fit algorithm;
 *   <tr> <td> \c memBestFitAlloc() <td align="center"> 406 <td> allocates a block of memory of the required size, using the best fit algorithm;
 *   <tr> <td> \c memWorstFitAlloc() <td align="center"> 407 <td> allocates a block of memory of the required size, using the worst fit algorithm;
 *   <tr> <td> \c memFree() <td align="center"> 408 <td> frees a previously allocated block of memory;
 *   <tr> <td> \c memGetBiggestHole() <td align="center"> 409 <td> returns the size of the biggest hole;
 *   <tr> <td> \c memGetMaxAllowableSize() <td align="center"> 409 <td> returns the maximum size allowable for the size of the address space of a process;
 *   <tr> <td> \c memAllocationPolicyAsString() <td align="center"> 490 <td> returns the allocation policy as a string. given the policy
 *   </table>
 *
 *   The following table presents an estimation of the effort required to implemented the
 *   functions of this module.
 *   Functions with similarities were put in the same source code file and
 *   the effort is defined in a <b>per file basis</b>.
 *   Effort is defined as a range of values, where 1 stands for easy and 5 for difficult.
 *   <table>
 *   <tr> <th> file(s) <th> contents <th> effort
 *   <tr> <td> \c mem.cpp, \c mem_module.h <td> module data structure <td align="center"> 2 -- 3
 *   <tr> <td> \c mem_init.cpp <td> memInit() <td align="center"> 2 -- 3
 *   <tr> <td> \c mem_print.cpp <td> memLog(), memPrint() <td align="center"> 2 -- 3
 *   <tr> <td> \c mem_alloc.cpp <td> memAlloc() <td align="center"> 1 -- 2
 *   <tr> <td> \c mem_ff_alloc.cpp <td> memFirstFitAlloc() <td align="center"> 3 -- 4
 *   <tr> <td> \c mem_nf_alloc.cpp <td> memNextFitAlloc() <td align="center"> 3 -- 4
 *   <tr> <td> \c mem_bf_alloc.cpp <td> memBestFitAlloc() <td align="center"> 3 -- 4
 *   <tr> <td> \c mem_wf_alloc.cpp <td> memWorstFitAlloc() <td align="center"> 3 -- 4
 *   <tr> <td> \c mem_free.cpp <td> memFree() <td align="center"> 3 -- 4
 *   <tr> <td> \c mem_getters.cpp <td> memGetBiggestHole(), memGetMaxAllowableSize() <td align="center"> 1 -- 2
 *   <tr> <td> \c mem_policy_as_string.cpp <td> memAllocationPolicyAsString() <td align="center"> 1 -- 2
 *   </table>
 *
 *  \author Artur Pereira - 2022
 */

#ifndef __SOMM22_MEM__
#define __SOMM22_MEM__

#include <stdint.h>

namespace somm22
{

    /** @{ */

    /**
     * \brief Allocation policy
     * \details
     */
    enum AllocationPolicy
    {
        FirstFit = 0, ///< first fit policy is used in the allocation procedure
        NextFit,      ///< next fit policy is used in the allocation procedure
        BestFit,      ///< best fit policy is used in the allocation procedure
        WorstFit      ///< worst fit policy is used in the allocation procedure
    };

    // ================================================================================== //

    /**
     * \brief Initialize the internal data structure
     * \details
     *  The module's internal data structure, defined in file \c mem.cpp,
     *  should be initialized appropriately.<br>
     *  The following must be considered:
     *  - In case of an error, an appropriate exception must be thrown.
     *  - All exceptions must be of the type defined in this project (Exception).
     *
     * \param [in] memSize Total amount of memory available
     * \param [in] memSizeOS The amount of memory used by the operating system
     * \param [in] chunkSize The unit of allocation
     * \param [in] policy The allocation policy to be used
     */
    void memInit(uint32_t memSize, uint32_t memSizeOS, uint32_t chunkSize, AllocationPolicy policy);

    // ================================================================================== //

    /**
     * \brief Log the internal state of the memory management module
     * \details
     *  The current state of the list of free and of the list of occupied blocks
     *  (module MEM) must be
     *  printed to the log stream (see logGetStream()).<br>
     *  The following must be considered:
     *  - For each list, the printing must be done in ascending order of block address.
     *  - The output must be the same as the one produced by the binary version.
     */
    void memLog();

    // ================================================================================== //

    /**
     * \brief Print the internal state of the memory management module
     * \details
     *  The current state of the list of free and of the list of occupied blocks
     *  (module MEM) must be
     *  printed to the given file.<br>
     *  The following must be considered:
     *  - For each list, the printing must be done in ascending order of block address.
     *  - The output must be the same as the one produced by the binary version.
     *  - If print mode is NEW, print to a new file (zapping if necessary).
     *  - If print mode is APPEND, append printing to the end of the file.
     *  - In case of an error, an appropriate exception must be thrown.
     *  - All exceptions must be of the type defined in this project (Exception).
     *
     * \param [in] fname Path to file where printing must be written to
     * \param [in] mode How to print (one of somm::NEW or somm::APPEND)
     */
    void memPrint(const char *fname, PrintMode mode);

    // ================================================================================== //

    /**
     * \brief Allocate a block of memory of the given size
     * \details
     *  This function is a front end point that forwards the call
     *  to the appropriate allocation function, base on the active allocation policy.<br>
     *  The following must be considered:
     *  - The given amount of memory must be rounded up to the smallest multiple of the chunk size.
     *
     * \param [in] pid PID of the process requesting memory
     * \param [in] size Size of the block to be allocated
     */
    void *memAlloc(uint32_t pid, uint32_t size);

    // ================================================================================== //

    /**
     * \brief Allocate a block of memory of the given size, using the first fit algorithm
     * \details
     *  This function may assume that the given size was already rounded up by the
     *  front end allocation function.<br>
     *  The following must be considered:
     *  - In case of an error, an appropriate exception must be thrown.
     *  - All exceptions must be of the type defined in this project (Exception).
     *
     * \param [in] pid PID of the process requesting memory
     * \param [in] size Size of the block to be allocated
     */
    void *memFirstFitAlloc(uint32_t pid, uint32_t size);

    // ================================================================================== //

    /**
     * \brief Allocate a block of memory of the given size, using the next fit algorithm
     * \details
     *  This function may assume that the given size was already rounded up by the
     *  front end allocation function.<br>
     *  The following must be considered:
     *  - In case of an error, an appropriate exception must be thrown.
     *  - All exceptions must be of the type defined in this project (Exception).
     *
     * \param [in] pid PID of the process requesting memory
     * \param [in] size Size of the block to be allocated
     */
    void *memNextFitAlloc(uint32_t pid, uint32_t size);

    // ================================================================================== //

    /**
     * \brief Allocate a block of memory of the given size, using the best fit algorithm
     * \details
     *  This function may assume that the given size was already rounded up by the
     *  front end allocation function.<br>
     *  The following must be considered:
     *  - In case of an error, an appropriate exception must be thrown.
     *  - All exceptions must be of the type defined in this project (Exception).
     *
     * \param [in] pid PID of the process requesting memory
     * \param [in] size Size of the block to be allocated
     */
    void *memBestFitAlloc(uint32_t pid, uint32_t size);

    // ================================================================================== //

    /**
     * \brief Allocate a block of memory of the given size, using the worst fit algorithm
     * \details
     *  This function may assume that the given size was already rounded up by the
     *  front end allocation function.<br>
     *  The following must be considered:
     *  - In case of an error, an appropriate exception must be thrown.
     *  - All exceptions must be of the type defined in this project (Exception).
     *
     * \param [in] pid PID of the process requesting memory
     * \param [in] size Size of the block to be allocated
     */
    void *memWorstFitAlloc(uint32_t pid, uint32_t size);

    // ================================================================================== //

    /**
     * \brief Free a block of memory
     * \details
     *  If the block to be freed is contiguous to an empty block, merging must take place.<br>
     *  The following must be considered:
     *  - In case of an error, an appropriate exception must be thrown.
     *  - All exceptions must be of the type defined in this project (Exception).
     *
     * \param [in] addr Fisr address of the block to be freed
     */
    void memFree(void *addr);

    // ================================================================================== //

    /**
     * \brief Get biggest hole
     *
     * \return The size of the biggest block in the free list
     */
    uint32_t memGetBiggestHole();

    // ================================================================================== //

    /**
     * \brief Get maximum allowable size for a process address space size
     *
     * \return The maximum allowable size for a process address space size
     */
    uint32_t memGetMaxAllowableSize();

    // ================================================================================== //

    /**
     * \brief Return the allocation policy as a string
     * \details
     *  The following must be considered:
     *  - The output must be the same as the one produced by the binary version.
     *
     * \param policy The allocation policy
     * \return The allocation policy as a string
     */
    const char *memAllocationPolicyAsString(AllocationPolicy policy);

    // ================================================================================== //

    /**
     * \brief Enable/Disable the binary version of the \c mem functions
     *
     * \param [in] state New state: \c true to enable; \c false to disable
     */
    void memSetBinaryMode(bool state);

    // ================================================================================== //

    /** @} */

};

#endif /* __SOMM22_MEM__ */
