/*
 *  \author Rafael Amorim
 *  \author Marco Almeida
 */

#include "pct_module.h"
#include "somm22.h"

#include <array>
#include <fstream>
#include <iostream>
#include <sstream>
#include <string>

using namespace std;

namespace somm22 {

namespace group {

int compare(const void *a, const void *b) {
    int int_a = *((int *)a);
    int int_b = *((int *)b);

    if (int_a == int_b)
        return 0;
    else if (int_a < int_b)
        return -1;
    else
        return 1;
}

void auxiliar(FILE *file) {
    // sort pct::process_table by key
    int keys[pct::process_table.size()];
    int i = 0;
    for (const auto p : pct::process_table) {
        keys[i++] = p.first;
    }
    qsort(keys, pct::process_table.size(), sizeof(int), compare);
    for (i = 0; i < (int)pct::process_table.size(); i++) {
        if (pct::process_table[keys[i]].state == TO_COME || pct::process_table[keys[i]].state == DISCARDED ||
            pct::process_table[keys[i]].state == SWAPPED) {
            fprintf(file, "|%6u |%12u |%9u |%#14x | %-9s |%10s |%10s |\n", keys[i],
                    pct::process_table[keys[i]].arrivalTime, pct::process_table[keys[i]].duration,
                    pct::process_table[keys[i]].addrSpaceSize, pctGetStateName(keys[i]), "(nil)  ", "(nil)  ");
        } else {
            fprintf(file, "|%6u |%12u |%9u |%#14x | %-9s |%10u |%10p |\n", keys[i],
                    pct::process_table[keys[i]].arrivalTime, pct::process_table[keys[i]].duration,
                    pct::process_table[keys[i]].addrSpaceSize, pctGetStateName(keys[i]),
                    pct::process_table[keys[i]].startTime, pct::process_table[keys[i]].memAddr);
        }
    }
    fprintf(file, "+====================================================================================+\n\n");
}

void printHeader(FILE *file) {
    fprintf(file, "+====================================================================================+\n");
    fprintf(file, "|                               Process Control Table                                |\n");
    fprintf(file, "+-------+-------------+----------+---------------+-----------+-----------------------+\n");
    fprintf(file, "|  PID  | arrivalTime | duration | addrSpaceSize |   state   | startTime |  memAddr  |\n");
    fprintf(file, "+-------+-------------+----------+---------------+-----------+-----------------------+\n");
}

void pctPrint(const char *fname, PrintMode mode) {
    soProbe(202, "%s(\"%s\", %s)\n", __func__, fname, (mode == NEW) ? "NEW" : "APPEND");
    FILE *file;
    const char *m = mode == NEW ? "w" : "a";
    file = fopen(fname, m); // open file in write mode
    // prints header if file is empty
    if (NULL != file) {
        fseek(file, 0, SEEK_END);
        int size = ftell(file);

        if (0 == size) {
            printHeader(file);
        }
    }
    cerr << file << endl;
    auxiliar(file);
    fclose(file);
}

void pctLog() {
    soProbe(202, "%s()\n", __func__);
    FILE *file = logGetStream();
    printHeader(file);
    auxiliar(file);
}

} // end of namespace group

} // end of namespace somm22
