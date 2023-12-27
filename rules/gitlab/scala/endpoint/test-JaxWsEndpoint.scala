// License: LGPL-3.0 License (c) find-sec-bugs
package endpoint

import org.apache.commons.text.StringEscapeUtils
import javax.jws.WebMethod
import javax.jws.WebService


@WebService
class JaxWsEndpoint {
  @WebMethod(operationName = "timestamp")
  def ping = System.currentTimeMillis // OK

  def randomFunc(s: String) = s

  @WebMethod
  def danger0(user: String) = "Hello " + user // BAD

  @WebMethod
  def danger1(user: String) = {
    val tainted = randomFunc(user)
    "Hello " + tainted
  }

  @WebMethod
  def danger3(user: String) = {
    "Hello " + user
  }

  @WebMethod(action="/hello2")
  def ok1(user: String) = {
    val sanitized = StringEscapeUtils.unescapeJava(user)
    "Hello " + sanitized // OK
  }

  def ok2(user: String): String = {
    return "Hello " + user // OK
  }

  @WebMethod
  def ok3(user: String) = {
    val sanitized = StringEscapeUtils.unescapeJava(user)
    "Hello " + sanitized
  }

  def ok4 = 8000
}
