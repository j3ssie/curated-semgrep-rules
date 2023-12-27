// License: MIT (c) GitLab Inc.
#include "stdio.h"

#include "stdlib.h"

#include "string.h"

#include "crypt.h"

enum {
  MAX_LEN = 1024
};

int main(int argc, char * argv[]) {
  char * text, * encrypted, * salt;
  size_t len;
  long lnmax;

  text = malloc(MAX_LEN);

  printf("Input string to be hashed: ");
  if (fgets(text, MAX_LEN, stdin) == NULL)
    exit(EXIT_FAILURE);

  len = strlen(text);
  if (text[len - 1] == '\n')
    text[len - 1] = '\0';

  salt = crypt_gensalt("$2b$", 15, NULL, 0);
  encrypted = crypt(text, salt);

  printf("Encrypted: %s", encrypted);

  free(text);
  exit(EXIT_SUCCESS);
}