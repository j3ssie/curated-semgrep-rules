#!/usr/bin/env python
# License: MIT (c) GitLab Inc.

import os

os.chmod(key_file, 0o777)
os.chmod('/etc/hosts', 0o777)
os.chmod('/tmp/oh_hai', 0x1ff)
os.chmod('/tmp/oh_hai', 0o007)
