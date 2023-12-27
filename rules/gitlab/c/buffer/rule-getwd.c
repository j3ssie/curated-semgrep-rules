// License: MIT (c) GitLab Inc.

#include <stdlib.h>

#include <unistd.h>

int main() {
  char * buf;
  char * ptr;

  // ruleid: c_buffer_rule-getwd
  ptr = getwd(buf);
  return 0;
}

