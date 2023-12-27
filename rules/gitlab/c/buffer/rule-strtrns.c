// License: MIT (c) GitLab Inc.

#include <stdio.h>

#include <libgen.h>

void main(int argc, char ** argv) {
  char lower[] = "abcdefghijklmnopqrstuvwxyz";
  char upper[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  char * buf;
  buf = (char * ) malloc(strlen(argv[1]));

  // ruleid:c_buffer_rule-strtrns
  strtrns(argv[1], lower, upper, buf);

  printf("%s\n", buf);
}

