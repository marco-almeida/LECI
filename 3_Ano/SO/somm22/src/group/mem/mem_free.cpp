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

        void memFree(void *addr)
        {
            soProbe(408, "%s(addr: %p)\n", __func__, addr);

            // Check that the address is not null
            if (addr == NULL)
            {
                throw Exception(ENOSYS, __func__);
            }

            // Search for the block to be freed in the allocated blocks list
            mem::SLOT *currentAllocatedSlot = mem::allocatedBlocks;
            mem::SLOT *lastAllocatedSlot = nullptr;
            mem::SLOT *freeingSlot = nullptr;
            while (currentAllocatedSlot != nullptr)
            {
                if (((uint8_t *)currentAllocatedSlot->start) == ((uint8_t *)addr))
                {
                    freeingSlot = currentAllocatedSlot;
                    freeingSlot->pid = 0;

                    // Remove the block from the allocated blocks list
                    if (lastAllocatedSlot == nullptr)
                    {
                        mem::allocatedBlocks = currentAllocatedSlot->next;
                    }
                    else
                    {
                        lastAllocatedSlot->next = currentAllocatedSlot->next;
                    }
                    break;
                }
                lastAllocatedSlot = currentAllocatedSlot;
                currentAllocatedSlot = currentAllocatedSlot->next;
            }

            // Check that the block was found in the allocated blocks list
            if (freeingSlot == nullptr)
            {
                throw Exception(ENOSYS, __func__);
            }

            // Add the freed block to the available blocks list
            mem::SLOT *currentAvailableSlot = mem::availableBlocks;
            while (currentAvailableSlot != nullptr)
            {
                // Check if current slot has lower address and next slot has higher address or is null
                if (((uint8_t *)currentAvailableSlot->start) < ((uint8_t *)freeingSlot->start) &&
                    (currentAvailableSlot->next == nullptr ||
                     ((uint8_t *)currentAvailableSlot->next->start) > ((uint8_t *)freeingSlot->start)))
                {
                    bool isolated = true;
                    // Check if current slot and freed slot are contiguous
                    // In this case, only need to update current slot size
                    if (((uint8_t *)currentAvailableSlot->start) + currentAvailableSlot->size == ((uint8_t *)freeingSlot->start))
                    {
                        currentAvailableSlot->size += freeingSlot->size;
                        isolated = false;
                    }

                    // Check if next slot and freed slot are contiguous or null
                    if (currentAvailableSlot->next == nullptr)
                    {
                        currentAvailableSlot->next = freeingSlot;
                        freeingSlot->next = nullptr;
                        // In this case, need to update next slot start and size
                    }
                    else if (((uint8_t *)freeingSlot->start) + freeingSlot->size == ((uint8_t *)currentAvailableSlot->next->start))
                    {
                        if (!isolated)
                        {
                            currentAvailableSlot->size += currentAvailableSlot->next->size;
                            currentAvailableSlot->next = currentAvailableSlot->next->next;
                        }
                        else
                        {
                            currentAvailableSlot->next->start = freeingSlot->start;
                            currentAvailableSlot->next->size += freeingSlot->size;
                        }
                        isolated = false;
                    }

                    if (isolated)
                    {
                        freeingSlot->next = currentAvailableSlot->next;
                        currentAvailableSlot->next = freeingSlot;
                    }

                    break;
                }
                else if (currentAvailableSlot->next == nullptr)
                {
                    bool isolated = true;
                    if (((uint8_t *)freeingSlot->start) + freeingSlot->size == ((uint8_t *)currentAvailableSlot->start))
                    {
                        currentAvailableSlot->size += freeingSlot->size;
                        currentAvailableSlot->start = freeingSlot->start;
                        isolated = false;
                    }

                    if (isolated)
                    {
                        freeingSlot->next = currentAvailableSlot;
                        mem::availableBlocks = freeingSlot;
                    }

                    break;
                }
                else if ((uint8_t *)freeingSlot->start < (uint8_t *)mem::availableBlocks->start)
                {
                    bool isolated = true;
                    if (((uint8_t *)freeingSlot->start) + freeingSlot->size == ((uint8_t *)mem::availableBlocks->start))
                    {
                        mem::availableBlocks->size += freeingSlot->size;
                        mem::availableBlocks->start = freeingSlot->start;
                        isolated = false;
                    }

                    if (isolated)
                    {
                        freeingSlot->next = mem::availableBlocks;
                        mem::availableBlocks = freeingSlot;
                    }

                    break;
                }
                currentAvailableSlot = currentAvailableSlot->next;
            }
        }

        // ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
