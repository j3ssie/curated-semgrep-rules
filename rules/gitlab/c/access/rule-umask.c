// License: MIT (c) GitLab Inc.

#include <stdio.h>

#include <stdlib.h>

#include <sys/types.h>

#include <sys/stat.h>

int main(void) {
  mode_t omask;
  mode_t nmask;

  nmask = S_IRUSR | S_IWUSR | /* owner read write */
    S_IRGRP | S_IWGRP | /* group read write */
    S_IROTH; /* other read */

  // ruleid: c_access_rule-umask
  omask = umask(nmask);
  printf("The new mask is %o\n", nmask);
  return 0;
}
