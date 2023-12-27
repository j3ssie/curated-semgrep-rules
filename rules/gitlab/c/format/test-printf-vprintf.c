// License: MIT (c) GitLab Inc.
#include<stdio.h>

#include <stdarg.h>

#include <wchar.h>

void vprintfex(char * format, ...) {
  va_list args;

  va_start(args, format);
  vprintf(format, args);
  va_end(args);
}

int main() {
  vprintfex("%d variable argument\n", 1);
  vprintfex("%d variable %s\n", 2, "arguments");
  printf("An example only");
  wint_t x = 8;
  char *fmt;
  wprintf(fmt, x);

  return 0;
}
