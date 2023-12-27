// License: MIT (c) GitLab Inc.
#include<stdio.h>

#include<stdlib.h>

#include<unistd.h>

int main() {
  FILE * p;
  char foo;
  char * args[] = {
    "./EXEC",
    NULL
  };
  execvp(args[0], args);
  execv(args[0], args);
  execle(args[0], args);
  execlp(args[0], args);
  execl(args[0], args);
  printf("exec family comes under unistd");

  //popen
  p = popen("ver", "r");

  while ((foo = fgetc(p)) != EOF)
    putchar(foo);
  pclose(p);

  return (0);
}