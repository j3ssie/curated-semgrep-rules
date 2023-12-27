// License: MIT (c) GitLab Inc.
#include <stdio.h>
#include <string.h>

int main() {
    char str[] = "Hello, World! Hello, World! Hello, World!";
    wchar_t wstr[] = L"Hello, World! Hello, World! Hello, World!";
    _TCHAR tstr[] = _T("Hello, World! Hello, World! Hello, World!");
    unsigned char mstr[] = "Hello, World! Hello, World! Hello, World!";

    // ruleid:c_buffer_rule-strlen-wcslen
    int len = strlen(str);

    // ruleid:c_buffer_rule-strlen-wcslen
    int wlen = wcslen(wstr);

    // ruleid:c_buffer_rule-strlen-wcslen
    int tlen = _tcslen(tstr);

    // ruleid:c_buffer_rule-strlen-wcslen
    int mlen = _mbslen(mstr);

    return 0;
}

