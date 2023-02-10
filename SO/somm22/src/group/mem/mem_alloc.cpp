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

        void *memAlloc(uint32_t pid, uint32_t size)
        {
            soProbe(403, "%s(%u, 0x%x)\n", __func__, pid, size);

            require(pid > 0, "process ID must be non-zero");

            // calculate slot size
            uint32_t chunk = (uint32_t)ceil(size / mem::chunkSize) * mem::chunkSize;

            switch (mem::policy)
            {
            case FirstFit:
                return memFirstFitAlloc(pid, chunk);
            case NextFit:
                return  memNextFitAlloc(pid, chunk);
            case BestFit:
                return  memBestFitAlloc(pid, chunk);
            case WorstFit:
                return  memWorstFitAlloc(pid, chunk);
            default:
                throw Exception(ENOSYS, __func__);
            }
        }

        // ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
