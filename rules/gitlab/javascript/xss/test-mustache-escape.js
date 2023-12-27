// License: MIT (c) JS Foundation and other contributors, https://js.foundation
// source: https://github.com/nodesecurity/eslint-plugin-security/blob/master/test/detect-disable-mustache-escape.js
// hash: edd1ae2
'use strict';

const RuleTester = require('eslint').RuleTester;
const tester = new RuleTester();

const ruleName = 'detect-disable-mustache-escape';


tester.run(ruleName, require(`../rules/${ruleName}`), {
  valid: [{ code: 'escapeMarkup = false' }],
  invalid: [
    {
      code: 'a.escapeMarkup = false',
      errors: [{ message: 'Markup escaping disabled.' }]
    }
  ]
});
