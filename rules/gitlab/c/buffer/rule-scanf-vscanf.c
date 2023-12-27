// License: MIT (c) GitLab Inc.
#include <stdio.h>
#include <wchar.h>
#include <tchar.h>

int main() {
    const char* format = fmtStrFromUser();
    const wchar_t* wformat = vfmtStrFromUser();
    const _TCHAR* tformat = tfmtStrFromUser();

    char str[20];
    wchar_t wstr[20];
    int num;

    // ruleid: c_buffer_rule-scanf-vscanf
    scanf(format, str, &num);
    printf("scanf: %s %d\n", str, num);

    // ok: c_buffer_rule-scanf-vscanf
    scanf("%s %d", str, &num);
    printf("scanf: %s %d\n", str, num);

    // ruleid: c_buffer_rule-scanf-vscanf
    vscanf(wformat, str, &num);
    wprintf(L"vscanf: %ls %d\n", str, num);

    // ok: c_buffer_rule-scanf-vscanf
    vscanf(L"%ls %d", str, &num);
    wprintf(L"vscanf: %ls %d\n", str, num);

    // ruleid: c_buffer_rule-scanf-vscanf
    wscanf(wformat, str, &num);
    wprintf(L"wscanf: %ls %d\n", str, num);

    // ok: c_buffer_rule-scanf-vscanf
    wscanf(L"%ls %d", str, &num);
    wprintf(L"wscanf: %ls %d\n", str, num);

    // ruleid: c_buffer_rule-scanf-vscanf
    _tscanf(tformat, str, &num);
    _tprintf(_T("_tscanf: %s %d\n"), str, num);

    // ok: c_buffer_rule-scanf-vscanf
    _tscanf(_T("%s %d"), str, &num);
    _tprintf(_T("_tscanf: %s %d\n"), str, num);

    return 0;
}

