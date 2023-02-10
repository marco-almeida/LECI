/*
 *  \author Marco Almeida
 */

#include "peq_module.h"
#include "somm22.h"
#include <iostream>
#include <queue>
namespace somm22 {

namespace group {

// ================================================================================== //

void peq_auxiliar(FILE *file) {

    for (Event e : peq::process_event_deque) {
        fprintf(file, "|%10u | %-9s |%6u |\n", e.eventTime, peqEventTypeAsString(e.eventType), e.pid);
    }
    fprintf(file, "+===============================+\n\n");
}

void peq_printHeader(FILE *file) {
    fprintf(file, "+===============================+\n");
    fprintf(file, "|      Process Event Queue      |\n");
    fprintf(file, "+-----------+-----------+-------+\n");
    fprintf(file, "| eventTime | eventType |  PID  |\n");
    fprintf(file, "+-----------+-----------+-------+\n");
}

void peqLog() {
    soProbe(302, "%s()\n", __func__);
    FILE *file = logGetStream();
    peq_printHeader(file);
    peq_auxiliar(file);
}

// ================================================================================== //

void peqLogEvent(Event *e, const char *msg) {
    soProbe(302, "%s(...)\n", __func__);
    FILE *file = logGetStream();
    fprintf(file, "%s: (%s, %u, %u)\n", msg, peqEventTypeAsString(e->eventType), e->eventTime, e->pid);
}

// ================================================================================== //

void peqPrint(const char *fname, PrintMode mode) {
    soProbe(302, "%s(\"%s\", %s)\n", __func__, fname, (mode == NEW) ? "NEW" : "APPEND");
    FILE *file;
    const char *m = mode == NEW ? "w" : "a";
    file = fopen(fname, m); // open file in write mode
    // prints header if file is empty
    if (NULL != file) {
        fseek(file, 0, SEEK_END);
        int size = ftell(file);

        if (0 == size) {
            peq_printHeader(file);
        }
    }
    std::cerr << file << std::endl;
    peq_auxiliar(file);
    fclose(file);
}

// ================================================================================== //

} // end of namespace group

} // end of namespace somm22
