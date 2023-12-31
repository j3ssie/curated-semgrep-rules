// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/exec/exec_os_command.js
// hash: e7a0a61

const { exec, spawn } = require('child_process');


router.post('/ping', (req, res) => {
    // ruleid:rules_lgpl_javascript_exec_rule-generic-os-command-exec
    exec(`${req.body.url}`, (error) => {
        if (error) {
            return res.send('error');
        }
        res.send('pong')
    })

})

router.post('/gzip', (req, res) => {/Users/ajinabraham/Code/njsscan/tests/assets/node_source/semantic_grep/exec/exec_os_command.js
    // ruleid:rules_lgpl_javascript_exec_rule-generic-os-command-exec
    exec(
        'gzip ' + req.query.file_path,
        function (err, data) {
            console.log('err: ', err)
            console.log('data: ', data);
            res.send('done');
        });
})

var child_process = require('child_process');
var x = 1;
app.get('/', function (req, res) {
    // ruleid:rules_lgpl_javascript_exec_rule-generic-os-command-exec
    child_process.exec(
        req.query.file_path,
        function (err, data) {
            console.log('err: ', err)
            console.log('data: ', data);
        });

    // ruleid:rules_lgpl_javascript_exec_rule-generic-os-command-exec
    child_process.exec('gzip' +
        req.query.file_path,
        function (err, data) {
            console.log('err: ', err)
            console.log('data: ', data);
        });

    // ruleid:rules_lgpl_javascript_exec_rule-generic-os-command-exec
    child_process.exec('foobar' +
        req.query.file_path + "asdD",
        function (err, data) {
            console.log('err: ', err)
            console.log('data: ', data);
        });

    // ruleid:rules_lgpl_javascript_exec_rule-generic-os-command-exec
    child_process.exec(
        req.query.file_path + "asdD",
        function (err, data) {
            console.log('err: ', err)
            console.log('data: ', data);
        });

    //Do not detect this
    child_process.exec(
        foo + "asdD",
        function (err, data) {
            console.log('err: ', err)
            console.log('data: ', data);
        });

    // ruleid:rules_lgpl_javascript_exec_rule-generic-os-command-exec
    child_process.execSync(
        req.query.file_path + 'rsync -avAXz --info=progress2 "/src" "/dest"',
        { stdio: 'inherit' });

    res.send('Hello World!')


    // ruleid:rules_lgpl_javascript_exec_rule-generic-os-command-exec
    var foo = req.query.ping;
    var x;
    child_process.exec('ping -c 2 ' + foo, function (err, data) {
        response.end();
    });
})

var foo = '1';
require('child_process').exec(foo + 'info=progress2 "/src" "/dest"');


const router = require('express').Router();
const exe = require('child_process');

router.post('/', function (req, res) {
    // ruleid:rules_lgpl_javascript_exec_rule-generic-os-command-exec
    exe.exec('ls ' + req.body.dir, function (err, data) {
        if (!err) {
            res.json({ message: data });
        } else {
            res.status(500).json({ message: err });
        }
    });
});

module.exports = router;


var http = require("http");
var url = require("url");
var exe = require('child_process');
http.createServer(function (request, response) {
    // ruleid:rules_lgpl_javascript_exec_rule-generic-os-command-exec
    var parsedUrl = url.parse(request.url, true);
    exe.exec('ping -c 2 ' + parsedUrl.query.ping, function (err, data) {
        response.end();
    });

}).listen(8888);

