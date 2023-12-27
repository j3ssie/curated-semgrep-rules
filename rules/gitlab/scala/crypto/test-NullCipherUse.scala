// License: LGPL-3.0 License (c) find-sec-bugs
package crypto

import java.io.UnsupportedEncodingException
import java.security.InvalidAlgorithmParameterException
import java.security.KeyException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NullCipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


object NullCipherUse {
  @throws[Exception]
  def main(args: Array[String]): Unit = {
    val pt = "AAAAAAAAAAAAAAAA".getBytes("UTF-8")
    val expectedCihper = Cipher.getInstance("AES/CBC/NoPadding")
    val doNothingCihper = new NullCipher
    printHex(encryptWithCipher(expectedCihper, pt))
    printHex(encryptWithCipher(doNothingCihper, pt))
  }

  @throws[KeyException]
  @throws[InvalidAlgorithmParameterException]
  @throws[IllegalBlockSizeException]
  @throws[BadPaddingException]
  @throws[UnsupportedEncodingException]
  def encryptWithCipher(cipher: Cipher, value: Array[Byte]) = { //Key generation
    val passkey = "BBBBBBBBBBBBBBBB".getBytes("UTF-8")
    val expectedCihper = cipher
    val key = new SecretKeySpec(passkey, "AES")
    //Setting the key
    expectedCihper.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new Array[Byte](expectedCihper.getBlockSize)))
    cipher.doFinal(value)
  }

  private def printHex(resultBytes: Array[Byte]): Unit = {
    for (b <- resultBytes) {
      System.out.print(Integer.toHexString(b & 0xFF))
    }
    System.out.println
  }
}
