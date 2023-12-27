// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/dos/layer7_object_dos.js
// hash: e7a0a61
const express = require('express');
const router = express.Router()


router.post("/list-users", (req, res) => {
    var obj = req.body.users;
    var someArr = [];

    // Potential DoS if obj.length is large.
    // ruleid:rules_lgpl_javascript_dos_rule-layer7-object-dos
    for (var i = 0; i < obj.length; i++) {
        someArr.push(obj[i]);
    }

});


module.exports = router


app.post("/foo", (req, res) => {
    var obj = req.body;

    var ret = [];

    // Potential DoS if obj.length is large.
    // ruleid:rules_lgpl_javascript_dos_rule-layer7-object-dos
    for (var i = 0; i < obj.length; i++) {
        ret.push(obj[i]);
    }
});
