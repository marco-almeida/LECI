/*
 *  \author ...
 */

#include "somm22.h"

#include <stdio.h>

namespace somm22
{

// ================================================================================== //

    namespace log
    {
        extern FILE *fout;

    } // end of namespace log

// ================================================================================== //

    void logOpen(FILE *fout)
    {
        soProbe(SOPROBE_GREEN, 601, "%s(...)\n", __func__);

        if (fileno(fout) == -1)
            throw Exception(errno, __func__);
        log::fout = fout;
    }

// ================================================================================== //

    void logOpen(const char *fname)
    {
        soProbe(SOPROBE_GREEN, 601, "%s(\"%s\")\n", __func__, fname);

        /* replace this comment with your code */

        /* open the log file */
        FILE *fout = fopen(fname, "w");
        if (fout == NULL)
            throw Exception(errno, __func__);
        log::fout = fout;
    }

// ================================================================================== //

} // end of namespace somm22
