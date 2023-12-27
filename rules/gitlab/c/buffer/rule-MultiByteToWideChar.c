// License: MIT (c) GitLab Inc.
#include<stdio.h>

int main() {
  char d[20];
  char s[20];
  int n;
  char *szName = "SomeName";
  wchar_t wszUserName[5];

  // ruleid: c_buffer_rule-MultiByteToWideChar
  MultiByteToWideChar(CP_ACP, 0, szName, -1, wszUserName, sizeof(wszUserName));

  // ruleid: c_buffer_rule-MultiByteToWideChar
  MultiByteToWideChar(CP_ACP, 0, szName, -1, wszUserName, sizeof wszUserName);

  // ruleid: c_buffer_rule-MultiByteToWideChar
  MultiByteToWideChar(CP_ACP, 0, szName, -1, wszUserName, sizeof(wszUserName) / sizeof(wszUserName[0]));

  // ruleid: c_buffer_rule-MultiByteToWideChar
  MultiByteToWideChar(CP_ACP, 0, szName, -1, wszUserName, sizeof wszUserName / sizeof(wszUserName[0]));
}
