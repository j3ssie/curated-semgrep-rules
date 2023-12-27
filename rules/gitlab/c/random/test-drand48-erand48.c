// License: MIT (c) GitLab Inc.

#include<stdio.h>

#include <stdlib.h>

#include <time.h>

#include <string.h>

int main() {
  short example[4];
  int i;
  short * seed48(short * );
  char * str = (char * ) malloc(sizeof(char) * 11);
  char * ptr;

  double a = drand48();
  double b = erand48(example);
  long b = jrand48(example);
  void lcong48();
  lcong48(example);
  long d = lrand48();
  long z = mrand48();
  long t = nrand48(example);

  srand(time(0));
  for (int i = 0; i < 2; i++)
    printf(" %d ", rand());

  oldptr = seed48(newseed);
  for (i = 0; i < 10; i++) {
    ptr = (char * ) strfry(str);
    printf("Random numbers are: #%d: %s\n", i, ptr);
  }

}