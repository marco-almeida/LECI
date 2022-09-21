/*
 *
 * \author (2016) Artur Pereira <artur at ua.pt>
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <stdint.h>

#include "ull.h"

namespace ull
{
    /* ************************************************* */

    /* The information support data structure  */
    struct Register
    {
        uint32_t nmec;       //!< student number
        const char *name;    //!< student name
    };

    /* The linked-list support data structure */
    struct Node 
    {
        Register reg;
        struct Node *next;
    };

    static Node *head = NULL;

    /* ************************************************* */

    void reset()
    {
    }

    /* ************************************************* */

    void load(const char *fname)
    {
    }

    /* ************************************************* */

    void print()
    {
    }

    /* ************************************************* */

    void insert(uint32_t nmec, const char *name)
    {
    }

    /* ************************************************* */

    const char *query(uint32_t nmec)
    {
        return NULL;
    }

    /* ************************************************* */

    void remove(uint32_t nmec)
    {
    }

    /* ************************************************* */

}
