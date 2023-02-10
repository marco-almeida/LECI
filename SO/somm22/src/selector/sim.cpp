/*
 *  \author ...
 */

#include "probing.h"

#include <stdint.h>

namespace somm22
{
    namespace sim 
    {
        static bool binariesEnabled = false;

    } // end of namespace somm22::sim
        
    void simSetBinaryMode(bool state)
    {
        soProbe(SOPROBE_GREEN, 500, "%s(%s)\n", __func__, state ? "true" : "false");

        sim::binariesEnabled = state;
    }

    namespace binaries
    {
        void simInit();
        bool simCheckInputFormat(const char*);
        uint32_t simGetCurrentSimStep();
        uint32_t simGetCurrentSimTime();
        uint32_t simGetCurrentSimMask();
        void simRun(uint32_t cnt);

    } // end of namespace somm22::binaries

    namespace group
    {
        void simInit();
        bool simCheckInputFormat(const char*);
        uint32_t simGetCurrentSimStep();
        uint32_t simGetCurrentSimTime();
        uint32_t simGetCurrentSimMask();
        void simRun(uint32_t cnt);

    } // end of namespace somm22::binaries

    void simInit()
    {
        if (sim::binariesEnabled)
            binaries::simInit();
        else
            group::simInit();
    }

    bool simCheckInputFormat(const char *fname)
    {
        if (sim::binariesEnabled)
            return binaries::simCheckInputFormat(fname);
        else
            return group::simCheckInputFormat(fname);
    }

    uint32_t simGetCurrentSimStep()
    {
        if (sim::binariesEnabled)
            return binaries::simGetCurrentSimStep();
        else
            return group::simGetCurrentSimStep();
    }

    uint32_t simGetCurrentSimTime()
    {
        if (sim::binariesEnabled)
            return binaries::simGetCurrentSimTime();
        else
            return group::simGetCurrentSimTime();
    }

    uint32_t simGetCurrentSimMask()
    {
        if (sim::binariesEnabled)
            return binaries::simGetCurrentSimMask();
        else
            return group::simGetCurrentSimMask();
    }

    void simRun(uint32_t cnt)
    {
        if (sim::binariesEnabled)
            binaries::simRun(cnt);
        else
            group::simRun(cnt);
    }

} // end of namespace somm22
