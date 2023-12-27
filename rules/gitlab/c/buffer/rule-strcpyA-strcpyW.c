// License: MIT (c) GitLab Inc.
#include <stdio.h>
#include <string.h>

int main() {
    char source[] = "Hello, World! Hello, World! Hello, World!";
    char destination[10];

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    strcpyA(destination, source);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    strcpyW(destination, source);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    StrCpy(destination, source);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    StrCpyA(destination, source);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    lstrcpyA(destination, source);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    lstrcpyW(destination, source);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    _tccpy(destination, source);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    _mbccpy(destination, source);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    _ftcscpy(destination, source);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    _mbsncpy(destination, source, 10);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    StrCpyN(destination, source, 10);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    StrCpyNA(destination, source, 10);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    StrCpyNW(destination, source, 10);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    StrNCpy(destination, source, 10);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    strcpynA(destination, source, 10);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    StrNCpyA(destination, source, 10);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    StrNCpyW(destination, source, 10);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    lstrcpynA(destination, source, 10);

    // ruleid:c_buffer_rule-strcpyA-strcpyW
    lstrcpynW(destination, source, 10);

    return 0;
}

