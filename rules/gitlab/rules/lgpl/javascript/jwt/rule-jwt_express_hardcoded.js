// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/jwt/jwt_express_hardcoded.js
// hash: e7a0a61
var jwt = require('express-jwt');

// ruleid:rules_lgpl_javascript_jwt_rule-jwt-express-hardcoded
app.get('/protected', jwt({ secret: 'shhhhhhared-secret' }), function (req, res) {
    if (!req.user.admin) return res.sendStatus(401);
    res.sendStatus(200);
});

// ruleid:rules_lgpl_javascript_jwt_rule-jwt-express-hardcoded
let hardcodedSecret = 'shhhhhhared-secret'

// ruleid:rules_lgpl_javascript_jwt_rule-jwt-express-hardcoded
app.get('/protected2', jwt({ secret: hardcodedSecret }), function (req, res) {
    if (!req.user.admin) return res.sendStatus(401);
    res.sendStatus(200);
});

// ruleid:rules_lgpl_javascript_jwt_rule-jwt-express-hardcoded
let secret = "hardcode"
// ruleid:rules_lgpl_javascript_jwt_rule-jwt-express-hardcoded
const opts = Object.assign({ issuer: 'http://issuer' }, { secret })

app.get('/protected3', jwt(opts), function (req, res) {
    if (!req.user.admin) return res.sendStatus(401);
    res.sendStatus(200);
});
