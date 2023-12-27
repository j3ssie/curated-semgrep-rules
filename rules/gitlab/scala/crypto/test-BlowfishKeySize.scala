// License: LGPL-3.0 License (c) find-sec-bugs
package crypto

import javax.crypto.KeyGenerator
import java.security.NoSuchAlgorithmException

class BlowfishKeySize {
  @throws[NoSuchAlgorithmException]
  def danger(): Unit = {
    val keyGen = KeyGenerator.getInstance("Blowfish")
    keyGen.init(64)
  }
}
