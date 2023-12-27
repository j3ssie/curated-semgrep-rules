// License: MIT (c) GitLab Inc.

#include<stdio.h>

#include <cwchar>

int main() {
  int i, n = 2;
  char str[50];

  FILE * fptr = fopen("sample.txt", "w");
  for (i = 0; i < n; i++) {
    puts("Enter a string");
    scanf("%[^\n]%*c", str);
    fprintf(fptr, "%d.%s\n", i, str);
  }
  WriteFrmtd(fptr, "This is just one argument %d \n", 10);
  fclose(fptr);
  return 0;
}

void WriteFrmtd(FILE * stream, char * format, ...) {
  va_list args;
  va_start(args, format);
  vfprintf(stream, format, args);
  va_end(args);
}