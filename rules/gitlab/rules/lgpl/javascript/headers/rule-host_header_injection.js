// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/headers/host_header_injection.js
// hash: e7a0a61
// https://www.acunetix.com/blog/articles/automated-detection-of-host-header-attacks/
app.get('/', function (req, res) {

    //semgrep string lateral support is pending
    var foo = {
        text: `reset url: https://${req.host}/password_reset/${token}`
    };

    //do not match
    var x = 'https://' + foo
    // do not match
    var x = "https://" + req.foo + "/reset" + foo;
    // do not match
    var x = "https://" + z + "/reset";



    // ruleid:rules_lgpl_javascript_headers_rule-host-header-injection
    var url = 'http://' + req.host;
    // ruleid:rules_lgpl_javascript_headers_rule-host-header-injection
    var reset = 'https://' + req.host + '/password_reset';
    // ruleid:rules_lgpl_javascript_headers_rule-host-header-injection
    var pass = "https://" + req.host + "/reset";

    // ruleid:rules_lgpl_javascript_headers_rule-host-header-injection
    var z = req.host;
    var pass = "https://" + z + "/reset";

    // ruleid:rules_lgpl_javascript_headers_rule-host-header-injection
    var reset_url = "Reset password: <a href='http://" + req.host + "/reset_pass'>Reset</a>";
    // ruleid:rules_lgpl_javascript_headers_rule-host-header-injection
    var foo = {
        text: 'password: https://' + req.host + '/token/',
        token: 'f2131ASDSADASoo',
    };

    // ruleid:rules_lgpl_javascript_headers_rule-host-header-injection
    var foo = {
        text: 'reset password: https://' + req['host'] + '/token/',
        token: 'f2131ASDSADASoo',
    };

    // ruleid:rules_lgpl_javascript_headers_rule-host-header-injection
    let x = "https://" + req['host'] + "/reset" + foo;
    // ruleid:rules_lgpl_javascript_headers_rule-host-header-injection
    x = "https://" + req("host") + "/reset" + foo + 'barr' + foo2;

    // ruleid:rules_lgpl_javascript_headers_rule-host-header-injection
    var foo = {
        text: 'reset password: https://' + req.host + '/resettoken/' + foo,
        token: 'f2131ASDSADASoo',
    };

});
