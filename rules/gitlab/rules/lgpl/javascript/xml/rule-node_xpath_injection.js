// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/xml/xpathi_node.js
// hash: e7a0a61
var xpath = require('xpath');
var express = require('express');

var app = express();

app.get('/xpath', function (req, res) {
    // ruleid:rules_lgpl_javascript_xml_rule-node-xpath-injection
    var expr = xpath.parse("//persons/user[name/text()='" + req.param("name") + "']/details/text()");
    // ruleid:rules_lgpl_javascript_xml_rule-node-xpath-injection
    expr = xpath.parse("//persons/user[name/text()='" + req.param.name + "']/details/text()");
    // ruleid:rules_lgpl_javascript_xml_rule-node-xpath-injection
    expr = xpath.parse("//persons/user[name/text()='" + req["name"] + "']/details/text()");
    // ruleid:rules_lgpl_javascript_xml_rule-node-xpath-injection
    var foo = req.param;
    expr = xpath.parse("//persons/user[name/text()='" + foo + "']/details/text()");
    //do not match
    expr = JSON.parse("{'foo':" + req.param + "}");
    res.redirect('/home')
});
