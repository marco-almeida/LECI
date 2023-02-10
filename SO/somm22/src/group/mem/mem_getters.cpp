/*
 *  \author David José Araújo Ferreira
 *  \author Marco Almeida
 */

#include "somm22.h"
#include "mem_module.h"
#include <cmath>

namespace somm22
{

    namespace group
    {

        // ================================================================================== //

        uint32_t memGetBiggestHole()
        {
            soProbe(409, "%s()\n", __func__);

            mem::SLOT *currentAvailableSlot = mem::availableBlocks;
            uint32_t biggestSize = 0;

            while (currentAvailableSlot != nullptr)
            {
                if (currentAvailableSlot->size > biggestSize)
                {
                    biggestSize = currentAvailableSlot->size;
                }
                currentAvailableSlot = currentAvailableSlot->next;
            }

            return biggestSize;
        }

        // ================================================================================== //

        uint32_t memGetMaxAllowableSize()
        {
            soProbe(409, "%s()\n", __func__);

            return mem::memSize - mem::osSize;
        }

        // ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
