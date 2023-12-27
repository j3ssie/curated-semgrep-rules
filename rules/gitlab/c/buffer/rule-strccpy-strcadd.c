// License: MIT (c) GitLab Inc.
#include <libgen.h>

char output[100];
char cp[100];

int main() {
  // ruleid: c_buffer_rule-strccpy-strcadd
  strccpy(output, inputFromUser());

  // ruleid: c_buffer_rule-strccpy-strcadd
  cp = strcadd(output, input1);
}

