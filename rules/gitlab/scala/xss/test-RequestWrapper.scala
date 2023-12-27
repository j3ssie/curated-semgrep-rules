// License: LGPL-3.0 License (c) find-sec-bugs
package xss

import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper


object RequestWrapper {
  private val patterns = Array[Pattern]( // Script fragments
    Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE), // src='...'
    Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL), Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL), // lonely script tags
    Pattern.compile("</script>", Pattern.CASE_INSENSITIVE), Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL), // eval(...)
    Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL), // expression(...)
    Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL), // javascript:...
    Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE), // vbscript:...
    Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE), // onload(...)=...
    Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL))
}

class RequestWrapper(val servletRequest: HttpServletRequest) extends HttpServletRequestWrapper(servletRequest) {
  override def getParameterValues(parameter: String): Array[String] = {
    val values = super.getParameterValues(parameter)
    if (values == null) return null
    val count = values.length
    val encodedValues = new Array[String](count)
    for (i <- 0 until count) {
      encodedValues(i) = stripXSS(values(i))
    }
    encodedValues
  }

  override def getParameter(parameter: String) = {
    val value = super.getParameter(parameter)
    stripXSS(value)
  }

  override def getHeader(name: String) = {
    val value = super.getHeader(name)
    stripXSS(value)
  }

  private def stripXSS(value: String) = {
    var innerValue = value
    if (innerValue != null) { // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
      // avoid encoded attacks.
      // value = ESAPI.encoder().canonicalize(value);
      // Avoid null characters
      innerValue = innerValue.replaceAll("\u0000", "")
      // Remove all sections that match a pattern
      for (scriptPattern <- RequestWrapper.patterns) {
        innerValue = scriptPattern.matcher(innerValue).replaceAll("")
      }
    }
    value
  }
}
