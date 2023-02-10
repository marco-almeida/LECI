/**
 * \defgroup peq Process Event Queue (peq)
 *
 * \details 
 *   During a simulation, different type of events can coexist in the queue:
 *   - the arrival of a process;
 *   - the termination of execution of a process that is running;
 *   - an arrival event is postponed when there is no enough available memory to the process to run.
 *
 *   The Process Event Queue (\c peq) module is responsible for storing these events and
 *   allowing them to be accessed in specific order.
 *
 *   <b>It is up to the implementer to decide which data structure to use, 
 *   as long as, at least, the following information is present</b>:
 *   - the time at which the event will occur or was postponed;
 *   - the type of event, being one of arrival (\c ARRIVAL),
 *      postponed (\c POSTPONED) or termination (\c TERMINATE);
 *   - the identification of the associated process (\c PID).
 *
 *   The interface of this module is predefined, being composed of the following functions:
 *   <table>
 *   <tr> <th> \c function <th align="center"> function ID <th>role
 *   <tr> <td> \c peqSetBinaryMode() <td align="center"> 300 <td> Enable/Disable the binary version of \c peq functions;
 *   <tr> <td> \c peqInit() <td align="center"> 301 <td> initializes the support internal data structure;
 *   <tr> <td> \c peqLog() <td align="center"> 302 <td> logs the internal state of the process event queue.
 *   <tr> <td> \c peqLogEvent() <td align="center"> 302 <td> logs the given event.
 *   <tr> <td> \c peqPrint() <td align="center"> 302 <td> prints the internal state of the process event queue.
 *   <tr> <td> \c peqIsEmpty() <td align="center"> 303 <td> indicates if the queue is empty;
 *   <tr> <td> \c peqInsert() <td align="center"> 304 <td> inserts a new event in the queue;
 *   <tr> <td> \c peqPeekNext() <td align="center"> 305 <td> Peeks first event covered by mask;
 *   <tr> <td> \c peqFetchNext() <td align="center"> 305 <td> Fetches first event covered by mask ;
 *   <tr> <td> \c peqGetFirstPostponedProcess() <td align="center"> 306 <td> returns the pid of the first postponed process;
 *   <tr> <td> \c peqEventTypeAsString() <td align="center"> 397 <td> returns the given event type as a string
 *   <tr> <td> \c peqEventMaskAsString() <td align="center"> 397 <td> returns the given event mask as a string
 *   </table>
 *
 *   The following table presents an estimation of the effort required to implemented the
 *   functions of this module. 
 *   Functions with similarities were put in the same source code file and
 *   the effort is defined in a <b>per file basis</b>.
 *   Effort is defined as a range of values, where 1 stands for easy and 5 for difficult.
 *   <table>
 *   <tr> <th> file(s) <th> contents <th> effort
 *   <tr> <td> \c peq.cpp, \c peq_module.h <td> module data structure <td align="center"> 2 -- 2
 *   <tr> <td> \c peq_init.cpp <td> peqInit() <td align="center"> 3 -- 3
 *   <tr> <td> \c peq_insert.cpp <td> peqInsert() <td align="center"> 2 -- 3
 *   <tr> <td> \c peq_print.cpp <td> peqLog(), peqPrint(), peqLogEvent() <td align="center"> 2 -- 3
 *   <tr> <td> \c peq_fetch.cpp <td> peqPeekNext(), peqFetchNext() <td align="center"> 3 -- 4
 *   <tr> <td> \c peq_is_empty.cpp <td> peqIsEmpty() <td align="center"> 2 -- 2
 *   <tr> <td> \c peq_first_postponed.cpp <td> peqGetFirstPostponedProcess() <td align="center"> 2 -- 2
 *   <tr> <td> \c peq_type_as_string.cpp <td> peqEventTypeAsString(), peqEventMaskAsString() <td align="center"> 1 -- 2
 *   </table>
 *
 *  \author Artur Pereira - 2022
 */

#ifndef __SOMM22_PEQ__
#define __SOMM22_PEQ__

namespace somm22
{

    /** @{ */

// ================================================================================== //

    /**
     * \brief Type of events
     * \ingroup peq
     * \details
     *   - Type ANY is used in the peek and pop operations to specify any type
     */
    enum EventType { 
        ARRIVAL = 0b001,   ///< The arrival of a process to the system
        POSTPONED = 0b010, ///< Due to lack of memory the event was postponed
        TERMINATE = 0b100  ///< A running process terminates its execution
    };

// ================================================================================== //

    /**
     * \brief Process Event Queue entry
     * \details
     *   - The support register to implement the queue
     */
    struct Event 
    {
        uint32_t eventTime; ///< Time at which event will occur
        EventType eventType; ///< Type of the event
        uint32_t pid; ///< Identification of the process
    };

// ================================================================================== //

    /**
     * \brief Initializes the internal data structure
     * \details
     *  The module's internal data structure, defined in file \c peq.cpp, 
     *  should be initialized appropriately.<br>
     *  The following must be considered:
     *   - It can be assumed that the input file is syntactically and semantically correct
     *
     * \param [in] fname Path to the file containing the simulation data
     */
    void peqInit(const char *fname);

// ================================================================================== //

    /**
     * \brief Inserts an entry in queue
     * \details
     *  A new entry should be created and added to the process event queue.<br>
     *  The following must be considered:
     *  - If an anomalous situation occurs, an appropriate exception must be thrown.
     *  - All exceptions must be of the type defined in this project (Exception).
     *  
     * \param [in] type The type of event to be inserted (one of ARRIVAL, POSTPONED, or TERMINATE)
     * \param [in] time Time at which event will occur
     * \param [in] pid Id of the process associated to the event
     */
    void peqInsert(EventType type, uint32_t time, uint32_t pid);

// ================================================================================== //

    /**
     * \brief Log the internal state of the process event queue
     * \details
     *  The current state of the process event queue (PEQ) must be
     *  printed to the log stream (see logGetStream()).<br>
     *  The following must be considered:
     *  - The printing must be done in ascending order of event time and insertion order.
     *  - The output must be the same as the one produced by the binary version.
     */
    void peqLog();

// ================================================================================== //

    /**
     * \brief Prints the internal state of the process event queue to the given file
     * \details
     *  The current state of the process event queue (PEQ) must be
     *  printed to the given file.<br>
     *  The following must be considered:
     *  - The printing must be done in ascending order of event time and insertion order.
     *  - The output must be the same as the one produced by the binary version.
     *  - If print mode is NEW, print to a new file (zapping if necessary).
     *  - If print mode is APPEND, append printing to the end of the file.
     *  - In case of an error, an appropriate exception must be thrown.
     *  - All exceptions must be of the type defined in this project (Exception).
     *
     * \param [in] fname Path to file where printing must be written to
     * \param [in] mode How to print (one of somm22::NEW or somm22::APPEND)
     */
    void peqPrint(const char *fname, PrintMode mode);

// ================================================================================== //

    /**
     * \brief Log the given event
     * \details
     *  The given event must be printed to the log stream (see logGetStream() and logPrint()).<br>
     *  The following must be considered:
     *  - The output must be the same as the one produced by the binary version.
     *
     * \param [in] e Pointer to the event object to log
     * \param [in] msg Message to print before the event data
     */
    void peqLogEvent(Event *e, const char *msg = "Event");

// ================================================================================== //

    /**
     * \brief Indicates the emptiness of the queue for events of the given types
     * \details 
     *  The function returns \c true if no event of the types covered by the given mask exist;
     *  otherwise it returns \c false.<br>
     *  The following must be considered:
     *  - If the given mask is 0, returns \c true if the whole queue is empty.
     *   
     * \param [in] mask Bitwise-or of the types to be considered
     */
    bool peqIsEmpty(uint32_t mask = 0);

// ================================================================================== //

    /**
     * \brief Peek the first event covered by given mask
     * \details
     *  The event to be selected is the one with earliest event time, covered by the given event mask.
     *  In case two or more events match the aforementioned condition, the one earliest inserted into
     *  the queue is the one selected.<br>
     *  The following must be considered:
     *  - The event <b>must not be removed</b> from the queue.
     *  - The \c EINVAL exception should be thrown, if no event exists.
     *  - All exceptions must be of the type defined in this project (Exception).
     *
     * \param [in] mask Bitwise-or of the types to be considered
     * \return The first entry covered by mask
     */
    Event peqPeekNext(uint32_t mask);

// ================================================================================== //

    /**
     * \brief Fetch the first event covered by given mask
     * \details
     *  The event to be selected is the one with earliest event time, covered by the given event mask.
     *  In case two or more events match the aforementioned condition, the one earliest inserted into
     *  the queue is the one selected.<br>
     *  The following must be considered:
     *  - The event <b>must be removed</b> from the queue.
     *  - The \c EINVAL exception should be thrown, if no event exists.
     *  - All exceptions must be of the type defined in this project (Exception).
     *
     * \param [in] mask Bitwise-or of the types to be considered
     * \return The first entry covered by mask
     */
    Event peqFetchNext(uint32_t mask);

// ================================================================================== //

    /**
     * \brief Get PID of first postponed process 
     * \details
     *  The event to be selected is the one with earliest event time of type \c POSTPONED.
     *  In case two or more events match the aforementioned condition, the one earliest inserted into
     *  the queue is the one selected.<br>
     *  The following must be considered:
     *  - If no such entry exist, 0 must be returned.
     *
     * \return The PID of the first postponed process or zero if no postponed process exist
     */
    uint32_t peqGetFirstPostponedProcess();

// ================================================================================== //

    /**
     * \brief Return the given event type as a string
     * \details
     *  The following must be considered:
     *  - The output must be the same as the one produced by the binary version.
     *
     * \param type The event type
     * \return The event type as a string
     */
    const char *peqEventTypeAsString(EventType type);

// ================================================================================== //

    /**
     * \brief Return the given event mask as a string
     * \details
     *  The following must be considered:
     *  - The output must be the same as the one produced by the binary version.
     *
     * \param mask The event mask
     * \return The event mask as a string
     */
    const char *peqEventMaskAsString(uint32_t mask);

// ================================================================================== //

    /**
     * \brief Enable/Disable the binary version of the \c peq functions
     *
     * \param [in] state New state: \c true to enable; \c false to disable
     */
    void peqSetBinaryMode(bool state);

// ================================================================================== //

    /** @} */

};


#endif /* __SOMM22_PEQ__ */

