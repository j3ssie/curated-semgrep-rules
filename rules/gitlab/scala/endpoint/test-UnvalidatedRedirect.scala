// License: LGPL-3.0 License (c) find-sec-bugs
package endpoint

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException


class UnvalidatedRedirect extends HttpServlet {

  @throws[ServletException]
  @throws[IOException]
  override protected def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val url = req.getParameter("urlRedirect")
    unvalidatedRedirect1(resp, url)
  }

  @throws[IOException]
  private def unvalidatedRedirect1(resp: HttpServletResponse, url: String): Unit = {
    if (url != null) resp.sendRedirect(url)
  }

  def unvalidatedRedirect2(resp: HttpServletResponse, url: String): Unit = {
    if (url != null) resp.addHeader("Location", url)
  }

  ///The following cases are safe for sure
  @throws[IOException]
  def falsePositiveRedirect1(resp: HttpServletResponse): Unit = {
    val url = "/Home"
    if (url != null) resp.sendRedirect(url)
  }

  def falsePositiveRedirect2(resp: HttpServletResponse): Unit = {
    resp.addHeader("Location", "/login.jsp")
  }
}
