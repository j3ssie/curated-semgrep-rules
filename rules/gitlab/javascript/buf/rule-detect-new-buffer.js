// License: MIT (c) JS Foundation and other contributors, https://js.foundation
// source: https://github.com/eslint-community/eslint-plugin-security/blob/main/test/detect-new-buffer.js
// hash: edd1ae2

"use strict";
import { Buffer } from "node:buffer";

function test(param) {
  // ok:javascript_buf_rule-detect-new-buffer
  const good1 = new Buffer("7468697320697320612074c3a97374", "hex");

  // ok:javascript_buf_rule-detect-new-buffer
  let good2 = new Buffer([0x62, 0x75, 0x66, 0x66, 0x65, 0x72]);

  // ok:javascript_buf_rule-detect-new-buffer
  var good3 = new Buffer(10);

  // ok:javascript_buf_rule-detect-new-buffer
  const good4 = new Buffer(good3);

  // ruleid:javascript_buf_rule-detect-new-buffer
  const bad = new Buffer(param);
}
