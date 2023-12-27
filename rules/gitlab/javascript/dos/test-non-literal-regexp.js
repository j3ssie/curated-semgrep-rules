// License: MIT (c) GitLab Inc.

function test_positive(someVar, obj) {
  // detect-non-literal-regexp
  r2 = new RegExp(someVar)
  r3 = new RegExp('a' + someVar + 'b')
}

function test_negative(someVar, obj) {
  // detect-non-literal-regexp
  r1 = new RegExp('boom', 'i')
  r1 = new RegExp('yeah')
}

