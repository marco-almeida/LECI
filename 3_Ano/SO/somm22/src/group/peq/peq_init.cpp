/*
 *  \author Marco Almeida
 */

#include "../pct/pct_init.cpp"
#include "peq_module.h"
#include "somm22.h"
#include <array>
#include <cstring>
#include <fstream>
#include <iostream>
#include <string>
using namespace std;

namespace somm22 {

namespace group {

// ================================================================================== //

array<uint32_t, 3> peqSanitizeCSVLine(string line) {
    string delim = ";"; // delimiter
    array<uint32_t, 3> peqInfo;
    size_t pos = 0;
    string token1; // define a string variable
    int i = 0;
    // use find() function to get the position of the delimiters
    while ((pos = line.find(delim)) != string::npos) {
        token1 = line.substr(0, pos); // store the substring
        peqInfo[i++] = stoi(token1);
        line.erase(0, pos + delim.length()); /* erase() function store the current positon and move to next token. */
    }
    return peqInfo;
}

void peqInit(const char *fname) {
    soProbe(301, "%s(\"%s\")\n", __func__, fname);

    ifstream file(fname);
    string line;
    if (file.is_open()) {
        while (getline(file, line)) {
            if (trim(line)[0] != '#') { // Comment line
                array<uint32_t, 3> peqInfo = peqSanitizeCSVLine(line);
                peqInsert(ARRIVAL, peqInfo[1], peqInfo[0]);
            }
        }
        file.close();
    } else {
        throw Exception(ENOENT, __func__);
    }
}

// ================================================================================== //

} // end of namespace group

} // end of namespace somm22
