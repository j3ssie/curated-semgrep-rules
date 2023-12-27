// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/generic/hardcoded_secrets.js
// hash: e7a0a61
// ruleid:rules_lgpl_javascript_generic_rule-node-password
password = '1212';
foo = "adasd";
x = 1;
password = x;
password = '';

username = 'ajin-test-user'
x.password = 123
x["password"] = 1;
pass = 123;
// ruleid:rules_lgpl_javascript_generic_rule-node-password
PASSWORD = '12211';

// ruleid:rules_lgpl_javascript_generic_rule-node-password
obj['password'] = '121233';
// ruleid:rules_lgpl_javascript_generic_rule-node-password
obj2.password = '1234';
// ruleid:rules_lgpl_javascript_generic_rule-node-password
obj2.pass = '1234';
// ruleid:rules_lgpl_javascript_generic_rule-node-password
obj2["pass"] = '1234';

// ruleid:rules_lgpl_javascript_generic_rule-node-password
const password = '1212';
// ruleid:rules_lgpl_javascript_generic_rule-node-password
let password = '1212';
// ruleid:rules_lgpl_javascript_generic_rule-node-password
var password = '1212';


angular.module('starter.services', []).constant('api_key', '6e906986c3b199c51fff3154cfb76979')
this.apiUrl = api_url;

aws('secret', 'asdasdadasddd')

x.config('APIKEY', 'asdsdadsada')

api_key = "213123123123";

api["apikey"] = "asddadasddad"

obj('APIKEY') = 'asdasdsadasdad'

obj('api_key') = 'asdasdsadasdad'

obj('Api_Key') = 'asdasdsadasdad'

secret = "Asdadasdasdda"

obj.secret = "Asdadasdasddsa"
