/*
    Author: Andrew Schneider 
    Lab3p2
*/

#include "lab3p2.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {
    int i;
    int operation;
    char *s1;
    char *s2;
    operation = *argv[1] - '0';
    if (operation == 1) {
        char* con = cat(argv[2], argv[3]);
    } else if (operation == 2) {
        int equalSuper = equalS(argv[2], argv[3]);
        if (equalSuper == 0) {
            printf("String1 and String2 are equal.\n");
        } else {
            printf("String1 and String2 are NOT equal.\n");
        }
    } else if (operation == 3) {
        char* newOne = toLowerCase(argv[2]);
        printf("%s\n", newOne);
    }
    
    return 0;
}


