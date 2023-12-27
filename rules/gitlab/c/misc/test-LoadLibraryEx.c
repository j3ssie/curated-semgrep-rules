// License: MIT (c) GitLab Inc.
#include <stdio.h>

void main() {
  (void) LoadLibraryEx(L "user32.dll", nullptr, LOAD_LIBRARY_AS_DATAFILE);
}