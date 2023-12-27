// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/traversal/resolve_path_traversal.js
// hash: e7a0a61
const path = require('path')
const express = require('express')
const app = express()
const port = 3000

app.get('/test1', (req, res) => {
    // ruleid:rules_lgpl_javascript_traversal_rule-join-resolve-path-traversal
    var extractPath = path.join(opts.path, req.query.path);
    extractFile(extractPath);
    res.send('Hello World!');
})

app.post('/test2', function test2(req, res) {
    // ruleid:rules_lgpl_javascript_traversal_rule-join-resolve-path-traversal
    createFile({ filePath: path.resolve(opts.path, req.body) })
    res.send('Hello World!')
})

function testCtrl3(req, res) {
    // ruleid:rules_lgpl_javascript_traversal_rule-join-resolve-path-traversal
    let somePath = req.body.path;
    const pth = path.join(opts.path, somePath);
    extractFile(pth);
    res.send('Hello World!');
}

app.listen(port, () => console.log(`Example app listening at http://localhost:${port}`))
