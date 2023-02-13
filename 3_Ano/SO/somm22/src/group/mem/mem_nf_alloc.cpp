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

        void *memNextFitAlloc(uint32_t pid, uint32_t size)
        {
            soProbe(405, "%s(%u, 0x%x)\n", __func__, pid, size);

            require(pid > 0, "process ID must be non-zero");

            mem::SLOT *currentAllocatedSlot = mem::allocatedBlocks;

            // If there is no allocated block, allocate the first one
            if (currentAllocatedSlot == nullptr)
            {
                mem::SLOT *newSlot = new mem::SLOT();
                newSlot->pid = pid;
                newSlot->size = size;
                newSlot->start = mem::availableBlocks->start;
                newSlot->next = nullptr;
                mem::allocatedBlocks = newSlot;

                mem::availableBlocks->size -= size;
                mem::availableBlocks->start = ((uint8_t *)mem::availableBlocks->start) + size;
                return mem::allocatedBlocks->start;
            }

            bool wrapped = false;
            mem::SLOT *currentAvailableSlot = mem::availableBlocks;
            while (currentAllocatedSlot != nullptr)
            {
                if (currentAllocatedSlot->next == nullptr)
                {
                    // Find available space at the end of the allocated memory
                    while (currentAvailableSlot != nullptr && currentAvailableSlot->start < currentAllocatedSlot->start)
                    {
                        currentAvailableSlot = currentAvailableSlot->next;
                    }

                    if (currentAvailableSlot != nullptr && currentAvailableSlot->start > currentAllocatedSlot->start && currentAvailableSlot->size >= size)
                    {
                        mem::SLOT *newSlot = new mem::SLOT();
                        newSlot->pid = pid;
                        newSlot->size = size;
                        newSlot->start = currentAvailableSlot->start;
                        newSlot->next = nullptr;
                        currentAllocatedSlot->next = newSlot;

                        currentAvailableSlot->size -= size;
                        currentAvailableSlot->start = ((uint8_t *)currentAvailableSlot->start) + size;
                        return newSlot->start;
                    }
                    else
                    {
                        wrapped = true;
                        currentAllocatedSlot = mem::allocatedBlocks;
                        currentAvailableSlot = mem::availableBlocks;
                        while (currentAvailableSlot != nullptr && currentAvailableSlot->size < size)
                        {
                            currentAvailableSlot = currentAvailableSlot->next;
                        }
                        continue;
                    }
                }
                else if (wrapped)
                {
                    mem::SLOT *newSlot = new mem::SLOT();
                    newSlot->pid = pid;
                    newSlot->size = size;
                    newSlot->start = currentAvailableSlot->start;

                    if (currentAllocatedSlot->start < currentAvailableSlot->start && currentAllocatedSlot->next->start > currentAvailableSlot->start)
                    {
                        newSlot->next = currentAllocatedSlot->next;
                        currentAllocatedSlot->next = newSlot;
                    }
                    else if (currentAvailableSlot->start < currentAllocatedSlot->start)
                    {
                        newSlot->next = currentAllocatedSlot;
                        mem::allocatedBlocks = newSlot;
                    }

                    currentAvailableSlot->size -= size;
                    currentAvailableSlot->start = ((uint8_t *)currentAvailableSlot->start) + size;
                    return newSlot->start;
                }
                currentAllocatedSlot = currentAllocatedSlot->next;
            }
            throw Exception(ENOMEM, __func__);
        }

        // ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
