/*
 *  \author David José Araújo Ferreira
 *  \author Marco Almeida
 */

#include "somm22.h"
#include "mem_module.h"
#include <exception.h>

namespace somm22
{
    namespace group
    {

        // ================================================================================== //

        void *memFirstFitAlloc(uint32_t pid, uint32_t size)
        {
            soProbe(404, "%s(%u, 0x%x)\n", __func__, pid, size);

            // Check that the process ID is non-zero
            if (pid == 0)
            {
                throw Exception(EINVAL, __func__);
            }

            // Iterate over the available blocks and find the first block that is large enough
            mem::SLOT *currentSlot = mem::availableBlocks;
            mem::SLOT *newSlot = nullptr;
            while (currentSlot != nullptr)
            {
                // If the current block is large enough, create a new allocated block and update the available block
                if (size <= currentSlot->size)
                {
                    newSlot = new mem::SLOT;
                    newSlot->pid = pid;
                    newSlot->size = size;
                    newSlot->start = currentSlot->start;
                    currentSlot->start = ((uint8_t *)currentSlot->start) + size;
                    currentSlot->size -= size;
                    break;
                }
                currentSlot = currentSlot->next;
            }

            // If an allocated block was created, add it to the end of the allocated blocks list
            if (newSlot != nullptr)
            {
                // If the list is empty, add the new block as the first element
                if (mem::allocatedBlocks == nullptr)
                {
                    mem::allocatedBlocks = newSlot;
                    newSlot->next = nullptr;
                    return newSlot->start;
                }
                // If list is not empty, insert by ascending order of start address
                mem::SLOT *lastAllocatedSlot = mem::allocatedBlocks;

                while (lastAllocatedSlot->next != nullptr && lastAllocatedSlot->next->start < newSlot->start)
                {
                    lastAllocatedSlot = lastAllocatedSlot->next;
                }
                newSlot->next = lastAllocatedSlot->next;
                lastAllocatedSlot->next = newSlot;
            }
            else
            {
                // If no suitable block was found, throw an exception
                throw Exception(ENOSYS, __func__);
            }

            // Return a pointer to the start of the allocated block
            return newSlot->start;
        }

        // ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
