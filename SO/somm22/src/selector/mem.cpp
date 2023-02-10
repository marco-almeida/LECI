/*
 *  \author ...
 */

#include "somm22.h"

#include <stdint.h>

namespace somm22
{
    namespace mem 
    {
        static bool binariesEnabled = false;

    } // end of namespace somm22::mem
        
// ================================================================================== //

    void memSetBinaryMode(bool state)
    {
        soProbe(SOPROBE_GREEN, 400, "%s(%s)\n", __func__, state ? "true" : "false");

        mem::binariesEnabled = state;
    }

// ================================================================================== //

    namespace binaries
    {
        void memInit(uint32_t memSize, uint32_t memSizeOS, uint32_t chunkSize, AllocationPolicy policy);
        void memLog();
        void memPrint(const char *fname, PrintMode mode);
        void *memAlloc(uint32_t pid, uint32_t size);
        void *memFirstFitAlloc(uint32_t pid, uint32_t size);
        void *memNextFitAlloc(uint32_t pid, uint32_t size);
        void *memBestFitAlloc(uint32_t pid, uint32_t size);
        void *memWorstFitAlloc(uint32_t pid, uint32_t size);
        void memFree(void *addr);
        uint32_t memGetBiggestHole();
        uint32_t memGetMaxAllowableSize();
        const char *memAllocationPolicyAsString(AllocationPolicy policy);

    } // end of namespace somm22::binaries

// ================================================================================== //

    namespace group
    {
        void memInit(uint32_t memSize, uint32_t memSizeOS, uint32_t chunkSize, AllocationPolicy policy);
        void memLog();
        void memPrint(const char *fname, PrintMode mode);
        void *memAlloc(uint32_t pid, uint32_t size);
        void *memFirstFitAlloc(uint32_t pid, uint32_t size);
        void *memNextFitAlloc(uint32_t pid, uint32_t size);
        void *memBestFitAlloc(uint32_t pid, uint32_t size);
        void *memWorstFitAlloc(uint32_t pid, uint32_t size);
        void memFree(void *addr);
        uint32_t memGetBiggestHole();
        uint32_t memGetMaxAllowableSize();
        const char *memAllocationPolicyAsString(AllocationPolicy policy);
        
    } // end of namespace somm22::group

// ================================================================================== //

    void memInit(uint32_t memSize, uint32_t memSizeOS, uint32_t chunkSize, AllocationPolicy policy)
    {
        if (mem::binariesEnabled)
            binaries::memInit(memSize, memSizeOS, chunkSize, policy);
        else
            group::memInit(memSize, memSizeOS, chunkSize, policy);
    }

// ================================================================================== //

    void memLog()
    {
        if (mem::binariesEnabled)
            binaries::memLog();
        else
            group::memLog();
    }

// ================================================================================== //

    void memPrint(const char *fname, PrintMode mode)
    {
        if (mem::binariesEnabled)
            binaries::memPrint(fname, mode);
        else
            group::memPrint(fname, mode);
    }

// ================================================================================== //

    void *memAlloc(uint32_t pid, uint32_t size)
    {
        if (mem::binariesEnabled)
            return binaries::memAlloc(pid, size);
        else
            return group::memAlloc(pid, size);
    }

// ================================================================================== //

    void *memFirstFitAlloc(uint32_t pid, uint32_t size)
    {
        if (mem::binariesEnabled)
            return binaries::memFirstFitAlloc(pid, size);
        else
            return group::memFirstFitAlloc(pid, size);
    }

// ================================================================================== //

    void *memNextFitAlloc(uint32_t pid, uint32_t size)
    {
        if (mem::binariesEnabled)
            return binaries::memNextFitAlloc(pid, size);
        else
            return group::memNextFitAlloc(pid, size);
    }

// ================================================================================== //

    void *memBestFitAlloc(uint32_t pid, uint32_t size)
    {
        if (mem::binariesEnabled)
            return binaries::memBestFitAlloc(pid, size);
        else
            return group::memBestFitAlloc(pid, size);
    }

// ================================================================================== //

    void *memWorstFitAlloc(uint32_t pid, uint32_t size)
    {
        if (mem::binariesEnabled)
            return binaries::memWorstFitAlloc(pid, size);
        else
            return group::memWorstFitAlloc(pid, size);
    }

// ================================================================================== //

    void memFree(void *addr)
    {
        if (mem::binariesEnabled)
            binaries::memFree(addr);
        else
            group::memFree(addr);
    }

// ================================================================================== //

    uint32_t memGetBiggestHole()
    {
        if (mem::binariesEnabled)
            return binaries::memGetBiggestHole();
        else
            return group::memGetBiggestHole();
    }

// ================================================================================== //

    uint32_t memGetMaxAllowableSize()
    {
        if (mem::binariesEnabled)
            return binaries::memGetMaxAllowableSize();
        else
            return group::memGetMaxAllowableSize();
    }

// ================================================================================== //

    const char *memAllocationPolicyAsString(AllocationPolicy policy)
    {
        if (mem::binariesEnabled)
            return binaries::memAllocationPolicyAsString(policy);
        else
            return group::memAllocationPolicyAsString(policy);
    }

// ================================================================================== //

} // end of namespace somm22
