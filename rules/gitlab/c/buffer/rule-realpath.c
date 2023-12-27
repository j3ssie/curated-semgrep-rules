// License: MIT (c) GitLab Inc.
#include <stdlib.h>

#include <stdio.h>

int main() {

  char * symlinkpath = "/tmp/symlink/file";
  char * actualpath;

  // ruleid: c_buffer_rule-realpath
  actualpath = realpath(symlinkpath, NULL);
  return 0;
}

