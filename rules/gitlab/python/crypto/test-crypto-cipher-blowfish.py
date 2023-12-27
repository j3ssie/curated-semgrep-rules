#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/ciphers.py
# hash:  8eee173

from Crypto.Cipher import Blowfish as pycrypto_blowfish
from Cryptodome.Cipher import Blowfish as pycryptodomex_blowfish


iv = Random.new().read(bs)
key = b'An arbitrarily long key'
plaintext = b'docendo discimus '
plen = bs - divmod(len(plaintext),bs)[1]
padding = [plen]*plen
padding = pack('b'*plen, *padding)
bs = pycrypto_blowfish.block_size
cipher = pycrypto_blowfish.new(key, pycrypto_blowfish.MODE_CBC, iv)
msg = iv + cipher.encrypt(plaintext + padding)
bs = pycryptodomex_blowfish.block_size
cipher = pycryptodomex_blowfish.new(key, pycryptodomex_blowfish.MODE_CBC, iv)
msg = iv + cipher.encrypt(plaintext + padding)


