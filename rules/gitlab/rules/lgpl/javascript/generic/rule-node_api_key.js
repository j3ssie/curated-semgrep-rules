// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/generic/hardcoded_secrets.js
// hash: e7a0a61

password = '1212';
foo = "adasd";
x = 1;
password = x;
password = '';

username = 'ajin-test-user'
x.password = 123
x["password"] = 1;
pass = 123;

PASSWORD = '12211';


obj['password'] = '121233';

obj2.password = '1234';

obj2.pass = '1234';

obj2["pass"] = '1234';


const password = '1212';

let password = '1212';

var password = '1212';

// ruleid:rules_lgpl_javascript_generic_rule-node-api-key
angular.module('starter.services', []).constant('api_key', '6e906986c3b199c51fff3154cfb76979')
this.apiUrl = api_url;

aws('secret', 'asdasdadasddd')
// ruleid:rules_lgpl_javascript_generic_rule-node-api-key
x.config('APIKEY', 'asdsdadsada')
// ruleid:rules_lgpl_javascript_generic_rule-node-api-key
api_key = "213123123123";
// ruleid:rules_lgpl_javascript_generic_rule-node-api-key
api["apikey"] = "asddadasddad"
// ruleid:rules_lgpl_javascript_generic_rule-node-api-key
obj('APIKEY') = 'asdasdsadasdad'
// ruleid:rules_lgpl_javascript_generic_rule-node-api-key
obj('api_key') = 'asdasdsadasdad'
// ruleid:rules_lgpl_javascript_generic_rule-node-api-key
obj('Api_Key') = 'asdasdsadasdad'

secret = "Asdadasdasdda"

obj.secret = "Asdadasdasddsa"
