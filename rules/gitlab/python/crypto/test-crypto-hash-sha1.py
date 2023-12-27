#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/crypto-md5.py
# hash:  8eee173

from Crypto.Hash import SHA as pycrypto_sha
from Cryptodome.Hash import SHA as pycryptodomex_sha

pycrypto_sha.new()

pycryptodomex_sha.new()
