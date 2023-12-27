// License: MIT (c) GitLab Inc.
#include <stdio.h>
#include <string.h>

int main() {
    char source[] = "Hello, World! Hello, World! Hello, World!";
    char destination[10];

    // ruleid:c_buffer_rule-streadd-strecpy
    streadd(destination, source);

    // ruleid:c_buffer_rule-streadd-strecpy
    strecpy(destination, source);

    return 0;
}

