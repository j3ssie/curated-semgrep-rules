# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/partial_path_process.py
# hash:  8eee173

from subprocess import Popen as pop

pop('gcc --version', shell=False)
pop('/bin/gcc --version', shell=False)
pop(var, shell=False)

pop(['ls', '-l'], shell=False)
pop(['/bin/ls', '-l'], shell=False)

pop('../ls -l', shell=False)

pop('c:\\hello\\something', shell=False)
pop('c:/hello/something_else', shell=False)
