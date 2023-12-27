#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/crypto-md5.py
# hash:  8eee173

import hashlib

hashlib.md5(1)
hashlib.md5(1).hexdigest()

abc = str.replace(hashlib.md5("1"), "###")

print(hashlib.md5("1"))

