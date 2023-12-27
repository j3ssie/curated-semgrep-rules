// License: LGPL-3.0 License (c) find-sec-bugs
package cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieInsecure {

    public void danger(HttpServletResponse res) {
        Cookie cookie = new Cookie("key", "value");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60);

        cookie.setSecure(false); // danger

        res.addCookie(cookie);
    }

    // cookie.setSecure(true); is missing
    public void danger2(HttpServletResponse res) {
        Cookie cookie = new Cookie("key", "value");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60);
        res.addCookie(cookie);
    }
}
