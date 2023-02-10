/*
 *  \author Marco Almeida
 */

#include "peq_module.h"
#include "somm22.h"
#include <algorithm>
#include <iostream>
namespace somm22 {

namespace group {

// ================================================================================== //

Event peqFetchNext(uint32_t mask) {
    const char *maskStr = (mask == 0) ? "ANY" : ((mask == POSTPONED) ? "POSTPONED" : "ARRIVAL | TERMINATE");
    soProbe(305, "%s(%s)\n", __func__, maskStr);

    // get event to remove with peeknext, then remove this event from the deque
    Event e = peqPeekNext(mask);
    // iterate deque, find event, and erase it
    std::deque<Event>::iterator it;
    for (it = peq::process_event_deque.begin(); it != peq::process_event_deque.end(); it++) {
        if (e.eventTime == it->eventTime && e.eventType == it->eventType && e.pid == it->pid) {
            peq::process_event_deque.erase(it);
            return e;
        }
    }
    // not supposed to reach here
    throw Exception(EINVAL, __func__);
}

// ================================================================================== //

Event peqPeekNext(uint32_t mask) {
    const char *maskStr = (mask == 0) ? "ANY" : ((mask == POSTPONED) ? "POSTPONED" : "ARRIVAL | TERMINATE");
    soProbe(305, "%s(%s)\n", __func__, maskStr);

    if (peqIsEmpty(mask)) {
        throw Exception(EINVAL, __func__);
    }

    if (mask == 0b000 || mask == 0b111) { // if mask is ANY
        return peq::process_event_deque.front();
    }

    for (Event e : peq::process_event_deque) {
        if ((e.eventType & mask) > 0b000) {
            return e;
        }
    }
    // not supposed to reach here
    throw Exception(EINVAL, __func__);
}

// ================================================================================== //

} // end of namespace group

} // end of namespace somm22
