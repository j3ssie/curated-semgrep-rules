// License: MIT (c) GitLab Inc.
#include <stdio.h>
#include <wchar.h>
#include <tchar.h>
#include <mbstring.h>

int main() {
    wchar_t dest1[20] = L"Hello, ";
    wchar_t src1[] = L"world!";

    // ruleid: c_buffer_rule-lstrcat-wcscat
    wcscat(dest1, src1);
    wprintf(L"wcscat: %ls\n", dest1);

    char dest2[20] = "Hello, ";
    char src2[] = "world!";

    // ruleid: c_buffer_rule-lstrcat-wcscat
    lstrcat(dest2, src2);
    printf("lstrcat: %s\n", dest2);

    // ruleid: c_buffer_rule-lstrcat-wcscat
    _mbscat((unsigned char*)dest2, (const unsigned char*)src2);
    printf("_mbscat: %s\n", dest2);

    _TCHAR dest3[20] = _T("Hello, ");
    _TCHAR src3[] = _T("world!");

    // ruleid: c_buffer_rule-lstrcat-wcscat
    _tcscat(dest3, src3);
    _tprintf(_T("_tcscat: %s\n"), dest3);

    return 0;
}

