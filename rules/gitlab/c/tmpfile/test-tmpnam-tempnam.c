// License: MIT (c) GitLab Inc.
#include <stdio.h>

#include <stdlib.h>

int main() {
  char filenamegen[L_tmpnam + 1];
  tmpnam(filenamegen);
  tempnam("/users", "file");
  return 0;
}