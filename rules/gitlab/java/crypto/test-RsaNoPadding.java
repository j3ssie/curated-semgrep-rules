// License: LGPL-3.0 License (c) find-sec-bugs
// source: https://github.com/find-sec-bugs/find-sec-bugs/blob/master/findsecbugs-samples-java/src/test/java/testcode/crypto/RsaNoPadding.java
// hash: a7694d0

package crypto;

import javax.crypto.Cipher;

/**
 * Code sample taken from : http://cwe.mitre.org/data/definitions/780.html
 */
public class RsaNoPadding {

    public void rsaCipherOk() throws Exception {
        // ok: java_crypto_rule-RsaNoPadding
        Cipher.getInstance("RSA/ECB/OAEPWithMD5AndMGF1Padding");
        // ok: java_crypto_rule-RsaNoPadding
        Cipher.getInstance("RSA");
        // ok: java_crypto_rule-RsaNoPadding
        Cipher.getInstance("RSA/ECB/OAEPWithMD5AndMGF1Padding", "BC");
        // ok: java_crypto_rule-RsaNoPadding
        Cipher.getInstance("AES/GCM/NoPadding");
    }

    public void rsaCipherWeak() throws Exception {
        // ruleid: java_crypto_rule-RsaNoPadding
        Cipher.getInstance("RSA/NONE/NoPadding");
        // ruleid: java_crypto_rule-RsaNoPadding
        Cipher.getInstance("RSA/NONE/NoPadding", "BC");
        // ruleid: java_crypto_rule-RsaNoPadding
        Cipher.getInstance("RSA/ECB/NoPadding");
    }

    public void dataflowCipherWeak() throws Exception {
        // ok: java_crypto_rule-RsaNoPadding
        String cipher1 = null;
        Cipher.getInstance(cipher1);
        // ruleid: java_crypto_rule-RsaNoPadding
        String cipher2 = "RSA/NONE/NoPadding";
        Cipher.getInstance(cipher2);
         // ruleid: java_crypto_rule-RsaNoPadding
        String cipher3 = "RSA/ECB/NoPadding";
        Cipher.getInstance(cipher3);
    }
}
