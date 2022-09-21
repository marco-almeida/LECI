/**
 *  @file 
 *
 *  @brief A unidirectional linked-list, whose elements are registers composed of
 *      a 32-bit unsigned integer, representing a student number,
 *      and a string, used to store the student name
 *
 *  The following operations are defined:
 *     \li insertion of a register
 *     \li query of a name by number
 *     \li removal of a register
 *     \li reset of the list
 *     \li print the list contents sorted by number or name
 *     \li load list from file
 *
 * \author (2022) Artur Pereira <artur at ua.pt>
 */

#ifndef __SO_IPC_ULL_
#define __SO_IPC_ULL_

#include <stdint.h>

namespace ull 
{
    /**
     * \brief Delete all registers in the list and initialize it 
     */
    void reset();

    /**
     * \brief Fill list with contents of given file
     * \details
     *   The file is just a two-columns table, using a semi-colon as separator,
     *   constaining a name and a number.
     *   The name can be composed of several words.
     * \param fname Path to the file to read from
     */
    void load(const char *fname);

    /**
     * \brief Print the list's registers in ascending order of the \c nmec field
     */
    void print();

    /**
     *  \brief Insert a new register in the list
     *  \details
     *    - The list should be kept in ascending order of the \c nmec field
     *    - A dynamic duplicate of the name should be done
     *  \param [in] nmec The student's number
     *  \param [in] name The student's name
     */
    void insert(uint32_t nmec, const char *name);

    /**
     *  \brief Get the name of a student given its nmec
     *  \details
     *    The list must be traversed in order to find the given nmec
     *    and the corresponding name should be returned
     *  \param nmec The nmec to be searched
     *  \return the corresponding name
     */
    const char *query(uint32_t nmec);

    /**
     * \brief Remove a register from the list
     *  \details
     *    The list must be traversed in order to find the register to be removed
     *  \param nmec The nmec associated to the register to be removed
     */
    void remove(uint32_t nmec);
}

#endif /* __SO_IPC_ULL_ */
