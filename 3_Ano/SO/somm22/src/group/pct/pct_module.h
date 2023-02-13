/*
 *  \author Rafael Amorim
 */

#ifndef __SOMM22__MODULE__PCT__GROUP__
#define __SOMM22__MODULE__PCT__GROUP__

#include "somm22.h"

#include <list>
#include <map>
#include <stdio.h>

namespace somm22 {

namespace group {

namespace pct {
struct PCT_T {
    uint32_t PID;
    uint32_t arrivalTime;
    uint32_t duration;
    uint32_t addrSpaceSize;
    ProcessState state;
    uint32_t startTime;
    void *memAddr; // 64 bits
};
extern std::map<uint32_t, PCT_T> process_table;
} // end of namespace pct

} // end of namespace group

} // end of namespace somm22

#endif /* __SOMM22__MODULE__PCT__GROUP__ */
