// License: MIT (c) JS Foundation and other contributors, https://js.foundation
// source: https://github.com/nodesecurity/eslint-plugin-security/blob/master/test/detect-pseudoRandomBytes.js
// hash: edd1ae2

'use strict';

const RuleTester = require('eslint').RuleTester;
const tester = new RuleTester();

const ruleName = 'detect-pseudoRandomBytes';
const invalid = 'crypto.pseudoRandomBytes';


tester.run(ruleName, require(`../rules/${ruleName}`), {
  valid: [{ code: 'crypto.randomBytes' }],
  invalid: [
    {
      code: invalid,
      errors: [{ message: 'Found crypto.pseudoRandomBytes which does not produce cryptographically strong numbers' }]
    }
  ]
});
