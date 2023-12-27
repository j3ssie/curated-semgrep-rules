// License: MIT (c) GitLab Inc.
#include <stdio.h>
#include <string.h>

int main() {
    char dest1[10];
    char dest2[10];
    char dest3[10];

    char src[] = "Hello, world!";

    // ruleid: c_buffer_rule-memcpy-CopyMemory
    memcpy(dest1, src, sizeof(dest1));
    printf("memcpy: %s\n", dest1);

    // ruleid: c_buffer_rule-memcpy-CopyMemory
    CopyMemory(dest2, src, sizeof(dest2));
    printf("CopyMemory: %s\n", dest2);

    // ruleid: c_buffer_rule-memcpy-CopyMemory
    bcopy(src, dest3, sizeof(dest3));
    printf("bcopy: %s\n", dest3);

    return 0;
}

