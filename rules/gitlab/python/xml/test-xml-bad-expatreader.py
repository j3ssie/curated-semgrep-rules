#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/xml_expatreader.py
# hash:  8eee173

import xml.sax.expatreader as bad
import defusedxml.expatreader as good

p = bad.create_parser()
b = good.create_parser()
