// License: LGPL-3.0 License (c) find-sec-bugs
package cookie

import javax.servlet.http.HttpServletRequest


class TrustBoundaryViolation { //Taint input
  def setSessionAttributeNameTainted(req: HttpServletRequest): Unit = {
    val input = req.getParameter("input")
    req.getSession.setAttribute(input, "true")
  }

  def setSessionAttributeValueTainted(req: HttpServletRequest): Unit = {
    val input = req.getParameter("input")
    req.getSession.setAttribute("user", input)
  }

  //Unknown source
  def setSessionAttributeNameUnknownSource(req: HttpServletRequest, input: String): Unit = {
    req.getSession.setAttribute(input, "true")
  }

  def setSessionAttributeValueUnknownSource(req: HttpServletRequest, input: String): Unit = {
    req.getSession.setAttribute("user", input) //Reported as low
  }

  //Legacy api
  def setSessionAttributeNameUnknownSourceLegacy(req: HttpServletRequest, input: String): Unit = {
    req.getSession.putValue(input, "true")
  }

  def setSessionAttributeValueUnknownSourceLegacy(req: HttpServletRequest, input: String): Unit = {
    req.getSession.putValue("user", input)
  }

  //Safe
  def setSessionAttributeSafe(req: HttpServletRequest, input: String): Unit = {
    if ("enable".equals(input)) req.getSession.setAttribute("user", "true")
    else req.getSession.setAttribute("user", "false")
  }
}
