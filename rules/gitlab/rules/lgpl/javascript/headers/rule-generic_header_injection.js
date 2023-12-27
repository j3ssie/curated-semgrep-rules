// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/headers/header_injection.js
// hash: e7a0a61
var server = http.createServer(function (req, res) {
    var bla = 'dsdsd';
    switch (testIndex++) {
        case 0:
            // ruleid:rules_lgpl_javascript_headers_rule-generic-header-injection
            res.writeHead(200, { test: 'foo \r\ninvalid: bar' + req.foo });
            break;
        case 1:
            // ruleid:rules_lgpl_javascript_headers_rule-generic-header-injection
            res.writeHead(200, { test: req.foo + 'foo \ninvalid: bar' });
            break;
        case 2:
            // ruleid:rules_lgpl_javascript_headers_rule-generic-header-injection
            res.writeHead(200, { test: 'foo \rinvalid: bar' + req.foo + 'asdadasd', foo: bar });
            break;
        case 3:
            // ruleid:rules_lgpl_javascript_headers_rule-generic-header-injection
            res.writeHead(200, { test: bla + 'foo \n\n\ninvalid: bar' + req.foo });
            break;
        case 5:
            // ruleid:rules_lgpl_javascript_headers_rule-generic-header-injection
            res.writeHead(200, { test: bla + 'foo \n\n\ninvalid: bar' + req.foo('asd') });
            break;
        case 4:
            // ruleid:rules_lgpl_javascript_headers_rule-generic-header-injection
            res.writeHead(200, { test: req.foo });
            server.close();
            break;
        default:
            assert(false);
    }
    res.end('Hi mars!');
});
server.listen(common.PORT);

var express = require('express');
var app = express();
app.get('/', function (req, res) {
    // ruleid:rules_lgpl_javascript_headers_rule-generic-header-injection
    res.writeHead(200, { test: 'foo \r\ninvalid: bar' + req.foo });

    // ruleid:rules_lgpl_javascript_headers_rule-generic-header-injection
    res.set('Content-Type', req.query.foo);
    // ruleid:rules_lgpl_javascript_headers_rule-generic-header-injection
    res.set('foo', 'asdad' + req.query.foo);
    // ruleid:rules_lgpl_javascript_headers_rule-generic-header-injection
    res.set(req.query.foo, 'asdadad');
    // ruleid:rules_lgpl_javascript_headers_rule-generic-header-injection
    res.set('asda' + req.query.foo, 'asdadad');
    // ruleid:rules_lgpl_javascript_headers_rule-generic-header-injection
    res.set('asda' + req.query["foo"], 'asdadad');
    // ruleid:rules_lgpl_javascript_headers_rule-generic-header-injection
    res.set('asda' + req.query("foo"), 'asdadad');
    // ruleid:rules_lgpl_javascript_headers_rule-generic-header-injection
    res.set({
        'Content-Type': 'text/plain',
        'Content-Length': req.query.foo,
        'ETag': '12345'
    })
    //do not detect
    res.writeHead(200, { tast: ddd })
    res.set(ffff)
});
