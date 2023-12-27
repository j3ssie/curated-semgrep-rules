# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/paramiko_injection.py
# hash:  8eee173

import paramiko

python/exec/test-paramiko_calls.py

client = paramiko.client.SSHClient()
# this is not safe
client.exec_command('something; really; unsafe')
# this is safe
client.connect('somehost')
