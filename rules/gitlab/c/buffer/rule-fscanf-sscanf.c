// License: MIT (c) GitLab Inc.
#include <stdio.h>

int main() {
    char input[10];
    int num;

    printf("Enter a number: ");
    // ruleid: c_buffer_rule-fscanf-sscanf
    fscanf(stdin, "%s", input);

    // ruleid: c_buffer_rule-fscanf-sscanf
    sscanf(input, "%d", &num);

    printf("Parsed number: %d\n", num);

    return 0;
}
