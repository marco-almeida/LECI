/*
 *  \author David José Araújo Ferreira
 *  \author Marco Almeida
 */

#include "somm22.h"
#include "mem_module.h"
#include <iostream>

#include <fstream>
#include <iostream>
#include <string>
#include <sstream>

using namespace std;

namespace somm22
{

    namespace group
    {
        void mem_auxiliar(FILE *file)
        {
            fprintf(file, "+==============================+\n|  Memory Management busy list |\n+-------+-----------+----------+\n|  PID  |   start   |   size   |\n+-------+-----------+----------+\n");
            if (mem::allocatedBlocks != nullptr)
            {
                mem::SLOT *currentAllocatedSlot = mem::allocatedBlocks;
                while (currentAllocatedSlot != nullptr)
                {
                    fprintf(file, "|%6u |%10p |%#9x |\n", currentAllocatedSlot->pid, currentAllocatedSlot->start, currentAllocatedSlot->size);
                    currentAllocatedSlot = currentAllocatedSlot->next;
                }
            }
            fprintf(file, "+==============================+\n\n");

            fprintf(file, "+==============================+\n|  Memory Management free list |\n+-------+-----------+----------+\n|  PID  |   start   |   size   |\n+-------+-----------+----------+\n");
            mem::SLOT *currentAvailableSlot = mem::availableBlocks;
            while (currentAvailableSlot != nullptr)
            {
                if (currentAvailableSlot->size != 0) {
                    fprintf(file, "|  ---  |%10p |%#9x |\n", currentAvailableSlot->start, currentAvailableSlot->size);
                }
                currentAvailableSlot = currentAvailableSlot->next;
            }
            fprintf(file, "+==============================+\n\n");
            // =====
        }

        // ================================================================================== //

        void memLog()
        {
            soProbe(402, "%s()\n", __func__);
            FILE *file = logGetStream();
            mem_auxiliar(file);
            /* ACTION POINT: Replace next instruction with your code */
            // throw Exception(ENOSYS, __func__);
        }

        // ================================================================================== //

        void memPrint(const char *fname, PrintMode mode)
        {
            soProbe(402, "%s(\"%s\", %s)\n", __func__, fname, (mode == NEW) ? "NEW" : "APPEND");
            FILE *file;
            const char *m = mode == NEW ? "w" : "a";
            file = fopen(fname, m);
            cerr << file << endl;
            mem_auxiliar(file);
            fclose(file);
            /* ACTION POINT: Replace next instruction with your code */
            // throw Exception(ENOSYS, __func__);
        }

        // ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
