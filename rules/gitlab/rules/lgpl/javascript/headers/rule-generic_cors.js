// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/headers/header_cors_star.js
// hash: e7a0a61
const express = require('express');

const app = express();

// ruleid:rules_lgpl_javascript_headers_rule-generic-cors
app.options('*', cors())
app.get('/', function (req, res) {

    res.set(ffff)
});

app.get('/', function (req, res) {
    var y = 1;
    var x = '*';
    
    res.writeHead(200, { 'Access-Control-Allow-Origin': '*' });
    
    res.set('access-control-allow-origin', '*');
    //do not match - sgrep bug -rewrite-rule
    res.set('Access-Control-Allow-Origin', 'google.com');
    
    res.set('Access-Control-Allow-Origin', '*');
    
    res.set({
        'Content-Length': 123,
        'access-control-allow-origin': '*',
        'ETag': '12345'
    })
    
    res.writeHead(200, { 'Access-Control-Allow-Origin': '*' })
    
    res.set('access-control-allow-origin', x);

    // do not detect - sgrep bug
    res.set('access-control-allow-origin', 'xyz.com');
    //do not detect - sgrep bug
    res.set('access-control-allow-origin', null);

});
