# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/subprocess_shell.py
# hash:  8eee173

import sys
import subprocess
from subprocess import Popen as pop

def Popen(*args, **kwargs):
    print('hi')

    def __len__(self):
        return 0

# ok: python_exec_rule-subprocess-popen-shell-true
pop('/bin/gcc --version', shell=True)
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen('/bin/ls *', shell=True)
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen('echo %s' % ('this is hardcoded'), shell=True)
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen('echo %s' % 'this is hardcoded', shell=True)
# ruleid: python_exec_rule-subprocess-popen-shell-true
pop('/bin/ls %s' % (sys.argv[1]), shell=True)
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen('echo {}'.format('this is hardcoded'), shell=True)
# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen('echo '+ sys.argv[1], shell=True)
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen('echo ' + 'hi', shell=True)

name = 'David ' + 'Rose'
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen('echo ' + name, shell=True)

cmdd = 'echo ' + 'Alex Rose'
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmdd, shell=True)

# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(['echo', 'Hi!'], shell=False)
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(['echo', 'Hello!'])
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(["/bin/ls",
                 "-l"
                 ])
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(['echo', 'Hi!'], shell='')


command = "/bin/ls" + sys.argv[1]
# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(command, shell=True)

# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen('pwd && echo Hi!', shell=True)


command2 = 'pwd'
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(command2, shell='True')
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(command2, shell='False')
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(command2, shell='None')
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(command2, shell=1)


command6 = sys.argv[2]
# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(command6, shell='True')
# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(command6, shell='False')
# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(command6, shell='None')
# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(command6, shell=1)

command10 = "random"

def Random():
    return 2

cmd = sys.argv[2]
# Popen() creates a new process, which is an object. Objects are truthy. So this is equivalent to shell=True
# todoruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell=Random())
# [True] is a non-empty list. In Python, non-empty lists are always truthy. Therefore, shell=[True] is truthy.
# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell=[True])
# {'IS': 'True'} is a non-empty dictionary. Non-empty dictionaries are truthy in Python. Thus, shell={'IS': 'True'} is truthy.
# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell={'IS': 'True'})
# command10 is a string ('random'). Non-empty strings are truthy in Python. Therefore, shell=command is truthy.
# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell=command10)

# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell=False)
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell=0)
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell=[])
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell={})
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell=None)

input = "/" 
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(['ls', '-l', input])

# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen("echo 'hello'", shell=True)

# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen("ls {} ".format(sys.argv[1]), shell=True)

# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen("ls {} ".format(sys.argv[1]), shell=True, cwd="/")


name = sys.argv[1]
cmd = "echo %s" % name
# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell=True)

address = "hello there"
cmd = "echo %s" % address
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell=True)

cmd = "echo %s" % "hello"
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell=True)

cmd = "echo %s" % ("hello")
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell=True)


user = sys.argv[1]
argument = sys.argv[2]

# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(f"echo {user} {argument}", shell=True)

cmd = f"echo {user} {argument}"
# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell=True)

# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(f"pwd", shell=True)

thedir = sys.argv[1]
# ruleid: python_exec_rule-subprocess-popen-shell-true
result = subprocess.Popen(f'ls -al {thedir}', shell=True)

cmd = f"pwd"
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(cmd, shell=True)

command11 = "echo {}".format(sys.argv[1])
# ruleid: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(command11, shell=True)

def foo(v):
    # ruleid: python_exec_rule-subprocess-popen-shell-true
    subprocess.Popen(v, shell=True)

command12 = "echo {}".format("hello")
# ok: python_exec_rule-subprocess-popen-shell-true
subprocess.Popen(command12, shell=True)
