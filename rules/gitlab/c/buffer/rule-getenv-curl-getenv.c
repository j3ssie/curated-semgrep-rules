// License: MIT (c) GitLab Inc.
#include <stdio.h>
#include <stdlib.h>

int main() {
  // ruleid: c_buffer_rule-getenv-curl-getenv
  printf("HOME : %s\n", getenv("HOME"));

  // ruleid: c_buffer_rule-getenv-curl-getenv
  printf("PATH : %s\n", curl_getenv("PATH"));

  return 0;
}

