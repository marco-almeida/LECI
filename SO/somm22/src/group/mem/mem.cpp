/*
 *  \author David José Araújo Ferreira
 *  \author Marco Almeida
 */

#include "somm22.h"
#include "mem_module.h"

namespace somm22
{
    namespace group
    {
        namespace mem
        {
            /* ACTION POINT: Declare here your module's internal data structure */
            AllocationPolicy policy;
            uint32_t chunkSize;
            uint32_t memSize;
            uint32_t osSize;
            SLOT *availableBlocks;
            SLOT *allocatedBlocks;
        } // end of namespace mem

    } // end of namespace group

} // end of namespace somm22
