// License: MIT (c) GitLab Inc.
#include <stdio.h>
#include <string.h>
#include <wchar.h>
#include <tchar.h>

int main() {
    char dest1[5];
    wchar_t dest2[5];
    _TCHAR dest3[5];
    unsigned char dest4[5];

    const char* src = "Hello, world!";

    // ruleid: c_buffer_rule-lstrcpy-wcscpy
    lstrcpy(dest1, src);
    printf("lstrcpy: %s\n", dest1);

    // ruleid: c_buffer_rule-lstrcpy-wcscpy
    wcscpy(dest2, L"Hello, world!");
    wprintf(L"wcscpy: %ls\n", dest2);

    // ruleid: c_buffer_rule-lstrcpy-wcscpy
    _tcscpy(dest3, _T("Hello, world!"));
    _tprintf(_T("_tcscpy: %s\n"), dest3);

    // ruleid: c_buffer_rule-lstrcpy-wcscpy
    _mbscpy(dest4, (const unsigned char*)"Hello, world!");
    printf("_mbscpy: %s\n", dest4);

    return 0;
}

