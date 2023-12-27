// License: LGPL-3.0 License (c) find-sec-bugs
package crypto

import java.security.NoSuchAlgorithmException
import org.apache.http.impl.client.DefaultHttpClient
import javax.net.ssl.SSLContext
import java.lang.reflect.Array


object WeakTLSProtocol {
  def main(args: Array): Unit = {
    new DefaultHttpClient // BAD

    try {
      val context1 = SSLContext.getInstance("SSL") // BAD
      val context2 = SSLContext.getInstance("TLS") // OK
    } catch {
      case e: NoSuchAlgorithmException =>
        // TODO Auto-generated catch block
        e.printStackTrace
    }
  }
}
