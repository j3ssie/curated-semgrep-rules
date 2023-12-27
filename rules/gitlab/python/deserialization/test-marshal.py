#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/marshal_deserialize.py
# hash:  8eee173

import marshal
import tempfile

serialized = marshal.dumps({'a': 1})
print(marshal.loads(serialized))

file_obj = tempfile.TemporaryFile()
marshal.dump(range(5), file_obj)
file_obj.seek(0)
print(marshal.load(file_obj))
file_obj.close()
