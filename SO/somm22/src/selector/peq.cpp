/*
 *  \author ...
 */

#include "somm22.h"

#include <stdint.h>

namespace somm22
{
    namespace peq 
    {
        static bool binariesEnabled = false;

    } // end of namespace somm22::peq
        
// ================================================================================== //

    void peqSetBinaryMode(bool state)
    {
        soProbe(SOPROBE_GREEN, 300, "%s(%s)\n", __func__, state ? "true" : "false");

        peq::binariesEnabled = state;
    }

// ================================================================================== //

    namespace binaries
    {
        void peqInit(const char *fname);
        void peqLog();
        void peqLogEvent(Event *e, const char *msg);
        void peqPrint(const char *fname, PrintMode mode);
        bool peqIsEmpty(uint32_t mask);
        void peqInsert(EventType type, uint32_t time, uint32_t pid);
        Event peqPeekNext(uint32_t mask);
        Event peqFetchNext(uint32_t mask);
        uint32_t peqGetFirstPostponedProcess();
        const char *peqEventTypeAsString(EventType type);
        const char *peqEventMaskAsString(uint32_t mask);

    } // end of namespace somm22::binaries

// ================================================================================== //

    namespace group
    {
        void peqInit(const char *fname);
        void peqLog();
        void peqLogEvent(Event *e, const char *msg);
        void peqPrint(const char *fname, PrintMode mode);
        bool peqIsEmpty(uint32_t mask);
        void peqInsert(EventType type, uint32_t time, uint32_t pid);
        Event peqPeekNext(uint32_t mask);
        Event peqFetchNext(uint32_t mask);
        uint32_t peqGetFirstPostponedProcess();
        const char *peqEventTypeAsString(EventType type);
        const char *peqEventMaskAsString(uint32_t mask);

    } // end of namespace somm22::group

// ================================================================================== //

    void peqInit(const char *fname)
    {
        if (peq::binariesEnabled)
            binaries::peqInit(fname);
        else
            group::peqInit(fname);
    }

// ================================================================================== //

    void peqLog()
    {
        if (peq::binariesEnabled)
            binaries::peqLog();
        else
            group::peqLog();
    }

// ================================================================================== //

    void peqLogEvent(Event *e, const char *msg)
    {
        if (peq::binariesEnabled)
            binaries::peqLogEvent(e, msg);
        else
            group::peqLogEvent(e, msg);
    }

// ================================================================================== //

    void peqPrint(const char *fname, PrintMode mode)
    {
        if (peq::binariesEnabled)
            binaries::peqPrint(fname, mode);
        else
            group::peqPrint(fname, mode);
    }

// ================================================================================== //

    bool peqIsEmpty(uint32_t mask)
    {
        if (peq::binariesEnabled)
            return binaries::peqIsEmpty(mask);
        else
            return group::peqIsEmpty(mask);
    }

// ================================================================================== //

    void peqInsert(EventType type, uint32_t time, uint32_t pid)
    {
        if (peq::binariesEnabled)
            binaries::peqInsert(type, time, pid);
        else
            group::peqInsert(type, time, pid);
    }

// ================================================================================== //

    Event peqPeekNext(uint32_t mask)
    {
        if (peq::binariesEnabled)
            return binaries::peqPeekNext(mask);
        else
            return group::peqPeekNext(mask);
    }

// ================================================================================== //

    Event peqFetchNext(uint32_t mask)
    {
        if (peq::binariesEnabled)
            return binaries::peqFetchNext(mask);
        else
            return group::peqFetchNext(mask);
    }

// ================================================================================== //

    uint32_t peqGetFirstPostponedProcess()
    {
        if (peq::binariesEnabled)
            return binaries::peqGetFirstPostponedProcess();
        else
            return group::peqGetFirstPostponedProcess();
    }

// ================================================================================== //

    const char *peqEventTypeAsString(EventType type)
    {
        if (peq::binariesEnabled)
            return binaries::peqEventTypeAsString(type);
        else
            return group::peqEventTypeAsString(type);
    }

// ================================================================================== //

    const char *peqEventMaskAsString(uint32_t mask)
    {
        if (peq::binariesEnabled)
            return binaries::peqEventMaskAsString(mask);
        else
            return group::peqEventMaskAsString(mask);
    }

// ================================================================================== //

} // end of namespace somm22
