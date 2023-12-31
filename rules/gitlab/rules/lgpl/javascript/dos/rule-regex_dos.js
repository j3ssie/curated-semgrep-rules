// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/dos/regex_dos.js
// hash: e7a0a61
const express = require('express');
const router = express.Router()


router.get("/tstMe", (req, res) => {
    var r = /([a-z]+)+$/;
    // ruleid:rules_lgpl_javascript_dos_rule-regex-dos
    let match = r.test(req.params.id);
});


module.exports = router

var http = require("http");
var url = require("url");
http.createServer(function (request, response) {

    var myArray = /d(b+)d/g.exec('cdbbdbsbz');
    var emailExpression = /^([a-zA-Z0-9_\.\-])+\@/ + foo + /(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;


    // ruleid:rules_lgpl_javascript_dos_rule-regex-dos
    var parsedUrl = url.parse(request.url, true);
    console.timeEnd('benchmark');
    /^(([a-z])+.)+[A-Z]([a-z])+$/.test(parsedUrl.query);

    /^(([a-z])+.)+[A-Z]([a-z])+$/.test('a'.repeat(100));
    console.timeEnd('benchmark');
    let match = r.test(req.params.id);
    // ruleid:rules_lgpl_javascript_dos_rule-regex-dos
    /^(([a-z])+.)+[A-Z]([a-z])+$/.test(request.foo);
    console.timeEnd('benchmark');
    // ruleid:rules_lgpl_javascript_dos_rule-regex-dos
    var y = /^(([a-z])+.)+[A-Z]([a-z])+$/.test(request.foo['bar']);
    console.timeEnd('benchmark');
    console.time('benchmark');
    var x = /^(([a-z])+.)+[A-Z]([a-z])+$/.test('a'.repeat(100));
    console.timeEnd('benchmark');

    // ruleid:rules_lgpl_javascript_dos_rule-regex-dos
    var myArray = /d(b+)d/g.exec(request.foo.bar);
    response.end();


    // ruleid:rules_lgpl_javascript_dos_rule-regex-dos
    var re = /(?:\d{3}|\(\d{3}\))([-\/\.])\d{3}\1\d{4}/;
    var OK = re.exec(request.value);
    if (!OK) {
        console.error(request.value + ' isn\'t a phone number with area code!');
    } else {
        console.log('Thanks, your phone number is ' + OK[0]);
    }

}).listen(8888);


