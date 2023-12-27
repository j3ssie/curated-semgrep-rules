// License: LGPL-3.0 License (c) find-sec-bugs
package crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class CipherCommon {

    // Detects: CIPHER_INTEGRITY, PADDING_ORACLE
    public void noIntegrityAndOraclePaddingAttack(Key key, byte[] plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = c.doFinal(plainText);
    }

    // Detects: CIPHER_INTEGRITY, ECB_MODE
    public void noIntegrity(Key key, byte[] plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher c = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = c.doFinal(plainText);
    }

    // Detects: CIPHER_INTEGRITY, DES_USAGE
    public void noIntegrityAndDESUsage(Key key, byte[] plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher c = Cipher.getInstance("DES/GCM/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = c.doFinal(plainText);
    }

    // Detects: CIPHER_INTEGRITY, TDES_USAGE
    public void noIntegrityAndDESedeUsage(Key key, byte[] plainText) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = c.doFinal(plainText);
    }
}
