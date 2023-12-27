// License: MIT (c) GitLab Inc.

#include <malloc.h>

#include<stdio.h>

void main(void) {
  int align = 5;
  int size = 10;
  void * memalign;
  void * ptr;

  ptr = memalign(align, size);
}