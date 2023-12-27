// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/database/nosql_find_injection.js
// hash: e7a0a61


app.post('/smth', function (req, res) {
    var query = {};
    // ruleid:rules_lgpl_javascript_database_rule-node-nosqli-injection
    query['email'] = req.body.email;
    User.findOne(query, function (err, data) {
        if (err) {
            res.send(err);
        } else if (data) {
            res.send('User Login Successful');
        } else {
            res.send('Wrong Username Password Combination');
        }
    })
});

app.post('/login', function (req, res) {
    // ruleid:rules_lgpl_javascript_database_rule-node-nosqli-injection
    User.findOne({ 'email': req.body.email, 'password': req.body.password }, function (err, data) {
        if (err) {
            res.send(err);
        } else if (data) {
            res.send('User Login Successful');
        } else {
            res.send('Wrong Username Password Combination');
        }
    })
});


app.post('/login', function (req, res) {
    // ruleid:rules_lgpl_javascript_database_rule-node-nosqli-injection
    x = req.body.email
    // ruleid:rules_lgpl_javascript_database_rule-node-nosqli-injection
    User.findOne({ 'email': x, 'password': req.body.password }, function (err, data) {
        if (err) {
            res.send(err);
        } else if (data) {
            res.send('User Login Successful');
        } else {
            res.send('Wrong Username Password Combination');
        }
    })
});

app.post('/login', function (req, res) {
    // ruleid:rules_lgpl_javascript_database_rule-node-nosqli-injection
    x = { 'email': req.body.email, 'password': req.body.password }
    User.findOne(x, function (err, data) {
        if (err) {
            res.send(err);
        } else if (data) {
            res.send('User Login Successful');
        } else {
            res.send('Wrong Username Password Combination');
        }
    })
});
