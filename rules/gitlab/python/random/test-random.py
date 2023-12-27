#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/random_module.py
# hash:  8eee173

import random
import os
import somelib

bad = random.random()
bad = random.randrange()
bad = random.randint()
bad = random.choice()
bad = random.uniform()
bad = random.triangular()

good = os.urandom()
good = random.SystemRandom()

unknown = random()
unknown = somelib.a.random()
