// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/traversal/express_hbs_lfr.js
// hash: e7a0a61

var express = require('express');
var router = express.Router();

router.get('/', function(req, res, next) {
     res.render('index')
});

router.post('/', function(req, res, next) {
    // ruleid:rules_lgpl_javascript_traversal_rule-express-lfr-warning
    var profile = req.body.profile
     res.render('index', profile)
});


router.post('/good', function(req, res, next) {
     res.render('index', { profile })
});

// Sure shot with hbs
var x = require('hbs')
var express = require('express');
var router = express.Router();

router.get('/', function(req, res, next) {
     res.render('index')
});

router.post('/', function(req, res, next) {
    
    var profile = req.body.profile
     res.render('index', profile)
});

router.post('/good', function(req, res, next) {
     res.render('index', { profile })
});
module.exports = router;
