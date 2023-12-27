// License: LGPL-3.0 License (c) find-sec-bugs
// source: https://github.com/find-sec-bugs/find-sec-bugs/blob/master/findsecbugs-samples-java/src/test/java/testcode/endpoint/BasicHttpServlet.java
// hash: a7694d0

package endpoint

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import java.util


class InsecureServlet extends HttpServlet {

  @throws[ServletException]
  @throws[IOException]
  override protected def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    useParameters(req)
    resp.getWriter.print("<!--" + req.getContentType + "-->")
    resp.getWriter.print("<h1>Welcome to " + req.getServerName)
    val sqlQuery = "UPDATE sessions(last_visit) VALUES(now()) WHERE where sid = '" + req.getRequestedSessionId + "')"
    resp.getWriter.print("<!--" + req.getQueryString + "-->")
    val referrer = req.getHeader("Referer") //Should have a higher priority
    if (referrer != null && referrer.startsWith("http://company.ca")) {
      req.getHeader("Host")
      req.getHeader("User-Agent")
      req.getHeader("X-Requested-With")
    }
  }

  private def useParameters(req: HttpServletRequest): Unit = {
    val username = req.getParameter("username").asInstanceOf[String]
    val roles = req.getParameterValues("roles").asInstanceOf[Array[String]]
    val price = req.getParameterMap.get("hidden_price_value")
    val parameters = req.getParameterNames
    var isAdmin = false
    while ( {
      parameters.hasMoreElements
    }) if (parameters.nextElement.equals("admin_mode")) {
      isAdmin = true
    }
  }
}
