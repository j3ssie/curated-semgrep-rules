// License: MIT (c) GitLab Inc.
#include <stdio.h>
#include <wchar.h>
#include <tchar.h>

int main() {
    char str[50];
    wchar_t wstr[50];
    _TCHAR tstr[50];

    int num = 42;
    const char* msg = "Hello, world!";
    const wchar_t* wmsg = L"Hello, world!";
    const _TCHAR* tmsg = _T("Hello, world!");

    // ruleid: c_buffer_rule-sprintf-vsprintf
    sprintf(str, "%s %d", msg, num);
    printf("sprintf: %s\n", str);

    // ruleid: c_buffer_rule-sprintf-vsprintf
    vsprintf(str, "%s %d", msg, num);
    printf("vsprintf: %s\n", str);

    // ruleid: c_buffer_rule-sprintf-vsprintf
    swprintf(wstr, 50, L"%ls %d", wmsg, num);
    wprintf(L"swprintf: %ls\n", wstr);

    // ruleid: c_buffer_rule-sprintf-vsprintf
    vswprintf(wstr, 50, L"%ls %d", wmsg, num);
    wprintf(L"vswprintf: %ls\n", wstr);

    // ruleid: c_buffer_rule-sprintf-vsprintf
    _stprintf(tstr, _T("%s %d"), tmsg, num);
    _tprintf(_T("_stprintf: %s\n"), tstr);

    // ruleid: c_buffer_rule-sprintf-vsprintf
    _vstprintf(tstr, _T("%s %d"), tmsg, num);
    _tprintf(_T("_vstprintf: %s\n"), tstr);

    return 0;
}

