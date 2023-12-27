// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/headers/header_xss_protection.js
// hash: e7a0a61
const express = require('express');
const lusca = require('lusca');

const app = express();

// ruleid:rules_lgpl_javascript_headers_rule-header-xss-lusca
app.use(lusca({
    csrf: true,
    csp: { policy: "referrer no-referrer" },
    xframe: 'SAMEORIGIN',
    p3p: 'ABCDEF',
    hsts: { maxAge: 31536000, includeSubDomains: true, preload: true },
    xssProtection: false,
    nosniff: true,
    referrerPolicy: 'same-origin'
}));

app.use(lusca.csrf());
app.use(lusca.csp({ policy: [{ "img-src": "'self' http:" }, "block-all-mixed-content"], reportOnly: false }));
app.use(lusca.xframe('SAMEORIGIN'));
app.use(lusca.p3p('ABCDEF'));
app.use(lusca.hsts({ maxAge: 31536000 }));
// ruleid:rules_lgpl_javascript_headers_rule-header-xss-lusca
app.use(lusca.xssProtection(false));
app.use(lusca.nosniff());
app.use(lusca.referrerPolicy('same-origin'));

app.get('/', function (req, res) {
    var x = 0;
    
    res.writeHead(200, { 'x-xss-protection': 0 });

    
    res.set('x-xss-protection', 0);
    //do not match
    res.set('x-xss-protection', 1);
    
    res.set('X-XSS-Protection', 0);
    
    res.set({
        'Content-Length': req.query.foo,
        'x-xss-protection': 0,
        'ETag': '12345'
    })
    
    res.writeHead(200, { 'x-xss-protection': 0 })
    
    res.set('X-XSS-Protection', x);

    // do not detect
    res.set(ffff)
});
