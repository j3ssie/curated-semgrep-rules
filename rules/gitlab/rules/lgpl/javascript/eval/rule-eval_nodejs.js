// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/eval/eval_node.js
// hash: e7a0a61
var express = require('express');
var app = express();
app.get('/', function (req, res) {
    // ruleid:rules_lgpl_javascript_eval_rule-eval-nodejs
    var resp = eval("(" + req.query.name + ")");
    // ruleid:rules_lgpl_javascript_eval_rule-eval-nodejs
    var z = new Function('arg1', 'arg2', req.query.name)
    z(1, 2);
    // ruleid:rules_lgpl_javascript_eval_rule-eval-nodejs
    setTimeout('alert(' + req.body.name, 0);
    // ruleid:rules_lgpl_javascript_eval_rule-eval-nodejs
    setInterval(req.body.name, 0);
    res.send('Response</br>');
});
app.listen(8000);
eval("outside_express" + req.foo)
setTimeout('alert(' + req.body.name, 0);
setInterval(req.body.name, 0);
new Function('arg1', 'arg2', req.query.name)
