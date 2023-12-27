// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/xss/xss_templates.js
// hash: e7a0a61
function name() {
    var x = '<h1>hell0</h1>'
    
    var y = new Handlebars.SafeString(x);
    
    return new Handlebars.SafeString('<img src="" onload=alert(0)>');
}

function test2() {
    var x = 'foooo'
    var z = new Handlebars;
    
    var xx = z.SafeString(x)
    return xx;
}


// ruleid:rules_lgpl_javascript_xss_rule-handlebars-noescape
var template = Handlebars.compile(source, { noEscape: true });
var template = "This is {{target}}";
var target = "user's pictures";
// ruleid:rules_lgpl_javascript_xss_rule-handlebars-noescape
var result = Handlerbars.compile(template, { noEscape: true })({ target: target });

Sqrl.autoEscaping(false)
