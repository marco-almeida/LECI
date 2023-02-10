/*
 *  \author ...
 *
 *  \ACP Para login é preferível ter o nome dos tipos em vez de um número (ou ambos)
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

    void logEvent(uint32_t pid, uint32_t time, EventType type)
    {
        soProbe(SOPROBE_GREEN, 603, "%s(%u, %u, %u)\n", __func__, pid, time, peqEventTypeAsString(type));

        fprintf(log::fout, "Event: (%s, %u, %u)\n", peqEventTypeAsString(type), time, pid);
    }

// ================================================================================== //

} // end of namespace somm22
