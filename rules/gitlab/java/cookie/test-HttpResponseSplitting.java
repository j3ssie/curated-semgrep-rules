// License: LGPL-3.0 License (c) find-sec-bugs
package cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

public class HttpResponseSplitting extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getParameter("input");
        Cookie c = new Cookie("name", null);
        c.setValue(input);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(c);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getParameter("input");
        Cookie c = new Cookie("name", input);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(c);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = req.getParameter("input");
        String input = data.replaceAll("\n", "");
        Cookie c = new Cookie("name", input);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(c);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // BAD
        String tainted = req.getParameter("input");
        resp.setHeader("test", tainted);

        // OK: False negative but reported by spotbugs
        String data = req.getParameter("input");
        String normalized = data.replaceAll("\n", "\n");
        resp.setHeader("test", normalized);

        // OK: False negative but reported by spotbugs
        String normalized2 = data.replaceAll("\n", req.getParameter("test"));
        resp.setHeader("test2", normalized2);

        // OK
        String normalized3 = org.apache.commons.text.StringEscapeUtils.unescapeJava(tainted);
        resp.setHeader("test3", normalized3);

        // BAD
        String normalized4 = getString(tainted);
        resp.setHeader("test4", normalized4);

        // BAD
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(resp);
        wrapper.addHeader("test", tainted);
        wrapper.setHeader("test2", tainted);

    }

    private String getString(String s) {
        return s;
    }
}
