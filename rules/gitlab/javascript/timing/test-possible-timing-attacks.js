// License: MIT (c) JS Foundation and other contributors, https://js.foundation
// source: https://github.com/nodesecurity/eslint-plugin-security/blob/master/test/detect-possible-timing-attacks.js
// hash: edd1ae2
'use strict';

const RuleTester = require('eslint').RuleTester;
const tester = new RuleTester();

const ruleName = 'detect-possible-timing-attacks';
const Rule = require(`../rules/${ruleName}`);

const valid = 'if (age === 5) {}';
const invalidLeft = 'if (password === \'mypass\') {}';
const invalidRight = 'if (\'mypass\' === password) {}';


// We only check with one string "password" and operator "==="
// to KISS.

tester.run(`${ruleName} (left side)`, Rule, {
  valid: [{ code: valid }],
  invalid: [
    {
      code: invalidLeft,
      errors: [{ message: 'Potential timing attack, left side: true' }]
    }
  ]
});


tester.run(`${ruleName} (right side)`, Rule, {
  valid: [{ code: valid }],
  invalid: [
    {
      code: invalidRight,
      errors: [{ message: 'Potential timing attack, right side: true' }]
    }
  ]
});
