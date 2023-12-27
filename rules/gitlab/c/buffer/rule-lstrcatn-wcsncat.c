// License: MIT (c) GitLab Inc.
#include <stdio.h>
#include <string.h>
#include <wchar.h>
#include <tchar.h>

int main() {
    char dest1[10] = "Hello, ";
    wchar_t dest2[10] = L"Hello, ";
    _TCHAR dest3[10] = _T("Hello, ");
    unsigned char dest4[10] = "Hello, ";

    const char* src = "world!";

    // ruleid: c_buffer_rule-lstrcatn-wcsncat
    lstrcatn(dest1, src, 7);
    printf("lstrcatn: %s\n", dest1);

    // ruleid: c_buffer_rule-lstrcatn-wcsncat
    wcsncat(dest2, L"world!", 7);
    wprintf(L"wcsncat: %ls\n", dest2);

    // ruleid: c_buffer_rule-lstrcatn-wcsncat
    _tcsncat(dest3, _T("world!"), 7);
    _tprintf(_T("_tcsncat: %s\n"), dest3);

    // ruleid: c_buffer_rule-lstrcatn-wcsncat
    _mbsnbcat(dest4, (const unsigned char*)"world!", 7);
    printf("_mbsnbcat: %s\n", dest4);

    return 0;
}

