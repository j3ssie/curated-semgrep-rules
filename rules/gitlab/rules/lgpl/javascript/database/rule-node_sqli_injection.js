var mysql = require("mysql");
const pg = require("pg");
const crypto = require("crypto");
const sequelize = require("sequelize");

var username;
var password;

module.exports = function (app) {
  //app.post("/api/role/add", controller.addUserRole);

  app.get("/mysql", (req, res) => {
    var connection = mysql.createConnection({
      host: "localhost",
      user: "user",
      password: "pass",
      database: "technicalkeeda",
      debug: false,
    });
    connection.connect();

    // mysql
    // ruleid:rules_lgpl_javascript_database_rule-node-sqli-injection
    connection.query(
      "SELECT * FROM bank_accounts WHERE dob = '" +
        req.query.dob +
        "' AND bank_account = '" +
        req.query.account_number +
        "'",
      function (error, results) {}
    );

    // mysql
    // ruleid:rules_lgpl_javascript_database_rule-node-sqli-injection
    var employeeId = req.query.id;
    // placing another test annotation here because this line gets matched by multiple patterns.
    // ruleid:rules_lgpl_javascript_database_rule-node-sqli-injection
    var sql = "SELECT * FROM trn_employee WHERE employee_id = " + employeeId;
    connection.query(sql, function (error, results, fields) {
      if (error) {
        throw error;
      }
      console.log(results);
    });

    connection.connect(function (err) {
      // mysql
      // ruleid:rules_lgpl_javascript_database_rule-node-sqli-injection
      connection.query(
        "SELECT * FROM users WHERE id = " + req.query.foo,
        (err, res) => {}
      );
    });

    connection.end();
  });

  app.post("/seq", function (req, res) {
    // sequelize
    // ruleid:rules_lgpl_javascript_database_rule-node-sqli-injection
    var query = "SELECT * FROM person WHERE id = '" + req.body.input + "'";
    sequelize
      .query(query, {
        type: sequelize.QueryTypes.SELECT,
        model: Foo,
      })
      .then(function (foo) {
        res.json({ message: person });
      })
      .catch(function (err) {
        res.json({ message: err.toString() });
      });

    // sequelize
    // ruleid:rules_lgpl_javascript_database_rule-node-sqli-injection
    username = req.body.username;
    // placing another test annotation here because this line gets matched by multiple patterns.
    // ruleid:rules_lgpl_javascript_database_rule-node-sqli-injection
    password = crypto
      .createHash("sha256")
      .update(req.body.password)
      .digest("base64");
    // placing another test annotation here because this line gets matched by multiple patterns.
    // ruleid:rules_lgpl_javascript_database_rule-node-sqli-injection
    query = `SELECT * FROM "Users" WHERE username = '${username}' AND password = '${password}'`;
    sequelize
      .query(query, { type: sequelize.QueryTypes.SELECT })
      .then((data) => {
        res.send(data);
      })
      .catch((err) => {
        res.send(err);
      });

    moreCases(username, password);
  });

  app.post("/pg", function (req, res) {
    const pgcon = new pg.Client({
      host: "host",
      user: "user",
      password: "pass",
      database: "db",
    });

    pgcon.connect();
    // ruleid:rules_lgpl_javascript_database_rule-node-sqli-injection
    var inp = req.body.id;
    pgcon.query("SELECT * FROM users WHERE id = " + inp, (err, res) => {});

    //const pg = require('pg');
    const pool = new pg.Pool(config);

    // ruleid:rules_lgpl_javascript_database_rule-node-sqli-injection
    var query1 =
      "SELECT FOO,BAR FROM TABLE WHERE CAT='" + req.body.foo + "' ORDER BY FOO";
    pool.query(query1, [], function (err, results) {});
  });
};

function moreCases(username, password) {
  // ruleid: rules_lgpl_javascript_database_rule-node-sqli-injection
  sequelize.query(
    `INSERT INTO user (username, password) VALUES('${username}','${password}')`
  );

  // ruleid: rules_lgpl_javascript_database_rule-node-sqli-injection
  var query = `INSERT INTO user (username, password) VALUES('${username}','${password}')`;
  console.log("check password");
  sequelize.query(query);

  const abc = "hardcoded val";
  const def = "hardcoded val";
  // ok: rules_lgpl_javascript_database_rule-node-sqli-injection
  var query = `INSERT INTO user (username, password) VALUES('${abc}','${def}')`;
  console.log("check password");
  sequelize.query(query);

  // ruleid: rules_lgpl_javascript_database_rule-node-sqli-injection
  db.sequelize.query(
    "INSERT INTO user (username, password) VALUES('" +
      username +
      "','" +
      password +
      "')"
  );

  // ruleid: rules_lgpl_javascript_database_rule-node-sqli-injection
  var query =
    "INSERT INTO user (username, password) VALUES('" +
    username +
    "','" +
    password +
    "')";
  console.log("check password");
  sequelize.query(query);

  // ok: rules_lgpl_javascript_database_rule-node-sqli-injection
  sequelize.query(
    "INSERT INTO user (username, password) VALUES('" +
      "username" +
      "','" +
      "password" +
      "')"
  );
}
