// License: LGPL-3.0 License (c) find-sec-bugs
package cookie

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

class CookieHTTPOnly {
  def danger(res: HttpServletResponse): Unit = {
    val cookie = new Cookie("key", "value")
    cookie.setSecure(true)
    cookie.setMaxAge(60)
    cookie.setHttpOnly(false) // danger

    res.addCookie(cookie)
  }

  // cookie.setHttpOnly(true) is missing
  def danger2(res: HttpServletResponse): Unit = {
    val cookie = new Cookie("key", "value")
    cookie.setSecure(true)
    cookie.setMaxAge(60)
    res.addCookie(cookie)
  }
}

