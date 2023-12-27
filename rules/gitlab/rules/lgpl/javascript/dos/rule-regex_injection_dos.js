// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/dos/regex_injection.js
// hash: e7a0a61
var express = require('express');
var app = express();

app.get('/search', function (req, res) {
    // ruleid:rules_lgpl_javascript_dos_rule-regex-injection-dos
    var key = req.param("key");
    // Regex created from user input
    var re = new RegExp("\\b" + key);
});
//do not detect this
var re = new RegExp("\\b" + key + "=(.*)\n");
