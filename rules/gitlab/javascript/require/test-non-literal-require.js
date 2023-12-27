// License: MIT (c) JS Foundation and other contributors, https://js.foundation
// source: https://github.com/nodesecurity/eslint-plugin-security/blob/master/test/detect-non-literal-require.js
// hash: edd1ae2
'use strict';

const RuleTester = require('eslint').RuleTester;
const tester = new RuleTester();

const ruleName = 'detect-non-literal-require';
const invalid = 'var a = require(c)';


tester.run(ruleName, require(`../rules/${ruleName}`), {
  valid: [{ code: 'var a = require(\'b\')' }],
  invalid: [
    {
      code: invalid,
      errors: [{ message: 'Found non-literal argument in require' }]
    }
  ]
});

