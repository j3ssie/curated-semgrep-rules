#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/crypto-md5.py
# hash:  8eee173

from Crypto.Hash import MD2 as pycrypto_md2
from Cryptodome.Hash import MD2 as pycryptodomex_md2

pycrypto_md2.new()
pycryptodomex_md2.new()

