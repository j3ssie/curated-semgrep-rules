// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/eval/server_side_template_injection.js
// hash: e7a0a61
var handlebars = require('handlebars'),
    fs = require('fs'),
    Sqrl = require('squirrelly');
// do not match
var template = handlebars.compile(source);

app.get('/', function (req, res) {
    var storeName = "console.log(process.pid)" // this should be a user-controlled string
    function getStoreName() {
        return storeName;
    }
    var scope = {
        getStoreName: getStoreName
    }

    fs.readFile('example.html', 'utf-8', function (error, source) {
        // ruleid:rules_lgpl_javascript_eval_rule-server-side-template-injection
        var template = handlebars.compile(source + req.foo);
        // ruleid:rules_lgpl_javascript_eval_rule-server-side-template-injection
        handlebars.compile(source + req.foo.bar);


        var myTemplate = 'Hi, my name is {{name}}'
        // ruleid:rules_lgpl_javascript_eval_rule-server-side-template-injection
        var temp = myTemplate + req.foo['bar']
        var compiled = Sqrl.Compile(temp)


        // ruleid:rules_lgpl_javascript_eval_rule-server-side-template-injection
        var xx = source.replace('<!-->', req.foo)
        handlebars.compile(xx)


        // ruleid:rules_lgpl_javascript_eval_rule-server-side-template-injection
        var x = source + req.foo;
        var z = 2;
        handlebars.compile(x);

        var html = template(data);
        console.log(html)
    });

    //do not match
    var template = handlebars.compile(source);
});
