// License: LGPL-3.0 License (c) find-sec-bugs
package unsafe;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;

public class ExternalConfigControl {
    private HttpServletRequest globalReq;
    private Connection globalConn;

    public void callSetCatalog(Connection c,HttpServletRequest req) throws SQLException {
        String tainted = req.getParameter("input");
        c.setCatalog(tainted);
        c.setCatalog("safe"); // ok
        c.setCatalog("very ".concat("safe").toUpperCase()); // ok
    }

    public void callSetCatalog2(Connection c) throws SQLException {
        String tainted = globalReq.getParameter("input");
        c.setCatalog(tainted);
        c.setCatalog("safe"); // ok
        c.setCatalog("very ".concat("safe").toUpperCase()); // ok
    }

    public void callSetCatalog3() throws SQLException {
        String tainted = globalReq.getParameter("input");
        globalConn.setCatalog(tainted);
        globalConn.setCatalog("safe"); // ok
        globalConn.setCatalog("very ".concat("safe").toUpperCase()); // ok
    }
}
