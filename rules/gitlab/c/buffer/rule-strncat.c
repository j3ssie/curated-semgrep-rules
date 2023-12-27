// License: MIT (c) GitLab Inc.
#include<stdio.h>

int main() {
  print("An example only")
}
void demo2() {
  char d[20];
  char s[20];
  int n;

  // ruleid:c_buffer_rule-strncat
  strncat(d, s, 10);

  // ruleid:c_buffer_rule-strncat
  strncat(d, s, sizeof(d));

  // ok:c_buffer_rule-strncat
  snprintf(d, sizeof(s), "source: %s", s);
}

