// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/ssrf/ssrf_wkhtmltopdf.js
// hash: e7a0a61
const wkhtmltopdf = require('wkhtmltopdf')

wkhtmltopdf(input(), { output: 'vuln.pdf' })

function test(userInput) {
    return wkhtmltopdf(userInput, { output: 'vuln.pdf' })
}


app.get('/', function (req, res) {
    wkhtmltopdf('<html><html/>', { output: 'vuln.pdf' })
    // ruleid:rules_lgpl_javascript_ssrf_rule-wkhtmltopdf-ssrf
    wkhtmltopdf(req.foo, { output: 'vuln.pdf' })

});
