/*
 *
 * \author (2016) Artur Pereira <artur at ua.pt>
 */

#include "ull.h"

#include <errno.h>
#include <stdint.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
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
    if (ptr->next != NULL) {
        delete_elements(ptr->next);
        delete ptr;
    }
    head = NULL;
}

void reset() { delete_elements(head); }

/* ************************************************* */

void load(const char *fname) {}

/* ************************************************* */

void print() {
    for (Node *ptr = head; ptr != NULL; ptr = ptr->next) {
        printf("%d %s\n", ptr->reg.nmec, ptr->reg.name);
    }
}

/* ************************************************* */

void insert(uint32_t nmec, const char *name) {
    if (head == NULL) {
        head = new Node();
        head->next = NULL;
        head->reg.name = name;
        head->reg.nmec = nmec;
    } else {
        Node *ptr = head;
        for (; ptr->next != NULL; ptr = ptr->next) {
        }
        Node *novo = new Node();
        ptr->next = novo;
        novo->reg.name = name;
        novo->reg.nmec = nmec;
        novo->next = NULL;
    }
}

/* ************************************************* */

const char *query(uint32_t nmec) {
    for (Node *ptr = head; ptr != NULL; ptr = ptr->next) {
        if (ptr->reg.nmec == nmec) {
            return ptr->reg.name;
        }
    }
    fprintf(stderr, "Student %u does not exist in the database!\n", nmec);
    return "";
}

/* ************************************************* */

void remove(uint32_t nmec) {
    const char *name = query(nmec);
    if (strcmp(name, "") == 0) {
        return;
    }

    char buffer[75];
    snprintf(buffer, sizeof buffer, "Student %d %s removed successfully\n", nmec, name);

    if (head->reg.nmec == nmec) {
        Node* ptr = head;
        delete head;
        head = ptr->next;
        printf("%s", buffer);
        return;
    }

    Node *nextptr = head->next;
    for (Node *ptr = head; nextptr != NULL; ptr = ptr->next, nextptr = nextptr->next) {
        if (nextptr->reg.nmec == nmec) {
            ptr->next = nextptr->next;
            delete nextptr;
            printf("%s", buffer);
            return;
        }
    }
}

/* ************************************************* */

}  // namespace ull
