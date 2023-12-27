#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/xml_minidom.py
# hash:  8eee173

from xml.dom.minidom import parseString as badParseString
from defusedxml.minidom import parseString as goodParseString

a = badParseString("<myxml>Some data some more data</myxml>")
print(a)
b = goodParseString("<myxml>Some data some more data</myxml>")
print(b)


from xml.dom.minidom import parse as badParse
from defusedxml.minidom import parse as goodParse
a = badParse("somfilethatdoesntexist.xml")
print(a)
b = goodParse("somefilethatdoesntexist.xml")
print(b)
