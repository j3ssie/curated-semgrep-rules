#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/ciphers.py
# hash:  8eee173

from Crypto.Cipher import XOR as pycrypto_xor
from Cryptodome.Cipher import XOR as pycryptodomex_xor

key = b'Super secret key'
plaintext = b'Encrypt me'
cipher = pycrypto_xor.new(key)
msg = cipher.encrypt(plaintext)
cipher = pycryptodomex_xor.new(key)
msg = cipher.encrypt(plaintext)

