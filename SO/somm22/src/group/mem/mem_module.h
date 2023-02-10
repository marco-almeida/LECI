/*
 *  \author David José Araújo Ferreira
 *  \author Marco Almeida
 */

#ifndef __SOMM22__MODULE__MEM__GROUP__
#define __SOMM22__MODULE__MEM__GROUP__

#include "somm22.h"

namespace somm22
{

    namespace group
    {

        namespace mem
        {
            // Block of memory
            struct SLOT
            {
                uint32_t pid;
                uint32_t size;
                void *start;
                SLOT *next;
            };

            extern uint32_t memSize;
            extern uint32_t osSize;
            extern AllocationPolicy policy;
            extern uint32_t chunkSize;
            extern SLOT *availableBlocks;
            extern SLOT *allocatedBlocks;

        } // end of namespace mem

    } // end of namespace group

} // end of namespace somm22

#endif /* __SOMM22__MODULE__MEM__GROUP__ */
