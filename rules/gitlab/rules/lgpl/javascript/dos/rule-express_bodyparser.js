// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/dos/express_bodyparser_dos.js
// hash: e7a0a61
const express = require('express')
    , cors = require('cors')
    , bodyParser = require('body-parser');

var app = express();

app.configure(function () {
    app.set('port', process.env.PORT || 3000);
    app.set('views', __dirname + '/views');
    app.set('view engine', 'jade');
    app.use(express.favicon());
    app.use(express.logger('dev'));
    // ruleid:rules_lgpl_javascript_dos_rule-express-bodyparser
    app.use(express.bodyParser());
    app.use(cors());
});
