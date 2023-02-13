/*
 *  \author David José Araújo Ferreira
 *  \author Marco Almeida
 */

#include "mem_module.h"
#include "somm22.h"

namespace somm22
{

    namespace group
    {

        // ================================================================================== //

        const char *memAllocationPolicyAsString(AllocationPolicy policy)
        {
            soProbe(490, "%s(\"%u\")\n", __func__, policy);

            switch (policy)
            {
            case FirstFit:
                return "FirstFit";
            case NextFit:
                return "NextFit";
            case BestFit:
                return "BestFit";
            case WorstFit:
                return "WorstFit";
            default:
                throw Exception(ESRCH, __func__);
            }
        }

        // ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
