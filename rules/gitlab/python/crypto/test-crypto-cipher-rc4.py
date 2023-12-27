#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/ciphers.py
# hash:  8eee173

from Crypto.Cipher import ARC4 as pycrypto_arc4
from Cryptodome.Cipher import ARC4 as pycryptodomex_arc4
from Crypto.Hash import SHA
from Crypto import Random

key = b'Very long and confidential key'
nonce = Random.new().read(16)
tempkey = SHA.new(key+nonce).digest()
cipher = pycrypto_arc4.new(tempkey)
msg = nonce + cipher.encrypt(b'Open the pod bay doors, HAL')
cipher = pycryptodomex_arc4.new(tempkey)
msg = nonce + cipher.encrypt(b'Open the pod bay doors, HAL')
