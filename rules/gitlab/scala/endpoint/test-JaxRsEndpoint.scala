// License: LGPL-3.0 License (c) find-sec-bugs
package endpoint

import javax.ws.rs.Path
import org.apache.commons.text.StringEscapeUtils


@Path("/test")
class JaxRsEndpoint {
  def randomFunc(s: String) = s

  @Path("/hello0")
  def danger0(user: String) = "Hello " + user // BAD

  @Path("/hello1")
  def danger1(user: String) = {
    val tainted = randomFunc(user)
    "Hello " + tainted
  }

  @Path("/hello2")
  def danger3(user: String) = {
    "Hello " + user
  }

  @Path("/hello2")
  def danger4(user: String): String = {
    return "Hello " + user
  }

  @Path("/hello2")
  def ok1(user: String) = {
    val sanitized = StringEscapeUtils.unescapeJava(user)
    "Hello " + sanitized // OK
  }

  def ok2(user: String): String = {
    return "Hello " + user // OK
  }
}
