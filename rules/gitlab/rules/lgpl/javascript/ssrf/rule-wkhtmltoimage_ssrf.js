// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/ssrf/ssrf_wkhtmltoimage.js
// hash: e7a0a61
var wkhtmltoimage = require('wkhtmltoimage')

wkhtmltoimage.generate(input(), { output: 'vuln.jpg' })

function test(userInput, bar) {
    wkhtmltoimage.generate(userInput, { output: 'vuln.jpg' })
}


app.get('/', function (req, res) {
    
    // ruleid:rules_lgpl_javascript_ssrf_rule-wkhtmltoimage-ssrf
    wkhtmltoimage.generate(req.foo, { output: 'vuln.jpg' })
});
