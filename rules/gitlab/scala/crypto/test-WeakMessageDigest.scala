// License: LGPL-3.0 License (c) find-sec-bugs
package crypto

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.security.Provider
import java.security.Signature


object WeakMessageDigest {
  @throws[NoSuchProviderException]
  @throws[NoSuchAlgorithmException]
  def weakDigestMoreSig(): Unit = {
    MessageDigest.getInstance("MD5", "SUN")
    MessageDigest.getInstance("MD4", "SUN")
    MessageDigest.getInstance("MD2", "SUN")
    MessageDigest.getInstance("MD5")
    MessageDigest.getInstance("MD4")
    MessageDigest.getInstance("MD2")
    MessageDigest.getInstance("MD5", new WeakMessageDigest.ExampleProvider)
    MessageDigest.getInstance("MD4", new WeakMessageDigest.ExampleProvider)
    MessageDigest.getInstance("MD2", new WeakMessageDigest.ExampleProvider)
    MessageDigest.getInstance("SHA", "SUN")
    MessageDigest.getInstance("SHA", new WeakMessageDigest.ExampleProvider)
    MessageDigest.getInstance("SHA1", "SUN")
    MessageDigest.getInstance("SHA1", new WeakMessageDigest.ExampleProvider)
    MessageDigest.getInstance("SHA-1", "SUN")
    MessageDigest.getInstance("SHA-1", new WeakMessageDigest.ExampleProvider)
    MessageDigest.getInstance("sha-384", "SUN") //OK!

    MessageDigest.getInstance("SHA-512", "SUN")
    Signature.getInstance("MD5withRSA")
    Signature.getInstance("MD2withDSA", "X")
    Signature.getInstance("SHA1withRSA", new WeakMessageDigest.ExampleProvider)
    Signature.getInstance("SHA256withRSA") //OK

    Signature.getInstance("uncommon name", "")
  }

  private class ExampleProvider(info: String) extends Provider("example", 0.0, info) {
    def this() {
      this("example")
    }
  }
}
