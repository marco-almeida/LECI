/*
 *  \author Joao Figueiredo
 *  \author Marco Almeida
 *  \author Rafael Amorim
 */

#ifndef __SOMM22__MODULE__SIM__GROUP__
#define __SOMM22__MODULE__SIM__GROUP__

#include <list>
#include <map>
#include <stdint.h>

namespace somm22 {
namespace group {
namespace sim {
/* ACTION POINT: Declare here your module's data structure as external */
struct SimModule {
    uint32_t simStep;
    uint32_t simTime;
    uint32_t simMask = 0b101; // arrival | terminate
};

extern SimModule simModule;

} // end of namespace sim

} // end of namespace group

} // end of namespace somm22

#endif /* __SOMM22__MODULE__SIM__GROUP__ */
