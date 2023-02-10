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

        // ================================================================================== //

        void memInit(uint32_t mSize, uint32_t osSize, uint32_t cSize, AllocationPolicy allocPolicy)
        {
            soProbe(401, "%s(0x%x, 0x%x, 0x%x, %s)\n", __func__, mSize, osSize, cSize, memAllocationPolicyAsString(allocPolicy));

            require(mSize > osSize, "memory must be bigger than the one use by OS");
            require((mSize % cSize) == 0, "memory size must be a multiple of chunck size");
            require((osSize % cSize) == 0, "memory size for OS must be a multiple of chunck size");

            mem::policy = allocPolicy;
            mem::allocatedBlocks = nullptr;
            mem::chunkSize = cSize;
            mem::memSize = mSize;
            mem::osSize = osSize;

            mem::SLOT *slot = new mem::SLOT;
            slot->pid = 0;
            slot->size = mSize - osSize;
            slot->start = (uint8_t *)0 + osSize;
            slot->next = nullptr;

            mem::availableBlocks = slot;
        }

        // ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
