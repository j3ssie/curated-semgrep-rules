// License: MIT (c) GitLab Inc.
#include <stdio.h>

#include <string.h>

int main() {
  char str1[100] = "You are", str2[] = "GitLab";

  // ruleid: c_buffer_rule-strcat
  strcat(str1, str2);
  puts(str1);
  puts(str2);

  return 0;
}

