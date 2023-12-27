// License: MIT (c) GitLab Inc.
#include <stdio.h>
#include <string.h>
#include <wchar.h>
#include <tchar.h>

int main() {
    char dest1[10];
    wchar_t dest2[10];
    _TCHAR dest3[10];
    unsigned char dest4[10];

    const char* src = "Hello, world!";

    // ruleid: c_buffer_rule-lstrcpyn-wcsncpy
    lstrcpyn(dest1, src, sizeof(dest1) / sizeof(dest1[0]));
    printf("lstrcpyn: %s\n", dest1);

    // ruleid: c_buffer_rule-lstrcpyn-wcsncpy
    wcsncpy(dest2, L"Hello, world!", sizeof(dest2) / sizeof(dest2[0]));
    wprintf(L"wcsncpy: %ls\n", dest2);

    // ruleid: c_buffer_rule-lstrcpyn-wcsncpy
    _tcsncpy(dest3, _T("Hello, world!"), sizeof(dest3) / sizeof(dest3[0]));
    _tprintf(_T("_tcsncpy: %s\n"), dest3);

    // ruleid: c_buffer_rule-lstrcpyn-wcsncpy
    _mbsnbcpy(dest4, (const unsigned char*)"Hello, world!", sizeof(dest4));
    printf("_mbsnbcpy: %s\n", dest4);

    return 0;
}

