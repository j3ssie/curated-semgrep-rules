// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/eval/eval_vm_injection.js
// hash: e7a0a61
const vm = require('vm')

let ctrl1 = function test1(req, res) {
    
    var input = req.query.something || ''
    var sandbox = {
        foo: input
    }
    vm.createContext(sandbox)
    vm.runInContext('safeEval(orderLinesData)', sandbox, { timeout: 2000 })
    res.send('hello world')
}
app.get('/', ctrl1)

app.get('/', (req, res) => {
    
    var sandbox = {
        foo: req.query.userInput
    }
    vm.createContext(sandbox)
    vm.runInContext('safeEval(orderLinesData)', sandbox, { timeout: 2000 })
    res.send('hello world')
})

var ctrl2 = null;
ctrl2 = function test2(req, res) {
    
    var input = req.query.something || ''
    var sandbox = {
        foo: input
    }
    vm.runInNewContext('safeEval(orderLinesData)', sandbox, { timeout: 2000 })
    res.send('hello world')
}
app.get('/', ctrl2)


app.get('/', function (req, res) {
    
    var sandbox = {
        foo: req.query.userInput
    }
    vm.runInNewContext('safeEval(orderLinesData)', sandbox, { timeout: 2000 })
    res.send('hello world')
})


app.get('/', function (req, res) {
    // ruleid:rules_lgpl_javascript_eval_rule-vm-code-injection
    const code = `
        var x = ${req.query.userInput};
    `
    vm.runInThisContext(code)
    res.send('hello world')
})


app.get('/', function test4(req, res) {
    const parsingContext = vm.createContext({ name: 'world' })
    // ruleid:rules_lgpl_javascript_eval_rule-vm-code-injection
    const code = `return 'hello ' + ${req.query.userInput}`
    let fn = vm.compileFunction(code, [], { parsingContext })
    res.send('hello world')
})


app.get('/', (req, res) => {
    
    const context = vm.createContext({ name: req.query.userInput })
    let code = `return 'hello ' name`
    const fn = vm.compileFunction(code, [], { parsingContext: context })
    res.send('hello world')
})

app.get('/', function (req, res) {
    // ruleid:rules_lgpl_javascript_eval_rule-vm-code-injection
    const script = new vm.Script(`
        function add(a, b) {
          return a + ${req.query.userInput};
        }

        const x = add(1, 2);
    `);

    script.runInThisContext();
    res.send('hello world')
})
