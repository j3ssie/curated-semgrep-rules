#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/pycrypto.py
# hash:  8eee173

import Crypto.Cipher
import Crypto.Hash
import Crypto.IO
import Crypto.Protocol
import Crypto.PublicKey
import Crypto.Random
import Crypto.Signature
import Crypto.Util

from . import CryptoMaterialsCacheEntry

def test_pycrypto():
    key = b'Sixteen byte key'
    iv = Random.new().read(AES.block_size)
    cipher = pycrypto_arc2.new(key, AES.MODE_CFB, iv)
    factory = CryptoMaterialsCacheEntry()


def test_pycrypto():
    key = b'Sixteen byte key'
    iv = Random.new().read(AES.block_size)
    cipher = pycrypto_arc2.new(key, AES.MODE_CFB, iv)
    factory = CryptoMaterialsCacheEntry()
