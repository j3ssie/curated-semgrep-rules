# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/os-spawn.py
# hash:  8eee173

import os

os.spawnl(mode, path)
os.spawnle(mode, path, env)
os.spawnlp(mode, file)
os.spawnlpe(mode, file, env)
os.spawnv(mode, path, args)
os.spawnve(mode, path, args, env)
os.spawnvp(mode, file, args)
os.spawnvpe(mode, file, args, env)

os.execl(path, arg0, arg1)
os.execle(path, arg0, arg1, env)
os.execlp(file, arg0, arg1)
os.execlpe(file, arg0, arg1, env)
os.execv(path, args)
os.execve(path, args, env)
os.execvp(file, args)
os.execvpe(file, args, env)
os.startfile(file, args, env)
