// License: MIT (c) GitLab Inc.
#include <glib.h>
#include <glib/gprintf.h>

int main() {
  char *gchar;

  // ruleid: c_buffer_rule-g-get-tmp-dir
  gchar = g_get_tmp_dir(void);

  printf(gchar);

  return 0;
}

