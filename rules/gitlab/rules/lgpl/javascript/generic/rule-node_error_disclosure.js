// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/generic/error_disclosure.js
// hash: e7a0a61
app.get('/', function (req, res) {
    try {
        foo;
    }
    catch (err) {
        res.statusCode = 500;
        res.setHeader("Content-Type", "text/plain");
        // ruleid:rules_lgpl_javascript_generic_rule-node-error-disclosure
        res.end(err.stack);
        return;
    }
});



try {
    throw new Error("Something unexpected has occurred.");
} catch (e) {
    console.error(e);
}


console.trace("baad")
