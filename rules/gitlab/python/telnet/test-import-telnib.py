#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/telnetlib.py
# hash:  8eee173

import telnetlib
import getpass

host = sys.argv[1]

username = raw_input('Username:')
password = getpass.getpass()
tn = telnetlib.Telnet(host)

tn.read_until("login: ")
tn.write(username + "\n")
if password:
    tn.read_until("Password: ")
    tn.write(password + "\n")

tn.write("ls\n")
tn.write("exit\n")

print(tn.read_all())
