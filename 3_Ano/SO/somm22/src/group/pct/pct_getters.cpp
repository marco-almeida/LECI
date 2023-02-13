/*
 *  \author Rafael Amorim
 */

#include "pct_module.h"
#include "somm22.h"

namespace somm22 {

namespace group {

// ================================================================================== //

uint32_t pctGetProcessDuration(uint32_t pid) {
    soProbe(204, "%s(%d)\n", __func__, pid);

    require(pid > 0, "process ID must be non-zero");
    
    if (pct::process_table.find(pid) == pct::process_table.end())
        throw Exception(EINVAL, __func__);

    return pct::process_table[pid].duration;
}

// ================================================================================== //

uint32_t pctGetProcessAddressSpaceSize(uint32_t pid) {
    soProbe(205, "%s(%d)\n", __func__, pid);

    require(pid > 0, "process ID must be non-zero");

    if (pct::process_table.find(pid) == pct::process_table.end())
        throw Exception(EINVAL, __func__);

    return pct::process_table[pid].addrSpaceSize;
}

// ================================================================================== //

void *pctGetProcessMemAddress(uint32_t pid) {
    soProbe(206, "%s(%d)\n", __func__, pid);

    require(pid > 0, "process ID must be non-zero");

    if (pct::process_table.find(pid) == pct::process_table.end())
        throw Exception(EINVAL, __func__);

    return pct::process_table[pid].memAddr;
}

// ================================================================================== //

const char *pctGetStateName(uint32_t pid) {
    soProbe(210, "%s(\"%u\")\n", __func__, pid);

    if (pct::process_table.find(pid) == pct::process_table.end())
        throw Exception(EINVAL, __func__);

    return pctStateAsString(pct::process_table[pid].state);
}

// ================================================================================== //

} // end of namespace group

} // end of namespace somm22
