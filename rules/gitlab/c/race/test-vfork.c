// License: MIT (c) GitLab Inc.

#include<stdio.h>

#include<unistd.h>

int main(void) {

  printf("First stmt\n");
  vfork();
  printf("Last Stmt\n");

}