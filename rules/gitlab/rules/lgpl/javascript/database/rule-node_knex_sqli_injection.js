// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/database/sql_injection_knex.js
// hash: e7a0a61
var knex = require('knex')({
    client: 'mysql',
    connection: {
      host : '127.0.0.1',
      user : 'your_database_user',
      password : 'your_database_password',
      database : 'myapp_test'
    }
  });
  
  app.post('/smth', function (req, res) {
      knex.with('with_alias', knex.raw('select * from "books" where "author" = ?', req.body.input )).select('*').from('with_alias')
      knex.raw('select * from "books" where "author" = ?', req.body.input )
      // ruleid:rules_lgpl_javascript_database_rule-node-knex-sqli-injection
      knex.raw("select * from users where email ='" + req.body.input  + "'")
      // ruleid:rules_lgpl_javascript_database_rule-node-knex-sqli-injection
      knex.raw(`select * from users where email = '${req.body.input }'`)
      // ruleid:rules_lgpl_javascript_database_rule-node-knex-sqli-injection
      knex.whereRaw("email = '" + req.body.input  + "'")
      // ruleid:rules_lgpl_javascript_database_rule-node-knex-sqli-injection
      knex.whereRaw(`email = '${req.body.input }'`)
  });
