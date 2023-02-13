/**
 *  \mainpage SO+FSO group work, academic year 2022-2023
 *
 *  \brief Simulating a FCFS processor scheduler based on contiguous memory allocation
 *    
 *  \author Artur Pereira - 2022
 *
 *  \details
 *    The purpose of this project is to implement a simulation system that simulates the activity
 *    of an FCFS processor scheduler based on a <b>contiguous memory allocation</b> approach.
 *    It is assumed that the system contains an infinity number of processors 
 *    and finite amount of memory to host the processors' address spaces.
 *    Thus, any process can be put running when it arrives to the system as long as
 *    enough memory to host its address space is available.
 *    Every process is considered to be composed of a single CPU burst, 
 *    whose arrival time and duration are known in advance.
 *
 *    The system is composed of 6 modules:
 *    - \c pct, which holds information about the processes being simulated
 *    - \c mem, which deals with the allocation / deallocation of memory used by processes
 *    - \c peq, which deals with the events that command the simulation
 *    - \c sim, which provides means to make and control the simulation
 *    - \c log, which provides a logging mechanism
 *    - \c probing, which provides a probing mechanism
 * 
 * The simulation is driven by an input file which defines the arrival time of a list
 * of processes, along with their time of execution and memory needs for their 
 * address spaces.
 * The simulation must show how the scheduling takes place and where in memory were the
 * address spaces stored.
 *
 * Only modules \c pct, \c peq, \c mem, and \c sim are to be developed in this assignment.
 */

#ifndef __SOMM22__
#define __SOMM22__

/** \defgroup somm22 somm22 Project
 *  \brief 
 *    Umbrella for all somm22 modules
 *
 * \defgroup pct Process Control Table (pct)
 * \ingroup somm22
 * \brief 
 *   The <b>Process Control Table (pct)</b> module holds information about
 *   the processes being simulated.
 *
 * \defgroup peq Process Event Queue (peq)
 * \ingroup somm22
 * \brief 
 *   The <b>Process Event Queue (peq)</b> module deals with the events that 
 *   command the simulation.
 *
 * \defgroup mem Memory Management (mem)
 * \ingroup somm22
 * \brief
 *   The <b>Memory Management (\c mem)</b> module deals with the allocation / deallocation
 *   of memory used by processes.
 *
 * \defgroup sim Scheduling simulation
 * \ingroup somm22
 * \brief 
 *   The <b>Simulation</b> module provides means to run the simulation
 *
 * \defgroup log Logging 
 * \ingroup somm22
 * \brief
 *   The <b>Logging</b> module provides means to log information
 *
 * \defgroup exception Exception 
 * \ingroup somm22
 * \brief 
 *   The <b>Exception</b> module provides a way to avoid defensive programming
 *   in the other components of the whole somm22 system.
 *
 * \defgroup probing Probing
 * \ingroup somm22
 * \brief
 *   The <b>Probing toolkit</b> module provides a way to print probing
 *   messages that can be turned on/off
 *
 */

#include "exception.h"
#include "probing.h"

namespace somm22
{

    /** @{ */

    /**
     * \brief Print mode
     * \details
     *   - If print mode is peq::NEW, print to a new file (zapping if necessary)
     *   - If print mode is peq::APPEND, append printing to the end of the file
     */
    enum PrintMode { 
        NEW,    ///< a new file is to be created
        APPEND  ///< printing should be appended to the file
    };

    /** 
     * \brief Set/Reset binary mode for all modules
     * \details
     *   Every module has a method to set/reset the binary mode.
     *   This is a global method to apply the same mode to all modules.
     * \param [in] state \c true to set and \c false to reset binary mode
     */
    void setBinaryMode(bool state);

    /** @} */

};

#include "dbc.h"
#include "pct.h"
#include "peq.h"
#include "mem.h"
#include "sim.h"
#include "log.h"

#endif /* __SOMM22__ */
