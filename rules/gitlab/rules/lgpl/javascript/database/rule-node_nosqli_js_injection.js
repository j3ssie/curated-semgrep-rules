// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/database/nosql_injection.js
// hash: e7a0a61
var MongoClient = require('mongodb').MongoClient;
// mongo js injection https://lockmedown.com/securing-node-js-mongodb-security-injection-attacks/
timelineRouter.route("/api/timeline")
    .get(async function (req, res) {
        try {
            var foo = req.foo.bar;
            const startDate = "01/01/2000";
            // ruleid:rules_lgpl_javascript_database_rule-node-nosqli-js-injection
            const endDate = req.query.end;
            const query = { $where: "this.hidden == false" };

            if (startDate && endDate) {
                query["$where"] = "this.start >= new Date('" + startDate + "') && " +
                    "this.end <= new Date('" + endDate + "') &&" +
                    "this.hidden == false;";
            }

            const TimelineItem = await getTimelineItemModel();
            const timelineItems = await TimelineItem.find(query);
            console.log(colors.yellow(`# of Timeline Items retrieved: ${timelineItems.length}`));
            return res.json({ timelineItems: timelineItems });

        } catch (error) {
            res.status(500).send("There was an error retrieving timeline items.  Please try again later");
        }
    });

// https://nullsweep.com/a-nosql-injection-primer-with-mongo/
// ruleid:rules_lgpl_javascript_database_rule-node-nosqli-js-injection
let username = req.query.username;
var query = { $where: `this.username == '${username}'` }
User.find(query, function (err, users) {
    if (err) {
        // Handle errors
    } else {
        res.render('userlookup', { title: 'User Lookup', users: users });
    }
});

app.post('/foo', function (req, res) {
    var query = {};
    // ruleid:rules_lgpl_javascript_database_rule-node-nosqli-js-injection
    query['$where'] = `this.email == '${req.body.email}'`;
    User.find(query, function (err, data) {
        if (err) {
            res.send(err);
        } else if (data) {
            res.send('User Login Successful');
        } else {
            res.send('Wrong Username Password Combination');
        }
    })
});
