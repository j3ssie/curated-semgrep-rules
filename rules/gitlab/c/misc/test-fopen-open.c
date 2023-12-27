// License: MIT (c) GitLab Inc.
#include<stdio.h>

#include<fcntl.h>

#include <stdlib.h>

int main() {
  int file = open("somefile.txt", O_RDONLY | O_CREAT);

  FILE * demo;
  demo = fopen("demo_file.txt", "w");

  fprintf(demo, "%s", "Humans are welcome");
  fclose(demo);

  return 0;

}