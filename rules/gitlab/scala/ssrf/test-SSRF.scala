// License: LGPL-3.0 License (c) find-sec-bugs
package ssrf

import java.io.IOException
import java.net._

object SSRF {
  @throws[IOException]
  def testURL(url: String): Unit = {
    new URL(url).openConnection.connect()
    new URL("http://safe.com").openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(url, 8080))).connect()
    new URL(url).openConnection.getInputStream()
    new URL(url).openConnection.getLastModified()
    new URL(url).openStream()
    new URL(url).getContent()
    new URL(url).getContent(new Array(0))
  }

  @throws[IOException]
  @throws[URISyntaxException]
  def testURI(url: String): Unit = {
    new URI(url).toURL.openConnection.connect
  }
}