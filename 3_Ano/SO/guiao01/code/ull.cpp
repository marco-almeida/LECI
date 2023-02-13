/*
 *
 * \author (2016) Artur Pereira <artur at ua.pt>
 */

#include "ull.h"

#include <errno.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

namespace ull {
/* ************************************************* */

/* The information support data structure  */
struct Register {
    uint32_t nmec;     //!< student number
    const char *name;  //!< student name
};

/* The linked-list support data structure */
struct Node {
    Register reg;
    struct Node *next;
};

static Node *head = NULL;

/* ************************************************* */
void delete_elements(Node *ptr) {
    if (!head) {
        return;
    }
    if (ptr->next != NULL) {
        delete_elements(ptr->next);
        delete ptr;
    }
    head = NULL;
}

void reset() { delete_elements(head); }

/* ************************************************* */

void load(const char *fname) {
    FILE *ptr = fopen(fname, "r");
    if (ptr == NULL) {
        fprintf(stderr, "File %s not found!", fname);
        return;
    }

    char line[200];
    while (fgets(line, 200, ptr) != NULL) {
        char *name = new char[100];  // tem que ter new
        uint32_t nmec;
        const char *token = strtok(line, ";");
        // file must have format <name;nmec>
        strcpy(name, token);
        token = strtok(NULL, ";");
        nmec = strtoul(token, NULL, 0);
        insert(nmec, name);
    }
    fclose(ptr);
}

/* ************************************************* */

void print() {
    for (Node *ptr = head; ptr != NULL; ptr = ptr->next) {
        printf("%u %s\n", ptr->reg.nmec, ptr->reg.name);
    }
}

/* ************************************************* */

void insert(uint32_t nmec, const char *name) {
    Node *now = head;
    Node *previous = NULL;

    for (; now != NULL; previous = now, now = now->next) {
        if (nmec == now->reg.nmec) {
            fprintf(stderr, "Student with nmec %u already exists!\n", nmec);
            return;
        }
        if (nmec < now->reg.nmec) {
            break;
        }
    }
    Node *novo = new Node();
    novo->reg.name = name;
    novo->reg.nmec = nmec;

    if (!previous) {
        novo->next = head;
        head = novo;
    } else {
        previous->next = novo;
        novo->next = now;
    }
}

/* ************************************************* */

const char *query(uint32_t nmec) {
    for (Node *ptr = head; ptr != NULL; ptr = ptr->next) {
        if (ptr->reg.nmec == nmec) {
            return ptr->reg.name;
        }
        if (ptr->reg.nmec > nmec) {
            break;
        }
    }
    fprintf(stderr, "Student %u does not exist in the database!\n", nmec);
    return NULL;
}

/* ************************************************* */

void remove(uint32_t nmec) {
    const char *name = query(nmec);
    if (!name) {
        return;
    }

    char buffer[100];
    snprintf(buffer, sizeof buffer, "Student %d %s removed successfully\n",
             nmec, name);

    if (head->reg.nmec == nmec) {
        Node *ptr = head;
        delete head;
        head = ptr->next;
        printf("%s", buffer);
        return;
    }

    Node *nextptr = head->next;
    for (Node *ptr = head; nextptr != NULL;
         ptr = ptr->next, nextptr = nextptr->next) {
        if (nextptr->reg.nmec == nmec) {
            ptr->next = nextptr->next;
            delete nextptr;
            printf("%s", buffer);
            return;
        }
    }
}

void store(const char *fileName) {
    FILE *out_file = fopen(fileName, "w");

    for (Node *ptr = head; ptr != NULL; ptr = ptr->next) {
        fprintf(out_file, "%s;%u\n", ptr->reg.name, ptr->reg.nmec);
    }
    fclose(out_file);  
}

/* ************************************************* */

}  // namespace ull
