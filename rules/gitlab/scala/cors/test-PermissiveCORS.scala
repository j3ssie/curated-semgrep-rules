// License: LGPL-3.0 License (c) find-sec-bugs
package cors

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException

class PermissiveCORS extends HttpServlet {
  override protected def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    falsePositiveCORS(resp)
    resp.getWriter.print(req.getSession.getAttribute("secret"))
  }

  private def falsePositiveCORS(resp: HttpServletResponse): Unit = {
    resp.addHeader("Access-Control-Allow-Origin", "http://example.com") // OK
  }

  // Overly permissive Cross-domain requests accepted
  def addPermissiveCORS(resp: HttpServletResponse): Unit = {
    resp.addHeader("Access-Control-Allow-Origin", "*") // BAD

  }

  def addPermissiveCORS2(resp: HttpServletResponse): Unit = {
    resp.addHeader("access-control-allow-origin", "*")
  }

  def addWildcardsCORS(resp: HttpServletResponse): Unit = {
    resp.addHeader("Access-Control-Allow-Origin", "*.example.com")
  }

  def addNullCORS(resp: HttpServletResponse): Unit = {
    resp.addHeader("Access-Control-Allow-Origin", "null")
  }

  def setPermissiveCORS(resp: HttpServletResponse): Unit = {
    resp.setHeader("Access-Control-Allow-Origin", "*")
  }

  def setPermissiveCORSWithRequestVariable(resp: HttpServletResponse, req: HttpServletRequest): Unit = {
    resp.setHeader("Access-Control-Allow-Origin", req.getParameter("tainted"))
  }

  def setPermissiveCORSWithRequestVariable2(resp: HttpServletResponse, req: HttpServletRequest): Unit = {
    val header = req.getParameter("tainted")
    resp.addHeader("access-control-allow-origin", header)
  }
}
