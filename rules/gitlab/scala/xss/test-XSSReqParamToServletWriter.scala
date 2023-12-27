// License: LGPL-3.0 License (c) find-sec-bugs
// scaffold: dependencies=org.owasp.encoder.encoder@1.2.3
package xss

import org.owasp.encoder.Encode
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import java.io.PrintWriter
import java.util

// Also contains vulnerabilities found under ids: XSS_SERVLET,SERVLET_PARAMETER
class XSSReqParamToServletWriter extends HttpServlet {
  @throws[ServletException]
  @throws[IOException]
  protected def danger(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val input1 = req.getParameter("input1")
    resp.getWriter.write(input1) // BAD

  }

  @throws[ServletException]
  @throws[IOException]
  override protected def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val input1 = req.getParameter("input1") // BAD
    val sessionId = req.getRequestedSessionId
    val queryString = req.getQueryString
    val referrer = req.getHeader("Referer") //Should have a higher priority
    if (referrer != null && referrer.startsWith("http://company.ca")) { // Header access
      val host = req.getHeader("Host")
      val referer = req.getHeader("Referer")
      val userAgent = req.getHeader("User-Agent")
    }
    val writer = resp.getWriter
    writer.write(input1)
  }

  @throws[ServletException]
  @throws[IOException]
  protected def danger3(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val input1 = req.getParameter("input1")
    val map = req.getParameterMap
    val vals = req.getParameterValues("input2")
    val names = req.getParameterNames
    val contentType = req.getContentType
    val serverName = req.getServerName
    resp.getWriter.write(input1)
  }

  @throws[ServletException]
  @throws[IOException]
  protected def ok(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val input1 = req.getParameter("input1")
    val writer = resp.getWriter
    writer.write(Encode.forHtml(input1)) // OK
  }

  @throws[ServletException]
  @throws[IOException]
  protected def ok2(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val input1 = req.getParameter("input1")
    val writer = resp.getWriter
    writer.write(Encode.forHtml(input1))
  }
}

