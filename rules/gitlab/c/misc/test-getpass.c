// License: MIT (c) GitLab Inc.
#include <stdio.h>

#include <unistd.h>

int main() {
  char * password;

  password = getpass("Your password: ");
  printf("Your password is '%s'\n", password);

  return (0);
}