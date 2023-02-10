/*
 *  \author Joao Figueiredo
 *  \author Marco Almeida
 */

#include "peq_module.h"
#include "somm22.h"

namespace somm22 {

namespace group {

// ================================================================================== //

bool peqIsEmpty(uint32_t mask) {
    const char *maskStr = (mask == 0) ? "ANY" : ((mask == POSTPONED) ? "POSTPONED" : "ARRIVAL | TERMINATE");
    soProbe(303, "%s(%s)\n", __func__, maskStr);

    if (mask == 0b000) { // if mask is ANY
        return peq::process_event_deque.empty();
    }

    for (Event e : peq::process_event_deque) {
        if ((e.eventType & mask) > 0b000) {
            return false;
        }
    }
    return true;
}

// ================================================================================== //

} // end of namespace group

} // end of namespace somm22
