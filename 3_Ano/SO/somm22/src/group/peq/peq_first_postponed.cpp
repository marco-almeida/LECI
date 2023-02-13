/*
 *  \author Marco Almeida
 */

#include "peq_module.h"
#include "somm22.h"

namespace somm22 {

namespace group {

// ================================================================================== //

uint32_t peqGetFirstPostponedProcess() {
    soProbe(306, "%s()\n", __func__);

    if (peqIsEmpty(POSTPONED)) {
        return 0;
    }

    return peqPeekNext(POSTPONED).pid;
}

// ================================================================================== //

} // end of namespace group

} // end of namespace somm22
