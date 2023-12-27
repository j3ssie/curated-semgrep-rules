// License: LGPL-3.0 License (c) find-sec-bugs
// source: https://github.com/find-sec-bugs/find-sec-bugs/blob/master/findsecbugs-samples-java/src/test/java/testcode/file/FileDisclosure.java
// hash: a7694d0
package inject

import java.io.IOException
import org.apache.struts.action.ActionForward
import org.springframework.web.servlet.ModelAndView
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.util


// REQUESTDISPATCHER_FILE_DISCLOSURE
class FileDisclosure extends HttpServlet {
  @throws[IOException]
  override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    try {
      val returnURL = request.getParameter("returnURL")
      /** ****Struts ActionForward vulnerable code tests***** */
      val forward = new ActionForward(returnURL) //BAD
      val forward2 = new ActionForward(returnURL, true)
      val forward3 = new ActionForward("name", returnURL, true)
      val forward4 = new ActionForward("name", returnURL, true)
      val forward5 = new ActionForward
      forward5.setPath(returnURL) //BAD

      //false positive test - returnURL moved from path to name (safe argument)
      val forward6 = new ActionForward(returnURL, "path", true) //OK
      /** ****Spring ModelAndView vulnerable code tests***** */
      val mv = new ModelAndView(returnURL)
      val mv4 = new ModelAndView
      mv4.setViewName(returnURL)
      //false positive test - returnURL moved from viewName to modelName (safe argument)
    } catch {
      case e: Exception =>
        System.out.println(e)
    }
  }

  @throws[IOException]
  def doGet2(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    try {
      val jspFile = request.getParameter("jspFile")
      var requestDispatcher = request.getRequestDispatcher(jspFile)
      requestDispatcher.include(request, response)
      requestDispatcher = request.getSession.getServletContext.getRequestDispatcher(jspFile)
      requestDispatcher.forward(request, response)
    } catch {
      case e: Exception =>
        System.out.println(e)
    }
  }
}
