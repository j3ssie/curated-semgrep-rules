#!/usr/bin/env python
# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/flask_debug.py
# hash:  8eee173

from flask import Flask

app = Flask(__name__)

@app.route('/')
def main():
    raise

#bad
app.run(debug=True)

#okay
app.run()
app.run(debug=False)

#unrelated
run()
run(debug=True)
run(debug)
