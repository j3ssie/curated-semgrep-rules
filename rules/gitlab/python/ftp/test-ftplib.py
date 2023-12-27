#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/ftplib.py
# hash:  8eee173

from ftplib import FTP

ftp = FTP('ftp.debian.org')
ftp.login()

ftp.cwd('debian')
ftp.retrlines('LIST')

ftp.quit()

