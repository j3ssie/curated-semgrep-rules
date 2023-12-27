// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/crypto/crypto_node.js
// hash: e7a0a61
var key = new Buffer('8CBDEC62EB4DCA778F842B02503011B2', 'hex')
var src = new Buffer('0002123401010100000000000000c631', 'hex')
// ruleid:rules_lgpl_javascript_crypto_rule-node-aes-ecb
cipher = crypto.createCipheriv("aes-128-ecb", key, iv)
cipher.setAutoPadding(false)
result = cipher.update(src).toString('hex');
result += cipher.final().toString('hex');
"result   : " + result


require("crypto")
    .createHash("sha1")
    .update("Man oh man do I love node!")
    .digest("hex");


require("crypto")
    .createHash("md5")
    .update("Man oh man do I love node!")
    .digest("hex");

function encrypt(text) {
    let iv = crypto.randomBytes(IV_LENGTH);
    // ruleid:rules_lgpl_javascript_crypto_rule-node-aes-ecb
    let cipher = crypto.createCipheriv('aes-256-ecb', Buffer.from(ENCRYPTION_KEY), iv);
    // ruleid:rules_lgpl_javascript_crypto_rule-node-aes-ecb
    let cipher = crypto.createCipheriv('aes-192-ecb', Buffer.from(ENCRYPTION_KEY), iv);
    // ruleid:rules_lgpl_javascript_crypto_rule-node-aes-ecb
    let cipher = crypto.createCipheriv('aes-128-ecb', Buffer.from(ENCRYPTION_KEY), iv);

    
    let cipher = crypto.createCipheriv('aes-128-gcm', Buffer.from(ENCRYPTION_KEY), "");
    
    const token = crypto_1.default.createCipheriv("AES-128-GCM", "key", ""); // bad idea to not provide initialization vector
    let encrypted = cipher.update(text);

    encrypted = Buffer.concat([encrypted, cipher.final()]);

    return iv.toString('hex') + ':' + encrypted.toString('hex');
}

function decrypt(text) {
    let textParts = text.split(':');
    let iv = Buffer.from(textParts.shift(), 'hex');
    let encryptedText = Buffer.from(textParts.join(':'), 'hex');
    // ruleid:rules_lgpl_javascript_crypto_rule-node-aes-ecb
    let decipher = crypto.createDecipheriv('aes-128-ecb', Buffer.from(ENCRYPTION_KEY), iv);
    let decrypted = decipher.update(encryptedText);
}


crypto.pseudoRandomBytes(1); // <Buffer 45>
//Math based random insecure

const val = Math.random();


var des = crypto.createCipher('des', key);
