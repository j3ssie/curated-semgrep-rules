#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/ciphers.py
# hash:  8eee173

from Crypto.Cipher import DES as pycrypto_des
from Cryptodome.Cipher import DES as pycryptodomex_des
from Crypto import Random
from Crypto.Util import Counter

key = b'-8B key-'
plaintext = b'We are no longer the knights who say ni!'
nonce = Random.new().read(pycrypto_des.block_size/2)
ctr = Counter.new(pycrypto_des.block_size*8/2, prefix=nonce)
cipher = pycrypto_des.new(key, pycrypto_des.MODE_CTR, counter=ctr)
msg = nonce + cipher.encrypt(plaintext)
nonce = Random.new().read(pycryptodomex_des.block_size/2)
ctr = Counter.new(pycryptodomex_des.block_size*8/2, prefix=nonce)
cipher = pycryptodomex_des.new(key, pycryptodomex_des.MODE_CTR, counter=ctr)
msg = nonce + cipher.encrypt(plaintext)

