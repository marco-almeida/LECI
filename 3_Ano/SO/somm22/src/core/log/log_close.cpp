/*
 *  \author ...
 */

#include "somm22.h"

namespace somm22
{

// ================================================================================== //

    namespace log
    {
        extern FILE *fout;

    } // end of namespace log

// ================================================================================== //

    void logClose()
    {
        soProbe(SOPROBE_GREEN, 602, "%s()\n", __func__);

        /* replace this comment with your code */

        fclose(log::fout);
    }

// ================================================================================== //

} // end of namespace somm22
