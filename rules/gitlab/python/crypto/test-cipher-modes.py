#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/cipher-modes.py
# hash:  8eee173

from cryptography.hazmat.primitives.ciphers.modes import CBC
from cryptography.hazmat.primitives.ciphers.modes import ECB


# Insecure mode
mode = ECB(iv)

# Secure cipher and mode
cipher = AES.new(key, blockalgo.MODE_CTR, iv)

# Secure mode
mode = CBC(iv)
