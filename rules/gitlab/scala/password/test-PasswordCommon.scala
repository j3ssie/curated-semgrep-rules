// License: LGPL-3.0 License (c) find-sec-bugs
// scaffold: dependencies=io.vertx.vertx-core@4.2.6,io.vertx.vertx-web@4.2.6
package password

import com.amazonaws.util.Base64
import org.springframework.beans.factory.annotation.Qualifier

import javax.crypto.spec._
import javax.net.ssl.KeyManagerFactory
import javax.security.auth.callback.PasswordCallback
import javax.security.auth.kerberos.KerberosKey
import javax.security.auth.kerberos.KerberosTicket
import java.io.ByteArrayInputStream
import java.io.FileInputStream
import java.io.IOException
import java.math.BigInteger
import java.net.PasswordAuthentication
import java.nio.charset.StandardCharsets
import java.security.KeyRep
import java.security.KeyStore
import java.security.KeyStore.PasswordProtection
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import java.security.spec._
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util


object PasswordCommon {
  private val KEYSTORE_TYPE = "RSA"
  private val PUBLIC_KEY = Array[Byte](1, 2, 3, 4, 5, 6, 7)
  private val PWD1 = "secret4"
  private val PWD2 = Array('s', 'e', 'c', 'r', 'e', 't', '5')
  private val big = new String("1000000")
  private val keys = Array(1, 2, 3, 4, 5, 6, 7, 8)

  @throws[Exception]
  def bad2(): Unit = {
    val passphrase = "secret2"
    System.out.println("secret2")
    val ks = KeyStore.getInstance("JKS")
    val fs = new FileInputStream("keystore")
    ks.load(fs, passphrase.toCharArray)
  }

  @throws[Exception]
  def bad5a(): Unit = {
    val ks = KeyStore.getInstance("JKS")
    ks.load(new FileInputStream("keystore"), PWD2)
  }

  private def getPassword = {
    val password = new Array[Char](3)
    // some operations to simulate non-constant password
    password(0) = 'x'
    password(1) = 10
    password(2) = ("o" + "z").charAt(1)
    password
  }
}

class PasswordCommon {
  final private val PWD3 = Array('s', 'e', 'c', 'r', 'e', 't', '5')
  final private val pwd5 = null
  final private val pwd6 = new Array[Char](7)
  private val pwd4 = null // not considered hard coded

  @throws[Exception]
  def bad1(): Unit = {
    val passphrase = "secret1".toCharArray
    val ks = KeyStore.getInstance("JKS")
    ks.load(new FileInputStream("keystore"), passphrase)
  }

  @throws[Exception]
  def bad3(): Unit = {
    val passphrase = Array('s', 'e', 'c', 'r', 'e', 't', '3')
    KeyStore.getInstance("JKS").load(new FileInputStream("keystore"), passphrase)
  }

  @throws[Exception]
  def bad4(): Unit = {
    val ks = KeyStore.getInstance("JKS")
    ks.load(new FileInputStream("keystore"), PasswordCommon.PWD1.toCharArray)
  }

  @throws[Exception]
  def bad5b(): Unit = {
    val ks = KeyStore.getInstance("JKS")
    ks.load(new FileInputStream("keystore"), PWD3)
  }

  @throws[Exception]
  def bad6(): Unit = {
    val pwdStr = "secret6"
    val pwd1 = pwdStr.toCharArray
    val ks = KeyStore.getInstance("JKS")
    val pwd2 = pwd1
    ks.load(new FileInputStream("keystore"), pwd2)
  }

  @throws[Exception]
  def bad7(): Unit = {
    val bytes = new Array[Byte](2)
    val pwd = "secret7".toCharArray
    new PBEKeySpec(pwd)
    new PBEKeySpec(pwd, bytes, 1)
    new PBEKeySpec(pwd, bytes, 1, 1)
    val auth = new PasswordAuthentication("user", pwd)
    val callback = new PasswordCallback("str", true)
    callback.setPassword(pwd)
    val protection = new PasswordProtection(pwd)
    val key = new KerberosKey(null, pwd, "alg")
    KeyManagerFactory.getInstance("").init(null, pwd)
  }

  @throws[Exception]
  def bad8a(): Unit = {
    new DESKeySpec(null) // should not be reported

    val key = Array[Byte](1, 2, 3, 4, 5, 6, 7, 8)
    val spec = new DESKeySpec(key)
    val spec2 = new DESedeKeySpec(key)
    val kerberosKey = new KerberosKey(null, key, 0, 0)
    System.out.println(spec.getKey()(0) + kerberosKey.getKeyType)
    new SecretKeySpec(key, "alg")
    new SecretKeySpec(key, 0, 0, "alg")
    new X509EncodedKeySpec(key)
    new PKCS8EncodedKeySpec(key)
    new KeyRep(null, "alg", "format", key)
    new KerberosTicket(null, null, null, key, 0, null, null, null, null, null, null)
  }

  def bad8b(): Unit = {
    val key = "secret8".getBytes
    System.out.println("something")
    new SecretKeySpec(key, "alg")
  }

  // Detects: Spotbugs:DMI_CONSTANT_DB_PASSWORD
  @throws[SQLException]
  def bad9a(): Unit = {
    val pass = "secret9"
    var connection = DriverManager.getConnection("url", "user", PasswordCommon.PWD1)
    System.out.println(connection.getCatalog)
    connection = DriverManager.getConnection("url", "user", pass)
    System.out.println(connection.getCatalog)
  }

  // Detects: Spotbugs:DMI_EMPTY_DB_PASSWORD
  @throws[SQLException]
  def bad9b(): Unit = {
    val connection = DriverManager.getConnection("url", "user", "")
    System.out.println(connection.getCatalog)
  }

  @throws[Exception]
  def bad10(): Unit = {
    val bigInteger = new BigInteger("12345", 5)
    new DSAPrivateKeySpec(bigInteger, null, null, null)
    new DSAPublicKeySpec(bigInteger, null, bigInteger, null) // report once

    new DHPrivateKeySpec(bigInteger, null, null)
    new DHPublicKeySpec(bigInteger, null, null)
    new ECPrivateKeySpec(bigInteger, null)
    new RSAPrivateKeySpec(bigInteger, null)
    new RSAMultiPrimePrivateCrtKeySpec(bigInteger, null, null, null, null, null, null, null, null)
    new RSAPrivateCrtKeySpec(bigInteger, null, null, null, null, null, null, null)
    new RSAPublicKeySpec(bigInteger, null)
  }

  def bad11(): Unit = {
    new DSAPrivateKeySpec(null, null, null, null)
    System.out.println
    new DSAPrivateKeySpec(null, null, null, null)
  }

  @throws[Exception]
  def bad12(): Unit = {
    val key = "secret8".getBytes(StandardCharsets.UTF_8)
    val bigInteger = new BigInteger(key)
    new DSAPrivateKeySpec(bigInteger, null, null, null)
  }

  @throws[Exception]
  def bad13(): Unit = {
    var pwd: String = null
    if (PasswordCommon.PWD2(3) < 'u') { // non-trivial condition
      pwd = "hardcoded"
    }
    if (pwd != null) KeyStore.getInstance("JKS").load( // should be reported
      new FileInputStream("keystore"), pwd.toCharArray)
  }

  @throws[Exception]
  def bad14 = {
    var pwd: String = null
    if (PasswordCommon.PWD2(2) % 2 == 1) pwd = "hardcoded1"
    else { // different constant but still hard coded
      pwd = "hardcoded2"
    }
    DriverManager.getConnection("url", "user", pwd)
  }

  @throws[Exception]
  def bad15(vertx: Nothing): Unit = {
    var pwd: String = null
    if (PasswordCommon.PWD2(2) % 2 == 1) pwd = "hardcoded1"
    else pwd = "hardcoded2"
    io.vertx.ext.web.handler.CSRFHandler.create(vertx, pwd)
  }

  @throws[Exception]
  def good1(): Unit = {
    val ks = KeyStore.getInstance("JKS")
    ks.load(new FileInputStream("keystore"), PasswordCommon.getPassword)
  }

  @throws[Exception]
  def good2(): Unit = {
    val pwd = "uiiii".substring(3) + "oo"
    val pwdArray = pwd.toCharArray
    val ks = KeyStore.getInstance("JKS")
    ks.load(new FileInputStream("keystore"), pwdArray)
  }

  @throws[Exception]
  def good3(): Unit = {
    var key = "hard coded"
    key = new String(PasswordCommon.getPassword) // no longer hard coded

    val message = "can be hard coded"
    val byteStringToEncrypt = message.getBytes(StandardCharsets.UTF_8)
    new SecretKeySpec(key.getBytes, "AES") // should not report

    val newArray = new Array[Byte](1024) // not considered hard coded
    new X509EncodedKeySpec(newArray)
  }

  @throws[KeyStoreException]
  @throws[CertificateException]
  @throws[IOException]
  @throws[NoSuchAlgorithmException]
  def good4(vaultServiceKey: String, @Qualifier("keyStorePassword") pass: Nothing) = {
    val keyStore = KeyStore.getInstance(PasswordCommon.KEYSTORE_TYPE)
    keyStore.load(new ByteArrayInputStream(Base64.decode(vaultServiceKey)), pass)
    keyStore
  }

  @throws[KeyStoreException]
  @throws[CertificateException]
  @throws[IOException]
  @throws[NoSuchAlgorithmException]
  def good5(vaultServiceKey: String, @Qualifier("keyStorePassword") password: Array[Char]) = {
    val keyStore = KeyStore.getInstance(PasswordCommon.KEYSTORE_TYPE)
    keyStore.load(new ByteArrayInputStream(Base64.decode(vaultServiceKey)), password)
    keyStore
  }

  @throws[KeyStoreException]
  @throws[CertificateException]
  @throws[IOException]
  @throws[NoSuchAlgorithmException]
  def bad16(vaultServiceKey: String) = {
    val keyStore = KeyStore.getInstance(PasswordCommon.KEYSTORE_TYPE)
    val pass = "test"
    keyStore.load(new ByteArrayInputStream(Base64.decode(vaultServiceKey)), pass.toCharArray)
    keyStore
  }

  @throws[KeyStoreException]
  @throws[CertificateException]
  @throws[IOException]
  @throws[NoSuchAlgorithmException]
  def bad17(vaultServiceKey: String) = {
    val keyStore = KeyStore.getInstance(PasswordCommon.KEYSTORE_TYPE)
    keyStore.load(new ByteArrayInputStream(Base64.decode(vaultServiceKey)), "test".toCharArray)
    keyStore
  }

  @throws[KeyStoreException]
  @throws[CertificateException]
  @throws[IOException]
  @throws[NoSuchAlgorithmException]
  def bad18(vaultServiceKey: String) = {
    val keyStore = KeyStore.getInstance(PasswordCommon.KEYSTORE_TYPE)
    val pass = "test".toCharArray
    keyStore.load(new ByteArrayInputStream(Base64.decode(vaultServiceKey)), pass)
    keyStore
  }

  private def passwordEquals(pwd: Array[Char], pwd2: Array[Char]): Unit = {
    val PWD8 = "secret4"
    if (pwd.eq(pwd2)) return
    if (PasswordCommon.PWD1.equals("")) return
    if ("password1213".equals(PWD8)) return
    if (PWD8.equals("password1213")) return
    if (pwd.eq(PasswordCommon.PWD2)) return
  }
}
