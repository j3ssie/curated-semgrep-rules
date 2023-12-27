#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/weak_cryptographic_key_sizes.py
# hash:  8eee173

from cryptography.hazmat.primitives.asymmetric import ec

# Correct
ec.generate_private_key(curve=ec.SECP384R1,
                        backend=backends.default_backend())

# Also correct: without keyword args
ec.generate_private_key(ec.SECP256K1,
                        backends.default_backend())

# Incorrect: weak key sizes
ec.generate_private_key(curve=ec.SECT163R2,
                        backend=backends.default_backend())

# Also incorrect: without keyword args
ec.generate_private_key(ec.SECT163R2,
                        backends.default_backend())



