# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/no_host_key_verification.py
# hash:  8eee173

from paramiko import client

ssh_client = client.SSHClient()
ssh_client.set_missing_host_key_policy(client.AutoAddPolicy)
ssh_client.set_missing_host_key_policy(client.WarningPolicy)
