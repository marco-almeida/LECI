/*
 *  \author Marco Almeida
 */

#include "../mem/mem_module.h"
#include "../pct/pct_module.h"
#include "../peq/peq_module.h"
#include "sim_module.h"
#include "somm22.h"
namespace somm22
{

    namespace group
    {

        // ================================================================================== //

        void processNewEvent(Event e)
        {
            // when we go from arrival/postponed to terminal
            pctSetProcessState(e.pid, ProcessState::RUNNING);
            pctSetProcessStartTime(e.pid, sim::simModule.simTime);
            void *newAddrStart = memAlloc(e.pid, pctGetProcessAddressSpaceSize(e.pid));
            if (newAddrStart == NULL)
            {
                // unexpected error, cant allocate memory
                throw Exception(ENOMEM, __func__);
            }
            pctSetProcessMemAddress(e.pid, newAddrStart);
            peqInsert(EventType::TERMINATE, sim::simModule.simTime + pctGetProcessDuration(e.pid), e.pid);
            // check if we can process the first postponed process in the next iteration
            if (peqGetFirstPostponedProcess() != 0 &&
                pctGetProcessAddressSpaceSize(peqGetFirstPostponedProcess()) <= memGetBiggestHole())
            {
                sim::simModule.simMask = POSTPONED;
            }
            else
            {
                sim::simModule.simMask = ARRIVAL | TERMINATE;
            }
        }

        void run()
        {
            // declare queue with postponed events

            sim::simModule.simStep = sim::simModule.simStep + 1;
            Event e = peqFetchNext(simGetCurrentSimMask());
            uint32_t processAddressSpaceSize = pctGetProcessAddressSpaceSize(e.pid);

            // discard in case of exceeding full memory size
            if (processAddressSpaceSize > memGetMaxAllowableSize())
            {
                pctSetProcessState(e.pid, ProcessState::DISCARDED);
                return;
            }

            // update simulation time
            if (e.eventTime > sim::simModule.simTime)
            {
                sim::simModule.simTime = e.eventTime;
            }
            switch (e.eventType)
            {
            case ARRIVAL:
                // if cant allocate enough memory right now

                if (processAddressSpaceSize > memGetBiggestHole())
                {

                    pctSetProcessState(e.pid, ProcessState::SWAPPED);
                    peqInsert(EventType::POSTPONED, e.eventTime, e.pid);
                }
                else
                {

                    processNewEvent(e);
                }
                break;
            case TERMINATE:
                // only needed to free memory and set state as finished
                memFree(pctGetProcessMemAddress(e.pid));
                pctSetProcessState(e.pid, ProcessState::FINISHED);
                if (peqGetFirstPostponedProcess() != 0 &&
                    pctGetProcessAddressSpaceSize(peqGetFirstPostponedProcess()) <= memGetBiggestHole())
                {
                    sim::simModule.simMask = POSTPONED;
                }
                else
                {
                    sim::simModule.simMask = ARRIVAL | TERMINATE;
                }
                break;
            case POSTPONED:
                processNewEvent(e);
                break;
            default:
                break;
            }
        }

        void simRun(uint32_t cnt)
        {
            soProbe(503, "%s(cnt: %u)\n", __func__, cnt);

            if (cnt == 0)
            {
                while (!peqIsEmpty(0b111))
                {
                    run();
                }
            }
            else
            {
                for (uint32_t i = 0; i < cnt; i++)
                {

                    run();
                }
            }
        }

        // ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
