/*
 *  \author Rafael Amorim
 *  \author João Figueiredo
 *  \author Marco Almeida
 */

#include "peq_module.h"
#include "somm22.h"
#include <string>
namespace somm22 {

namespace group {

// ================================================================================== //

const char *peqEventTypeAsString(EventType type) {
    soProbe(397, "%s(\"0x%x\")\n", __func__, type);

    switch (type) {
    case ARRIVAL:
        return "ARRIVAL";
    case POSTPONED:
        return "POSTPONED";
    case TERMINATE:
        return "TERMINATE";
    default:
        return "ANY";
    }
}

// ================================================================================== //

const char *peqEventMaskAsString(uint32_t mask) {
    soProbe(397, "%s(\"0x%x\")\n", __func__, mask);

    require((mask | 0b111) == 0b111, "wrong event mask");

    switch (mask) {
    case 0b000:
    case 0b111:
        return "ANY";
    case 0b001:
        return "ARRIVAL";
    case 0b010:
        return "POSTPONED";
    case 0b100:
        return "TERMINATE";
    case 0b011:
        return "ARRIVAL | POSTPONED";
    case 0b101:
        return "ARRIVAL | TERMINATE";
    case 0b110:
        return "TERMINATE | POSTPONED";
    default:
        return "Error peqEventMaskAsString";
    }
}

// ================================================================================== //

} // end of namespace group

} // end of namespace somm22
