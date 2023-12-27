// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/eval/eval_sandbox.js
// hash: e7a0a61
const Sandbox = require('sandbox');
const express = require('express');
const app = express();
const port = 3000;

const cb = () => {
    console.log('ok')
}

app.get('/', (req, res) => res.send('Hello World!'))

app.get('/test1', function (req, res) {
    const s = new Sandbox();
    // ruleid:rules_lgpl_javascript_eval_rule-sandbox-code-injection
    s.run('lol(' + req.query.userInput + ')', cb);
    res.send('Hello world');
})

app.get('/test2', function (req, res) {
    const s = new Sandbox();
    // ruleid:rules_lgpl_javascript_eval_rule-sandbox-code-injection
    var code = 'lol(' + req.query.userInput + ')'
    s.run(code, cb);
    res.send('Hello world');
})

app.get('/test3', function (req, res) {
    const s = new Sandbox();
    // ruleid:rules_lgpl_javascript_eval_rule-sandbox-code-injection
    s.run(`lol(${req.query.userInput})`, cb);
    res.send('Hello world');
})



app.listen(port, () => console.log(`Example app listening at http://localhost:${port}`))
