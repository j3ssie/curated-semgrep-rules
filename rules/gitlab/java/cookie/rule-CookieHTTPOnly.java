// License: LGPL-3.0 License (c) find-sec-bugs
package cookie;

public class CookieHTTPOnly {

    public void dangerJavax(javax.servlet.http.HttpServletResponse res) {
        // ruleid: java_cookie_rule-CookieHTTPOnly
        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("key", "value");
        cookie.setSecure(true);
        cookie.setMaxAge(60);
        cookie.setHttpOnly(false); // danger
        res.addCookie(cookie);
    }

    // cookie.setHttpOnly(true) is missing
    public void danger2Javax(javax.servlet.http.HttpServletResponse res) {
        // ruleid: java_cookie_rule-CookieHTTPOnly
        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("key", "value");
        cookie.setSecure(true);
        cookie.setMaxAge(60);
        res.addCookie(cookie);
    }

    protected void safeJavax(javax.servlet.http.HttpServletResponse response) {
        // rule ok: java_cookie_rule-CookieHTTPOnly
        javax.servlet.http.Cookie myCookie = new javax.servlet.http.Cookie("key", "value");
        myCookie.setHttpOnly(true);
        myCookie.setMaxAge(60);
        response.addCookie(myCookie);
    }

    protected void dangerJakarta(jakarta.servlet.http.HttpServletResponse response) {
        // ruleid: java_cookie_rule-CookieHTTPOnly
        jakarta.servlet.http.Cookie myCookie = new jakarta.servlet.http.Cookie("key", "value");
        myCookie.setHttpOnly(false);
        myCookie.setMaxAge(60);
        response.addCookie(myCookie);
    }

    protected void danger2Jakarta(jakarta.servlet.http.HttpServletResponse response) {
        // ruleid: java_cookie_rule-CookieHTTPOnly
        jakarta.servlet.http.Cookie myCookie = new jakarta.servlet.http.Cookie("key", "value");
        myCookie.setMaxAge(60);
        response.addCookie(myCookie);
    }

    protected void safeJakarta(jakarta.servlet.http.HttpServletResponse response) {
        // rule ok: java_cookie_rule-CookieHTTPOnly
        jakarta.servlet.http.Cookie myCookie = new jakarta.servlet.http.Cookie("key", "value");
        myCookie.setHttpOnly(true);
        myCookie.setMaxAge(60);
        response.addCookie(myCookie);
    }
}
