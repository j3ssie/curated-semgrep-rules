// License: LGPL-3.0 License (c) find-sec-bugs
package crypto

import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import java.security.InvalidKeyException
import java.security.Key
import java.security.NoSuchAlgorithmException


class CipherCommon { // Detects: CIPHER_INTEGRITY, PADDING_ORACLE
  @throws[NoSuchPaddingException]
  @throws[NoSuchAlgorithmException]
  @throws[IllegalBlockSizeException]
  @throws[BadPaddingException]
  @throws[InvalidKeyException]
  def noIntegrityAndOraclePaddingAttack(key: Key, plainText: Array[Byte]): Unit = {
    val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
    c.init(Cipher.ENCRYPT_MODE, key)
    val cipherText = c.doFinal(plainText)
  }

  // Detects: CIPHER_INTEGRITY, ECB_MODE
  @throws[NoSuchPaddingException]
  @throws[NoSuchAlgorithmException]
  @throws[IllegalBlockSizeException]
  @throws[BadPaddingException]
  @throws[InvalidKeyException]
  def noIntegrity(key: Key, plainText: Array[Byte]): Unit = {
    val c = Cipher.getInstance("DESede/ECB/PKCS5Padding")
    c.init(Cipher.ENCRYPT_MODE, key)
    val cipherText = c.doFinal(plainText)
  }

  // Detects: CIPHER_INTEGRITY, DES_USAGE
  @throws[NoSuchPaddingException]
  @throws[NoSuchAlgorithmException]
  @throws[IllegalBlockSizeException]
  @throws[BadPaddingException]
  @throws[InvalidKeyException]
  def noIntegrityAndDESUsage(key: Key, plainText: Array[Byte]): Unit = {
    val c = Cipher.getInstance("DES/GCM/PKCS5Padding")
    c.init(Cipher.ENCRYPT_MODE, key)
    val cipherText = c.doFinal(plainText)
  }

  // Detects: CIPHER_INTEGRITY, TDES_USAGE
  @throws[NoSuchPaddingException]
  @throws[NoSuchAlgorithmException]
  @throws[IllegalBlockSizeException]
  @throws[BadPaddingException]
  @throws[InvalidKeyException]
  def noIntegrityAndDESedeUsage(key: Key, plainText: Array[Byte]): Unit = {
    val c = Cipher.getInstance("DESede/CBC/PKCS5Padding")
    c.init(Cipher.ENCRYPT_MODE, key)
    val cipherText = c.doFinal(plainText)
  }
}
