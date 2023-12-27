// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/headers/header_helmet_disabled.js
// hash: e7a0a61
// ruleid:rules_lgpl_javascript_headers_rule-helmet-feature-disabled
app.use(helmet({
    frameguard: false,
}))


// ruleid:rules_lgpl_javascript_headers_rule-helmet-feature-disabled
app.use(helmet({
    "xssFilter": false
}))
