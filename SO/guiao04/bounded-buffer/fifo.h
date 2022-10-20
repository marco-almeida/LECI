/**
 *  @file 
 *
 *  @brief A simple FIFO, whose elements are pairs of integers,
 *      one representing the producer and the other the value produced
 *
 *  The following operations are defined:
 *     \li insertion of a value
 *     \li retrieval of a value.
 *
 * \author (2016-2022) Artur Pereira <artur at ua.pt>
 */

#ifndef __SO_IPC_FIFO_
#define __SO_IPC_FIFO_

#include <stdint.h>

namespace fifo
{
    /** \brief create a FIFO in shared memory, initialize it, and return its id */
    void create(void);

    /** \brief destroy the shared FIFO given id */
    void destroy(void);

    /**
     *  \brief Insertion of a value into the FIFO.
     *
     * \param id id of the producer
     * \param value value to be stored
     */
    void in(uint32_t id, uint32_t value);

    /**
     *  \brief Retrieval of a value from the FIFO.
     *
     * \param idp pointer to recipient where to store the producer id
     * \param valuep pointer to recipient where to store the value 
     */
    void out(uint32_t * idp, uint32_t  *valuep);

}
#endif /* __SO_IPC_FIFO_ */
