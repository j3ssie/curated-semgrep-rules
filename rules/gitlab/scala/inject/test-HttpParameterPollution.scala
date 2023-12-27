// License: LGPL-3.0 License (c) find-sec-bugs
package inject

import org.apache.commons.httpclient.methods.GetMethod
import org.apache.http.client.methods.HttpGet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import java.net.URLEncoder
import com.google.common.net.UrlEscapers.urlPathSegmentEscaper


class HttpParameterPollution extends HttpServlet {
  override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    try {
      val item = request.getParameter("item")
      //in HttpClient 4.x, there is no GetMethod anymore. Instead there is HttpGet
      val httpget = new HttpGet("http://host.com?param=" + URLEncoder.encode(item)) //OK
      val httpget2 = new HttpGet("http://host.com?param=" + item) //BAD
      val httpget3 = new HttpGet("http://host.com?param=" + urlPathSegmentEscaper().escape(item))
      val get = new GetMethod("http://host.com?param=" + item)
      get.setQueryString("item=" + item) //BAD
    } catch {
      case e: Exception =>
        System.out.println(e)
    }
  }
}
