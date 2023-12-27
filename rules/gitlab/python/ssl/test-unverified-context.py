#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/unverified_context.py hash:  8eee173

import ssl

# Correct
context = ssl.create_default_context()

# Incorrect: unverified context
context = ssl._create_unverified_context()

import ssl

