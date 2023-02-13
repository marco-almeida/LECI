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

        void *memBestFitAlloc(uint32_t pid, uint32_t size)
        {
            soProbe(406, "%s(%u, 0x%x)\n", __func__, pid, size);

            require(pid > 0, "process ID must be non-zero");

            // === Get block with smallest difference in size === //
            mem::SLOT *currentAvailableSlot = mem::availableBlocks;
            mem::SLOT *tempSlot = nullptr;
            uint32_t diff = currentAvailableSlot->size;
            while (currentAvailableSlot != nullptr)
            {
                if (currentAvailableSlot->size >= size && currentAvailableSlot->size - size <= diff)
                {
                    diff = currentAvailableSlot->size - size;
                    tempSlot = currentAvailableSlot;
                }
                currentAvailableSlot = currentAvailableSlot->next;
            }

            // If a suitable block was found, allocate memory from it
            if (tempSlot != nullptr)
            {
                mem::SLOT *newSlot = new mem::SLOT();
                newSlot->pid = pid;
                newSlot->size = size;
                newSlot->start = tempSlot->start;
                newSlot->next = nullptr;

                tempSlot->size -= size;
                tempSlot->start = ((uint8_t *)tempSlot->start) + size;

                if (tempSlot->size == 0)
                {
                    mem::SLOT *currentAvailableSlot = mem::availableBlocks;
                    if (currentAvailableSlot == tempSlot)
                        mem::availableBlocks = tempSlot->next;
                    else
                    {
                        while (currentAvailableSlot != nullptr)
                        {
                            if (currentAvailableSlot->next == tempSlot)
                            {
                                currentAvailableSlot->next = tempSlot->next;
                                break;
                            }
                            currentAvailableSlot = currentAvailableSlot->next;
                        }
                    }
                }

                mem::SLOT *currentAllocatedSlot = mem::allocatedBlocks;
                while (currentAllocatedSlot != nullptr)
                {
                    if (newSlot->start < currentAllocatedSlot->start)
                    {
                        newSlot->next = currentAllocatedSlot;
                        mem::allocatedBlocks = newSlot;
                        return newSlot->start;
                    }
                    else if (currentAllocatedSlot->next == nullptr)
                    {
                        currentAllocatedSlot->next = newSlot;
                        return newSlot->start;
                    }
                    else if (newSlot->start < currentAllocatedSlot->next->start)
                    {
                        newSlot->next = currentAllocatedSlot->next;
                        currentAllocatedSlot->next = newSlot;
                        return newSlot->start;
                    }
                    currentAllocatedSlot = currentAllocatedSlot->next;
                }
                mem::allocatedBlocks = newSlot;

                return newSlot->start;
            }
            throw Exception(ENOSYS, __func__);
        }

        // ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
