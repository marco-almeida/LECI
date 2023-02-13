/*
 *  \author Rafael Amorim
 *  \author Marco Almeida
 */

#include "pct_module.h"
#include "somm22.h"

namespace somm22 {

namespace group {

// ================================================================================== //

void pctInsert(uint32_t pid, uint32_t arrivalTime, uint32_t duration, uint32_t addrSpaceSize) {
    soProbe(203, "%s(%d, %u, %u, 0x%x)\n", __func__, pid, arrivalTime, duration, addrSpaceSize);
    pct::PCT_T pct; 
    // check if pid key already exists
    if (pct::process_table.find(pid) != pct::process_table.end()) {
        throw Exception(EINVAL, __func__);
    }

    pct.PID = pid;
    pct.arrivalTime = arrivalTime;
    pct.duration = duration;
    pct.addrSpaceSize = addrSpaceSize;
    pct.state = TO_COME;
    pct.startTime = UINT32_MAX;
    pct.memAddr = NULL;

    pct::process_table.insert(std::pair<uint32_t, pct::PCT_T>(pid, pct));
}

// ================================================================================== //

} // end of namespace group

} // end of namespace somm22
