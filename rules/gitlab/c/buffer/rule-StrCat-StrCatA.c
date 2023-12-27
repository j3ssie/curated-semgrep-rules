// License: MIT (c) GitLab Inc.

#include <stdio.h>
#include <string.h>

int main() {
    char str1[100] = "Hello, ";
    char str2[] = "world!";

    // ruleid: c_buffer_rule-StrCat-StrCatA
    strcat(str1, str2);

    char str3[100] = "Good ";
    char str4[] = "morning!";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    strcat(str3, str4);

    wchar_t wstr1[100] = L"Hello, ";
    wchar_t wstr2[] = L"world!";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    StrcatW(wstr1, wstr2);

    char lstr1[100] = "This is ";
    char lstr2[] = "a test.";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    lstrcatA(lstr1, lstr2);

    wchar_t lwstr1[100] = L"This is ";
    wchar_t lwstr2[] = L"a test.";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    lstrcatW(lwstr1, lwstr2);

    char buffer1[100] = "Hello, ";
    char buffer2[] = "world!";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    strCatBuff(buffer1, buffer2, sizeof(buffer1));

    char buffer3[100] = "Good ";
    char buffer4[] = "night!";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    StrCatBuffA(buffer3, buffer4, sizeof(buffer3));

    wchar_t wbuffer1[100] = L"Hello, ";
    wchar_t wbuffer2[] = L"universe!";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    StrCatBuffW(wbuffer1, wbuffer2, sizeof(wbuffer1));

    wchar_t chain1[100] = L"This ";
    wchar_t chain2[] = L"is ";
    wchar_t chain3[] = L"a ";
    wchar_t chain4[] = L"chain.";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    StrCatChainW(chain1, sizeof(chain1), 4, chain2, chain3, chain4);

    TCHAR tstr1[100] = _T("Hello, ");
    TCHAR tstr2[] = _T("TCHAR!");
    // ruleid: c_buffer_rule-StrCat-StrCatA
    _tccat(tstr1, tstr2);

    char mbs1[100] = "Hello, ";
    char mbs2[] = "MBCS!";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    _mbccat(mbs1, mbs2);

    TCHAR ftstr1[100] = _T("Good ");
    TCHAR ftstr2[] = _T("evening!");
    // ruleid: c_buffer_rule-StrCat-StrCatA
    _ftcscat(ftstr1, ftstr2);

    char nstr1[100] = "This ";
    char nstr2[] = "is a ";
    char nstr3[] = "test.";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    StrCatN(nstr1, nstr2, sizeof(nstr1), strlen(nstr2));

    char nastr1[100] = "This ";
    char nastr2[] = "is a ";
    char nastr3[] = "test.";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    StrCatNA(nastr1, nastr2, sizeof(nastr1), strlen(nastr2));

    wchar_t nwstr1[100] = L"This ";
    wchar_t nwstr2[] = L"is a ";
    wchar_t nwstr3[] = L"test.";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    StrCatNW(nwstr1, nwstr2, sizeof(nwstr1), wcslen(nwstr2));

    char ncstr1[100] = "Hello, ";
    char ncstr2[] = "world!";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    StrNCat(ncstr1, ncstr2, sizeof(ncstr1) - strlen(ncstr1) - 1);

    char ncstr3[100] = "Good ";
    char ncstr4[] = "afternoon!";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    StrNCatA(ncstr3, ncstr4, sizeof(ncstr3) - strlen(ncstr3) - 1);

    wchar_t ncwstr1[100] = L"Hello, ";
    wchar_t ncwstr2[] = L"galaxy!";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    StrNCatW(ncwstr1, ncwstr2, sizeof(ncwstr1) - wcslen(ncwstr1) - 1);

    char nlstr1[100] = "This is ";
    char nlstr2[] = "a sample.";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    lstrncat(nlstr1, nlstr2, sizeof(nlstr1) - strlen(nlstr1) - 1);

    char nlstr3[100] = "This is ";
    char nlstr4[] = "another sample.";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    lstrcatnA(nlstr3, nlstr4, sizeof(nlstr3) - strlen(nlstr3) - 1);

    wchar_t nlwstr1[100] = L"This is ";
    wchar_t nlwstr2[] = L"yet another sample.";
    // ruleid: c_buffer_rule-StrCat-StrCatA
    lstrcatnW(nlwstr1, nlwstr2, sizeof(nlwstr1) - wcslen(nlwstr1) - 1);

    return 0;
}
