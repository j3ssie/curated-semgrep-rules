// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/jwt/jwt_none_algorithm.js
// hash: e7a0a61
// ruleid:rules_lgpl_javascript_jwt_rule-node-jwt-none-algorithm
const jose = require("jose");
const { JWK, JWT } = jose;
const token = JWT.verify('token-here', JWK.None);

function verifyJwt() {
    // ruleid:rules_lgpl_javascript_jwt_rule-node-jwt-none-algorithm
    let jwt = require("jsonwebtoken");
    let secret = 'some-secret';
    jwt.verify('token-here', secret, { algorithms: ['RS256', 'none'] }, function (err, payload) {
        console.log(payload);
    });
}

