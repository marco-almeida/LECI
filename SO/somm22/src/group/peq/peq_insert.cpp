/*
 *  \author Rafael Amorim
 *  \author Marco Almeida
 */

#include "peq_module.h"
#include "somm22.h"
#include <deque>
#include <iterator>
namespace somm22 {

namespace group {

// ================================================================================== //

void peqInsert(EventType type, uint32_t time, uint32_t pid) {
    soProbe(304, "%s(%s, %u, %u)\n", __func__, peqEventTypeAsString(type), time, pid);

    require(pid > 0, "process ID must be non-zero");
    Event e;
    e.eventTime = time;
    e.eventType = type;
    e.pid = pid;

    if (peq::process_event_deque.empty()) {
        peq::process_event_deque.push_back(e);
        return;
    }

    std::deque<Event>::iterator it;
    for (it = peq::process_event_deque.begin(); it != peq::process_event_deque.end(); it++) {
        if (e.eventTime < it->eventTime) {
            peq::process_event_deque.insert(it, e);
            return;
        }
    }
    peq::process_event_deque.push_back(e);

}

// ================================================================================== //

} // end of namespace group

} // end of namespace somm22
