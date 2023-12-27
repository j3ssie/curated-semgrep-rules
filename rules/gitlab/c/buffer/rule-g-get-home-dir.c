// License: MIT (c) GitLab Inc.
#include <glib.h>
#include <glib/gprintf.h>

int main() {
  char *gchar;

  // ruleid: c_buffer_rule-g-get-home-dir
  gchar = g_get_home_dir(void);

  printf(gchar);

  return 0;
}

