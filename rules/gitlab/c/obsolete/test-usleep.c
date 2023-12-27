// License: MIT (c) GitLab Inc.
#include <unistd.h>

void main() {
  unsigned int millis = 500000;
  int expect = usleep(millis);

}