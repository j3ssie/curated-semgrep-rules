#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/crypto-md5.py
# hash:  8eee173

from Crypto.Hash import MD5 as pycrypto_md5
from Cryptodome.Hash import MD5 as pycryptodomex_md5

pycrypto_md5.new()

pycryptodomex_md5.new()
