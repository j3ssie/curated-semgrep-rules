#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/pickle_deserialize.py
# hash:  8eee173

import pickle
import StringIO

# pickle
pick = pickle.dumps({'a': 'b', 'c': 'd'})
print(pickle.loads(pick))

file_obj = StringIO.StringIO()
pickle.dump([1, 2, '3'], file_obj)
file_obj.seek(0)
print(pickle.load(file_obj))

file_obj.seek(0)
print(pickle.Unpickler(file_obj).load())

