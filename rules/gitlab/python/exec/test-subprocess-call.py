# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/subprocess_shell.py
# hash:  8eee173

import subprocess
from subprocess import Popen as pop

def Popen(*args, **kwargs):
    print('hi')

    def __len__(self):
        return 0

# safe
pop('/bin/gcc --version', shell=True)
Popen('/bin/gcc --version', shell=True)

subprocess.Popen('/bin/gcc --version', shell=True)
subprocess.Popen(['/bin/gcc', '--version'], shell=False)
subprocess.Popen(['/bin/gcc', '--version'])

subprocess.call(["/bin/ls",
                 "-l"
                 ])
subprocess.call('/bin/ls -l', shell=True)

subprocess.check_call(['/bin/ls', '-l'], shell=False)
subprocess.check_call('/bin/ls -l', shell=True)

subprocess.check_output(['/bin/ls', '-l'])
subprocess.check_output('/bin/ls -l', shell=True)

subprocess.run(['/bin/ls', '-l'])
subprocess.run('/bin/ls -l', shell=True)

subprocess.Popen('/bin/ls *', shell=True)
subprocess.Popen('/bin/ls %s' % ('something'), shell=True)
subprocess.Popen('/bin/ls {}'.format('something'), shell=True)

command = "/bin/ls" + unknown_function()
subprocess.Popen(command, shell=True)

subprocess.Popen('/bin/ls && cat /etc/passwd', shell=True)

constant_command = 'pwd'
subprocess.call(constant_command, shell=True)
subprocess.call(constant_command, shell=False)

# unsafe
user_command = input()
subprocess.run([user_command])
subprocess.run(user_command)
subprocess.run(user_command, shell=True)
