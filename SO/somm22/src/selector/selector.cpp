/*
 *  \author ...
 */

#include "somm22.h"

namespace somm22
{
    void setBinaryMode(bool state)
    {
        simSetBinaryMode(state);
        pctSetBinaryMode(state);
        peqSetBinaryMode(state);
        memSetBinaryMode(state);
    }

} // end of namespace somm22
