// License: LGPL-3.0 License (c) find-sec-bugs
package strings

import java.util.Formatter
import java.util.Locale
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServlet


class FormatStringManipulation extends HttpServlet {
  @throws[IOException]
  override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = { // create a new formatter
    val buffer = new StringBuffer()
    val formatter = new Formatter(buffer, Locale.US)
    val input = request.getParameter("suffix")
    val format = "The customer: %s %s" + input
    //test cases
    formatter.format(format, "John", "Smith", "Jr") //BAD

    formatter.format(Locale.US, format, "John", "Smith")
    //false positive test
    formatter.format("The customer: %s %s", "John", request.getParameter("testParam")) //OK

    System.out.printf(format, "John", "Smith")
    System.out.printf(Locale.US, format, "John", "Smith")
    System.out.format(format, "John", "Smith")
    System.out.format(Locale.US, format, "John", "Smith")

    val format2 = "The customer: %s %s" + request.getParameter("suffix")
    String.format(format2, "John", "Smith")
    String.format(Locale.US, format2, "John", "Smith")
  }
}
