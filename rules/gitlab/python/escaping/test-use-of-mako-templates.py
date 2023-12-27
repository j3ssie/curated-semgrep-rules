# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/mako_templating.py
# hash:  8eee173

from mako.template import Template
import mako

from mako import template

Template("hello")

# XXX(fletcher): for some reason, bandit is missing the one below. keeping it
# in for now so that if it gets fixed inadvertitently we know.
mako.template.Template("hern")
template.Template("hern")
