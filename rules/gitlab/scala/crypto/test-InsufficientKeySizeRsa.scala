// License: LGPL-3.0 License (c) find-sec-bugs

package crypto

import java.security._
import java.security.spec.RSAKeyGenParameterSpec


/**
 * The key size might need to be adjusted in the future.
 * http://en.wikipedia.org/wiki/Key_size#Asymmetric_algorithm_key_lengths
 */
class InsufficientKeySizeRsa {
  @throws[NoSuchAlgorithmException]
  def weakKeySize1 = {
    val keyGen = KeyPairGenerator.getInstance("RSA")
    keyGen.initialize(512) //BAD

    keyGen.generateKeyPair
  }

  @throws[NoSuchAlgorithmException]
  def weakKeySize2 = {
    val keyGen = KeyPairGenerator.getInstance("RSA")
    keyGen.initialize(128, new SecureRandom) //BAD //Different signature

    keyGen.generateKeyPair
  }

  @throws[NoSuchAlgorithmException]
  @throws[InvalidAlgorithmParameterException]
  def weakKeySize3ParameterSpec = {
    val keyGen = KeyPairGenerator.getInstance("RSA")
    keyGen.initialize(new RSAKeyGenParameterSpec(128, RSAKeyGenParameterSpec.F4))
    val key = keyGen.generateKeyPair
    key
  }

  @throws[NoSuchAlgorithmException]
  @throws[InvalidAlgorithmParameterException]
  def weakKeySize4ParameterSpec = {
    val keyGen = KeyPairGenerator.getInstance("RSA")
    keyGen.initialize(new RSAKeyGenParameterSpec(128, RSAKeyGenParameterSpec.F4), new SecureRandom)
    val key = keyGen.generateKeyPair
    key
  }

  @throws[NoSuchAlgorithmException]
  def weakKeySize5Recommended = {
    val keyGen = KeyPairGenerator.getInstance("RSA")
    keyGen.initialize(1024) //BAD with lower priority

    keyGen.generateKeyPair
  }

  @throws[NoSuchAlgorithmException]
  @throws[InvalidAlgorithmParameterException]
  def okKeySizeParameterSpec = {
    val keyGen = KeyPairGenerator.getInstance("RSA")
    keyGen.initialize(new RSAKeyGenParameterSpec(2048, RSAKeyGenParameterSpec.F4)) //Different signature

    keyGen.generateKeyPair
  }

  @throws[NoSuchAlgorithmException]
  @throws[NoSuchProviderException]
  def weakKeySizeWithProviderString = {
    val keyGen = KeyPairGenerator.getInstance("RSA", "BC")
    keyGen.initialize(1024)
    keyGen.generateKeyPair
  }

  @throws[NoSuchAlgorithmException]
  def weakKeySizeWithProviderObject1 = {
    val keyGen = KeyPairGenerator.getInstance("RSA")
    keyGen.initialize(1024)
    keyGen.generateKeyPair
  }

  @throws[NoSuchAlgorithmException]
  def weakKeySizeWithProviderObject2 = {
    val p = new ExampleProvider("info")
    val keyGen = KeyPairGenerator.getInstance("RSA", p)
    keyGen.initialize(1024)
    keyGen.generateKeyPair
  }

  @throws[NoSuchAlgorithmException]
  @throws[NoSuchProviderException]
  def strongKeySizeWithProviderString = {
    val keyGen = KeyPairGenerator.getInstance("RSA", "BC")
    keyGen.initialize(2048) // OK: n >= 2048

    keyGen.generateKeyPair
  }

  private class ExampleProvider(info: String) extends Provider("example", 0.0, info) {
    def this() {
      this("example")
    }
  }
}
