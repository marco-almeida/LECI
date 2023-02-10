/*
 *  \author Marco Almeida
 *  \author Rafael Amorim
 *  \author João Figueiredo
 */

#include "sim_module.h"
#include "somm22.h"
#include <queue>

namespace somm22 {

namespace group {
// ================================================================================== //

uint32_t simGetCurrentSimStep() {
    soProbe(504, "%s()\n", __func__);

    return sim::simModule.simStep;
}

// ================================================================================== //

uint32_t simGetCurrentSimTime() {
    soProbe(504, "%s()\n", __func__);

    return sim::simModule.simTime;
}

// ================================================================================== //

uint32_t simGetCurrentSimMask() {
    soProbe(504, "%s()\n", __func__);
    
    return sim::simModule.simMask;
}

// ================================================================================== //

} // end of namespace group

} // end of namespace somm22
