#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/ciphers.py
# hash:  8eee173

from Crypto.Cipher import ARC2 as pycrypto_arc2
from Cryptodome.Cipher import ARC2 as pycryptodomex_arc2
from Crypto import Random

key = b'Sixteen byte key'
iv = Random.new().read(pycrypto_arc2.block_size)
cipher = pycrypto_arc2.new(key, pycrypto_arc2.MODE_CFB, iv)
msg = iv + cipher.encrypt(b'Attack at dawn')
cipher = pycryptodomex_arc2.new(key, pycryptodomex_arc2.MODE_CFB, iv)
msg = iv + cipher.encrypt(b'Attack at dawn')

