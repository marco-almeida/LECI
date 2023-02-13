/*
 *  \author Marco Almeida
 */

#include "sim_module.h"
#include "somm22.h"

namespace somm22 {

namespace group {

// ================================================================================== //

void simInit() {
    soProbe(501, "%s()\n", __func__);
    
    sim::simModule.simStep = 0;
    sim::simModule.simTime = 0;
    sim::simModule.simMask = ARRIVAL | TERMINATE;
}

// ================================================================================== //

} // end of namespace group

} // end of namespace somm22
