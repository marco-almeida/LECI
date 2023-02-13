/*
 *  \author Rafael Amorim
 *  \author Marco Almeida
 */

#include "pct_module.h"
#include "somm22.h"

#include <algorithm>
#include <array>
#include <cstring>
#include <fstream>
#include <iostream>
#include <string>

using namespace std;

namespace somm22 {

namespace group {
// ==============================AUXILIARY FUNCTIONS================================= //
std::string &ltrim(std::string &s) {
    auto it = std::find_if(s.begin(), s.end(), [](char c) { return !std::isspace<char>(c, std::locale::classic()); });
    s.erase(s.begin(), it);
    return s;
}

std::string &rtrim(std::string &s) {
    auto it = std::find_if(s.rbegin(), s.rend(), [](char c) { return !std::isspace<char>(c, std::locale::classic()); });
    s.erase(it.base(), s.end());
    return s;
}

std::string &trim(std::string &s) { return ltrim(rtrim(s)); }
// ================================================================================== //
array<uint32_t, 4> sanitizeCSVLine(string line) {
    string delim = ";"; // delimiter
    array<uint32_t, 4> pctInfo;
    size_t pos = 0;
    string token1; // define a string variable
    unsigned int i = 0;
    while ((pos = line.find(delim)) != string::npos) {
        token1 = line.substr(0, pos); // store the substring
        pctInfo[i++] = stoi(token1);
        line.erase(0, pos + delim.length()); /* erase() function store the current positon and move to next token. */
    }
    line.erase(0, pos + delim.length());
    line.erase(0, 2);                     // tirar 0x do inicio
    pctInfo[3] = stoi(line, nullptr, 16); // addrSpaceSize
    return pctInfo;
}

void pctInit(const char *fname) {
    soProbe(201, "%s(\"%s\")\n", __func__, fname);

    ifstream file(fname);
    string line;
    if (file.is_open()) {
        while (getline(file, line)) {
            if (trim(line)[0] != '#') { // Comment line in file
                array<uint32_t, 4> pctInfo = sanitizeCSVLine(line);
                pctInsert(pctInfo[0], pctInfo[1], pctInfo[2], pctInfo[3]);
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
