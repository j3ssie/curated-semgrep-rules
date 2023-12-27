// License: LGPL-3.0 License (c) find-sec-bugs
// source: https://github.com/find-sec-bugs/find-sec-bugs/blob/master/findsecbugs-samples-java/src/test/java/testcode/crypto/RsaNoPadding.java
// hash: a7694d0

package crypto

import javax.crypto.Cipher


/**
 * Code sample taken from : http://cwe.mitre.org/data/definitions/780.html
 */
class RsaNoPadding {
  @throws[Exception]
  def rsaCipherOk(): Unit = {
    Cipher.getInstance("RSA/ECB/OAEPWithMD5AndMGF1Padding")
    Cipher.getInstance("RSA")
    Cipher.getInstance("RSA/ECB/OAEPWithMD5AndMGF1Padding", "BC")
  }

  @throws[Exception]
  def rsaCipherWeak(): Unit = {
    Cipher.getInstance("RSA/NONE/NoPadding")
    Cipher.getInstance("RSA/NONE/NoPadding", "BC")
  }

  @throws[Exception]
  def dataflowCipherWeak(): Unit = {
    val cipher1 = null
    Cipher.getInstance(cipher1)
    val cipher2 = "RSA/NONE/NoPadding"
    Cipher.getInstance(cipher2)
    val cipher3 = null
    Cipher.getInstance(cipher3)
  }
}
