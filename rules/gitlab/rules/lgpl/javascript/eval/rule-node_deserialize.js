// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/eval/eval_deserialize.js
// hash: e7a0a61
var express = require('express');
var cookieParser = require('cookie-parser');
var escape = require('escape-html');
var serialize = require('node-serialize');
const serialize2 = require('serialize-to-js')


var app = express();
app.use(cookieParser())

app.get('/', function (req, res) {
    if (req.cookies.profile) {
        var str = new Buffer(req.cookies.profile, 'base64').toString();
        // ruleid:rules_lgpl_javascript_eval_rule-node-deserialize
        var obj = serialize.unserialize(str);
        
        serialize2.deserialize(str);
        if (obj.username) {
            res.send("Hello " + escape(obj.username));
        }
    } else {
        res.cookie('profile', "eyJ1c2VybmFtZSI6ImFqaW4iLCJjb3VudHJ5IjoiaW5kaWEiLCJjaXR5IjoiYmFuZ2Fsb3JlIn0=", {
            maxAge: 900000,
            httpOnly: true
        });
    }
    res.send("Hello World");
});
app.listen(3000);

require('serialize-to-js').deserialize(str);
