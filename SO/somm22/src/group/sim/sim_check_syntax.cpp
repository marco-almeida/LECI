/*
 *  \author Marco Almeida
 *  \author Rafael Amorim
 */

#include "sim_module.h"
#include "somm22.h"
#include <algorithm>
#include <cctype>
#include <cstring>
#include <fstream>
#include <iostream>
#include <regex>
#include <set>
#include <string>

using namespace std;
namespace somm22 {

namespace group {

// ===============================AUXILIARY FUNCTIONS================================ //

std::string &ltrime(std::string &s) {
    auto it = std::find_if(s.begin(), s.end(), [](char c) { return !std::isspace<char>(c, std::locale::classic()); });
    s.erase(s.begin(), it);
    return s;
}

std::string &rtrime(std::string &s) {
    auto it = std::find_if(s.rbegin(), s.rend(), [](char c) { return !std::isspace<char>(c, std::locale::classic()); });
    s.erase(it.base(), s.end());
    return s;
}

std::string &trime(std::string &s) { return ltrime(rtrime(s)); }

bool lineIsComment(string line) { return trime(line)[0] == '#'; }

string eraseWhitespace(string line) {
    line.erase(remove_if(line.begin(), line.end(), ::isspace), line.end());
    return line;
}

// ================================================================================== //

bool simCheckInputFormat(const char *fname) {
    soProbe(502, "%s(\"%s\")\n", __func__, fname);
    ifstream file(fname);
    string line;
    set<uint32_t> known_pids;
    unsigned int lineNumber = 0;

    if (!file.is_open())
        throw Exception(ENOENT, __func__);

    while (getline(file, line)) {
        if (lineIsComment(line)) {
            continue;
        }

        string trimmedLine = eraseWhitespace(line);
        regex reg("^0*[1-9][0-9]*;[0-9]+;0*[1-9][0-9]*;(0x0*[1-9a-fA-F][0-9a-fA-F]*)?$");

        if (!regex_match(trimmedLine, reg)) { // does not match pattern
            cerr << "-- Match failure: " << line << "\n";
            return false; // ou return false?
        }

        // first field is the PID, parse it to number so we can store it later
        string number = "";
        for (long unsigned int i = 0; i < trimmedLine.length(); i++) {
            if (trimmedLine[i] == ';') {
                break;
            }
            number += trimmedLine[i];
        }

        // check for duplicate PIDs using set size
        uint32_t size_before_insertion = known_pids.size();
        uint32_t num = stoul(number);
        known_pids.insert(num);

        // if set size remains the same, it means the PID was already inserted
        if (known_pids.size() == size_before_insertion) {
            fprintf(stderr, "%s %u %s %u %s \"%s\"\n", "-- Semantic error at line", ++lineNumber, "(pid", num,
                    "is repeated):", line.c_str());
            return false;
        }
        lineNumber++;
    }
    file.close();
    return true;
}

// ================================================================================== //

} // end of namespace group

} // end of namespace somm22
