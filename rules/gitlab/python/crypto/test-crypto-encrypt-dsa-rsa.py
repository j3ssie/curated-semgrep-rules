#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/weak_cryptographic_key_sizes.py
# hash:  8eee173

from cryptography.hazmat import backends
from cryptography.hazmat.primitives.asymmetric import dsa
from cryptography.hazmat.primitives.asymmetric import rsa
from Crypto.PublicKey import DSA as pycrypto_dsa
from Crypto.PublicKey import RSA as pycrypto_rsa
from Cryptodome.PublicKey import DSA as pycryptodomex_dsa
from Cryptodome.PublicKey import RSA as pycryptodomex_rsa


# Correct
dsa.generate_private_key(key_size=2048,
                         backend=backends.default_backend())
rsa.generate_private_key(public_exponent=65537,
                         key_size=2048,
                         backend=backends.default_backend())
pycrypto_dsa.generate(bits=2048)
pycrypto_rsa.generate(bits=2048)
pycryptodomex_dsa.generate(bits=2048)
pycryptodomex_rsa.generate(bits=2048)

# Also correct: without keyword args
dsa.generate_private_key(4096,
                         backends.default_backend())
rsa.generate_private_key(3,
                         4096,
                         backends.default_backend())
pycrypto_dsa.generate(4096)
pycrypto_rsa.generate(4096)
pycryptodomex_dsa.generate(4096)
pycryptodomex_rsa.generate(4096)

# Incorrect: weak key sizes
dsa.generate_private_key(key_size=1024,
                         backend=backends.default_backend())
rsa.generate_private_key(public_exponent=65537,
                         key_size=1024,
                         backend=backends.default_backend())
pycrypto_dsa.generate(bits=1024)
pycrypto_rsa.generate(bits=1024)
pycryptodomex_dsa.generate(bits=1024)
pycryptodomex_rsa.generate(bits=1024)

# Also incorrect: without keyword args
dsa.generate_private_key(512,
                         backends.default_backend())
rsa.generate_private_key(3,
                         512,
                         backends.default_backend())
pycrypto_dsa.generate(512)
pycrypto_rsa.generate(512)
pycryptodomex_dsa.generate(512)
pycryptodomex_rsa.generate(512)

# Don't crash when the size is variable
rsa.generate_private_key(public_exponent=65537,
                         key_size=some_key_size,
                         backend=backends.default_backend())
