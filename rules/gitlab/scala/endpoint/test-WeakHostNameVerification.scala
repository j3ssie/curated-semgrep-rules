// License: LGPL-3.0 License (c) find-sec-bugs
package endpoint

import javax.net.ssl._
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import java.security.cert.X509Certificate


class WeakHostNameVerification {
  def useAllHosts(): Unit = {
    HttpsURLConnection.setDefaultHostnameVerifier(new AllHosts)
  }

  @throws[NoSuchAlgorithmException]
  @throws[KeyManagementException]
  def useTrustAllManager(): Unit = {
    val trustAllCerts = Array[TrustManager](new TrustAllManager)
    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, null)
    HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory)
  }

  def useSecurityBypasser(): Unit = {
    SecurityBypasser.destroyAllSSLSecurityForTheEntireVMForever()
  }
}

class AllHosts extends HostnameVerifier {
  def verify(hostname: String, session: SSLSession) = true
}

class TrustAllManager extends X509TrustManager {
  @throws[CertificateException]
  def checkClientTrusted(cert: Array[X509Certificate], authType: String): Unit = {
  }

  @throws[CertificateException]
  def checkServerTrusted(cert: Array[X509Certificate], authType: String): Unit = {
  }

  def getAcceptedIssuers(): Array[X509Certificate] = null
}

class TrustAllManagerTwo extends X509TrustManager {
  @throws[CertificateException]
  def checkClientTrusted(cert: Array[X509Certificate], authType: String): Unit = {
  }

  @throws[CertificateException]
  def checkServerTrusted(cert: Array[X509Certificate], authType: String): Unit = {
  }

  def getAcceptedIssuers(): Array[X509Certificate] = {
    Array()
  }
}

object SecurityBypasser {
  def destroyAllSSLSecurityForTheEntireVMForever(): Unit = {
    try {
      val trustAllCerts = Array[TrustManager](new TrustAllManager)
      val sslContext = SSLContext.getInstance("SSL")
      sslContext.init(null, trustAllCerts, null)
      HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory)
      HttpsURLConnection.setDefaultHostnameVerifier(new AllHosts)
    } catch {
      case e: NoSuchAlgorithmException =>
        e.printStackTrace
      case e: KeyManagementException =>
        e.printStackTrace
    }
  }
}

