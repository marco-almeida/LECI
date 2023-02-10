/**
 * \defgroup sim Scheduling simulation
 *
 * \details
 *   - The simulation is driven by an input file, that represents a set of processes,
 *     including their execution data.
 *   - Syntactically, an input file is a sequence of lines, 
 *     each one representing a different process and containing the following fields:
 *     - the PID of a process;
 *     - the time of its arrival to the system;
 *     - the duration of its execution;
 *     - the size in bytes of its address space;
 *   - The field separator is the semi-colon (\c ;)
 *   - Lines starting with an # are comments and to be discarded.
 *   - There can exist whitespace (spaces or tabs) at the beginning and end of any field value.
 *
 *   The interface of this module is predefined, being composed of the following functions:
 *   <table>
 *   <tr> <th> \c function <th align="center"> function ID <th> role
 *   <tr> <td> \c simSetBinaryMode() <td align="center"> 500 <td> Enable/Disable the binary version of \c sim functions;
 *   <tr> <td> \c simInit() <td align="center"> 501 <td> initializes the support internal data structure;
 *   <tr> <td> \c simCheckInputFormat() <td align="center"> 502 <td> Check the syntax of the scheduling simulation description file;
 *   <tr> <td> \c simRun() <td align="center"> 503 <td> Run a given number of simulation steps;
 *   <tr> <td> \c simGetCurrentSimTime() <td align="center"> 504 <td> Return the current simulation time;
 *   <tr> <td> \c simGetCurrentSimStep() <td align="center"> 504 <td> Return the current simulation step;
 *   <tr> <td> \c simGetCurrentSimMask() <td align="center"> 504 <td> Return the current simulation mask;
 *   </table>
 *
 *   The following table presents an estimation of the effort required to implemented the
 *   functions of this module. 
 *   Functions with similarities were put in the same source code file and
 *   the effort is defined in a <b>per file basis</b>.
 *   Effort is defined as a range of values, where 1 stands for easy and 5 for difficult.
 *   <table>
 *   <tr> <th> file(s) <th> contents <th> effort
 *   <tr> <td> \c sim.cpp, \c sim_module.h <td> module data structure <td align="center"> 2 -- 2
 *   <tr> <td> \c sim_init.cpp <td> simInit() <td align="center"> 2 -- 2
 *   <tr> <td> \c sim_run.cpp <td> simRun() <td align="center"> 4 -- 5
 *   <tr> <td> \c sim_check_syntax.cpp <td> simCheckInputFormat() <td align="center"> 3 -- 4
 *   <tr> <td> \c sim_getters.cpp <td> simGetCurrentSimStep(), simGetCurrentSimTime(), <br>simGetCurrentSimMask() <td align="center"> 1 -- 1
 *   </table>
 *
 *  \author Artur Pereira - 2022
 */

#ifndef __SOMM22_SIM__
#define __SOMM22_SIM__

namespace somm22
{
    
    /** @{ */

// ================================================================================== //

    /**
     * \brief Initializes the internal data structure
     * \details
     *  The module's internal data structure, defined in file \c sim.cpp, 
     *  should be initialized appropriately.<br>
     *  The following must be considered:
     *  - This module must keep internally the number of steps already processed.
     *  - This module must keep internally the current simulation time.
     *  - This module must keep internally an event mask to indicate the types of events to be processed next
     *  - In case of an error, an appropriate exception must be thrown
     *  - All exceptions must be of the type defined in this project (Exception)
     */
    void simInit();

// ================================================================================== //

    /**
     * \brief Check the input file both syntactically and semantically
     * \details
     *  The file should be composed of lines, which are either a comment or a process record.<br>
     *  Comments lines starts with a <b>#</b> and can have whitespace (spaces or tabs) before it.
     *  Record lines contains 4 fields, separated by semi-colons.<br>
     *  The following must be considered:
     *  - There can exist whitespace at the beginning and end of any field.
     *  - All PIDs must be different and PID number 0 must not exist.
     *  - In case of a syntax or semantic error, 
     *    an error message should be sent to standard error and the function should return \c false.
     *  - On success, the function should return \c true.
     *  - In case of any other error, an appropriate exception must be thrown.
     *  - All exceptions must be of the type defined in this project (Exception).
     *
     * \param [in] fname Path to the file whose syntax is to be checked
     * \return \c true, if file is valid syntactically; \c false, otherwise
     */
    bool simCheckInputFormat(const char *fname);

// ================================================================================== //

    /**
     * \brief Run the simulation for a given number of steps
     * \details
     *  This function is responsible for actually do the simulation, interacting with all
     *  the other modules.
     *  For each step, an event should be fetched from the process event queue and processed.
     *  The event type to be fetched depends on the simulation state. 
     *  Normally, an event of types ARRIVAL or TERMINATE should be fetched.
     *  The processing of an ARRIVAL event may result in a new TERMINATE event,
     *  if a big enough block of memory is available to put the process running,
     *  or in a POSTPONED event, if it is not available.
     *  In the processing of a TERMINATE event, memory becomes free and so
     *  a big enough block may become available to allow
     *  the first POSTPONED event to be dispatched.<br>
     *  The following must be considered:
     *  - The simulation can reach the end in less than the given number of steps.
     *  - If the given number of steps is zero, the simulation must run til the end.
     *  - The dispatch of an ARRIVAL event may result in a POSTPONED or TERMINATE event, 
     *    depending on memory availability, and the associated process must change its state
     *    to SWAPPED or RUNNING, respectively.
     *  - The dispatch of a POSTPONED event results in a TERMINATE event 
     *    and the associated process must change its state to RUNNING.
     *  - In the dispatch of a TERMINATE event, the associated process must change its state to FINISHED.
     *  - Since the processor scheduler follows an FCFS policy, only after the first postponed
     *    event is dispatched can other postponed events be dispatched.
     *  - If a process requires more memory than the total existing memory, it
     *    should be DESCARDED.
     *
     * \param [in] cnt Number of simulation steps to execute
     */
    void simRun(uint32_t cnt);

// ================================================================================== //

    /**
     * \brief Get the current simulation step
     * \details
     *  This module must keep internally the number of steps already processed.
     *
     * \return The current simulation step
     */
    uint32_t simGetCurrentSimStep();

// ================================================================================== //

    /**
     * \brief Get the current simulation time
     * \details
     *  This module must keep internally the current simulation time.
     *
     * \return The current simulation time
     */
    uint32_t simGetCurrentSimTime();

// ================================================================================== //

    /**
     * \brief Get the current simulation mask
     * \details
     *  At every step, the type of events that can be processed may change.
     *  This module must keep internally an event mask indicating the type of events
     *  must be processed next.
     *  The event mask is a bitwise-or of those possible.
     *  
     * \return The current simulation mask
     */
    uint32_t simGetCurrentSimMask();

// ================================================================================== //

    /**
     * \brief Enable/Disable the binary version of the \c sim functions
     *
     * \param [in] state New state: \c true to enable; \c false to disable
     */
    void simSetBinaryMode(bool state);

// ================================================================================== //

    /** @} */

};

#endif /* __SOMM22_SIM__ */

