// License: LGPL-3.0 License (c) find-sec-bugs
package ssrf

import java.io.IOException
import play.api.libs.ws._
import java.net.URISyntaxException

object PlaySSRF {
  @throws[IOException]
  def unsafe(ws: WSClient, url: String, input: String): Unit = {
    ws.url(url + "?query=abc")
    ws.url(url)
    val internal = "https://test.com"
    ws.url(internal + input)
  }

  @throws[IOException]
  @throws[URISyntaxException]
  def safe(ws: WSClient, input: String): Unit = {
    val url = "https://test.com"
    ws.url(url + "?query=abc")
    ws.url(url)
  }
}
