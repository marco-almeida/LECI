/*
 *  \author ...
 */

#include "somm22.h"

#include <stdint.h>

namespace somm22
{

// ================================================================================== //

    namespace log
    {
        /* log stream, by default stdout */
        FILE *fout = stdout;

    } // end of namespace log

// ================================================================================== //

    void logPrint(const char *fmt, ...)
    {
        soProbe(SOPROBE_GREEN, 690, "%s(\"%s\", ...)\n", __func__, fmt);

        /* print the message */
        //fprintf(log::fout, "\e[01;34m(%03u)\e[0m ", id);
        va_list ap;
        va_start(ap, fmt);
        vfprintf(log::fout, fmt, ap);
        va_end(ap);
    }

// ================================================================================== //

    FILE *logGetStream()
    {
        return log::fout;
    }

// ================================================================================== //

} // end of namespace somm22

