// License: LGPL-3.0 License (c) find-sec-bugs
package cookie

import javax.servlet.ServletException
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponseWrapper
import java.io.IOException


class HttpResponseSplitting extends HttpServlet {

  @throws[ServletException]
  @throws[IOException]
  override protected def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val input = req.getParameter("input")
    val c = new Cookie("name", null)
    c.setValue(input)
    c.setHttpOnly(true)
    c.setSecure(true)
    resp.addCookie(c)
  }

  @throws[ServletException]
  @throws[IOException]
  override protected def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val input = req.getParameter("input")
    val c = new Cookie("name", input)
    c.setHttpOnly(true)
    c.setSecure(true)
    resp.addCookie(c)
  }

  @throws[ServletException]
  @throws[IOException]
  override protected def doDelete(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val data = req.getParameter("input")
    val input = data.replaceAll("\n", "")
    val c = new Cookie("name", input)
    c.setHttpOnly(true)
    c.setSecure(true)
    resp.addCookie(c)
  }

  @throws[ServletException]
  @throws[IOException]
  override protected def doOptions(req: HttpServletRequest, resp: HttpServletResponse): Unit = { // BAD
    val tainted = req.getParameter("input")
    resp.setHeader("test", tainted)
    // OK: False negative but reported by spotbugs
    val data = req.getParameter("input")
    val normalized = data.replaceAll("\n", "\n")
    resp.setHeader("test", normalized)
    val normalized2 = data.replaceAll("\n", req.getParameter("test"))
    resp.setHeader("test2", normalized2)
    // OK
    val normalized3 = org.apache.commons.text.StringEscapeUtils.unescapeJava(tainted)
    resp.setHeader("test3", normalized3)
    val normalized4 = getString(tainted)
    resp.setHeader("test4", normalized4)
    val wrapper = new HttpServletResponseWrapper(resp)
    wrapper.addHeader("test", tainted)
    wrapper.setHeader("test2", tainted)
  }

  private def getString(s: String) = s
}
