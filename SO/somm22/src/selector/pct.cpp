/*
 *  \author ...
 */

#include "somm22.h"

#include <stdint.h>

namespace somm22
{
    namespace pct 
    {
        static bool binariesEnabled = false;

    } // end of namespace somm22::pct
        
    void pctSetBinaryMode(bool state)
    {
        soProbe(SOPROBE_GREEN, 200, "%s(%s)\n", __func__, state ? "true" : "false");

        pct::binariesEnabled = state;
    }

    namespace binaries
    {
        void pctInit(const char *fname);
        void pctLog();
        void pctPrint(const char *fname, PrintMode mode);
        void pctInsert(uint32_t pid, uint32_t arrivalTime, uint32_t duration, uint32_t addrSpaceSize);
        uint32_t pctGetProcessDuration(uint32_t pid);
        uint32_t pctGetProcessAddressSpaceSize(uint32_t pid);
        void *pctGetProcessMemAddress(uint32_t pid);
        void pctSetProcessMemAddress(uint32_t pid, void *memAddr);
        void pctSetProcessState(uint32_t pid, ProcessState state);
        void pctSetProcessStartTime(uint32_t pid, uint32_t time);
        const char *pctGetStateName(uint32_t pid);
        const char *pctStateAsString(ProcessState state);

    } // end of namespace somm22::binaries

    namespace group
    {
        void pctInit(const char *fname);
        void pctLog();
        void pctPrint(const char *fname, PrintMode mode);
        void pctInsert(uint32_t pid, uint32_t arrivalTime, uint32_t duration, uint32_t addrSpaceSize);
        uint32_t pctGetProcessDuration(uint32_t pid);
        uint32_t pctGetProcessAddressSpaceSize(uint32_t pid);
        void *pctGetProcessMemAddress(uint32_t pid);
        void pctSetProcessMemAddress(uint32_t pid, void *memAddr);
        void pctSetProcessState(uint32_t pid, ProcessState state);
        void pctSetProcessStartTime(uint32_t pid, uint32_t time);
        const char *pctGetStateName(uint32_t pid);
        const char *pctStateAsString(ProcessState state);

    } // end of namespace somm22::group

    void pctInit(const char *fname)
    {
        if (pct::binariesEnabled)
            binaries::pctInit(fname);
        else
            group::pctInit(fname);
    }

    void pctLog()
    {
        if (pct::binariesEnabled)
            binaries::pctLog();
        else
            group::pctLog();
    }

    void pctPrint(const char *fname, PrintMode mode)
    {
        if (pct::binariesEnabled)
            binaries::pctPrint(fname, mode);
        else
            group::pctPrint(fname, mode);
    }

    void pctInsert(uint32_t pid, uint32_t arrivalTime, uint32_t duration, uint32_t addrSpaceSize)
    {
        if (pct::binariesEnabled)
            binaries::pctInsert(pid, arrivalTime, duration, addrSpaceSize);
        else
            group::pctInsert(pid, arrivalTime, duration, addrSpaceSize);
    }

    uint32_t pctGetProcessDuration(uint32_t pid)
    {
        if (pct::binariesEnabled)
            return binaries::pctGetProcessDuration(pid);
        else
            return group::pctGetProcessDuration(pid);
    }

    uint32_t pctGetProcessAddressSpaceSize(uint32_t pid)
    {
        if (pct::binariesEnabled)
            return binaries::pctGetProcessAddressSpaceSize(pid);
        else
            return group::pctGetProcessAddressSpaceSize(pid);
    }

    void *pctGetProcessMemAddress(uint32_t pid)
    {
        if (pct::binariesEnabled)
            return binaries::pctGetProcessMemAddress(pid);
        else
            return group::pctGetProcessMemAddress(pid);
    }

    void pctSetProcessMemAddress(uint32_t pid, void *memAddr)
    {
        if (pct::binariesEnabled)
            binaries::pctSetProcessMemAddress(pid, memAddr);
        else
            group::pctSetProcessMemAddress(pid, memAddr);
    }

    void pctSetProcessState(uint32_t pid, ProcessState state)
    {
        if (pct::binariesEnabled)
            binaries::pctSetProcessState(pid, state);
        else
            group::pctSetProcessState(pid, state);
    }

    void pctSetProcessStartTime(uint32_t pid, uint32_t time)
    {
        if (pct::binariesEnabled)
            binaries::pctSetProcessStartTime(pid, time);
        else
            group::pctSetProcessStartTime(pid, time);
    }

    const char *pctGetStateName(uint32_t pid)
    {
        if (pct::binariesEnabled)
            return binaries::pctGetStateName(pid);
        else
            return group::pctGetStateName(pid);
    }

    const char *pctStateAsString(ProcessState state)
    {
        if (pct::binariesEnabled)
            return binaries::pctStateAsString(state);
        else
            return group::pctStateAsString(state);
    }

} // end of namespace somm22
