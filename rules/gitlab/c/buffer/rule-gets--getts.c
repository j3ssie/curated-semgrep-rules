// License: MIT (c) GitLab Inc.
#include <stdio.h>
#include <wchar.h>

int main() {
  char string[50];
  printf("Please enter your str input");
  // ruleid: c_buffer_rule-gets--getts
  gets(string);
  printf("Your input: %s", string);

  // ruleid: c_buffer_rule-gets--getts
  _getts(string);
  printf("Your input: %s", string);

  // ruleid: c_buffer_rule-gets--getts
  _getws(string);
  printf("Your input: %s", string);

  return 0;
}

