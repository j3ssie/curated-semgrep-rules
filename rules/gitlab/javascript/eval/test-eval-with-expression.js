// License: MIT (c) JS Foundation and other contributors, https://js.foundation

var obj = { x: "foo" },
    key = "x",
    value = eval("obj." + key);

(0, eval)("var a = 0");

var foo = eval;
foo("var a = 0");

this.eval("var a = 0");
