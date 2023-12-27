// License: LGPL-3.0 License (c) find-sec-bugs
// scaffold: dependencies=com.amazonaws.aws-java-sdk-simpledb@1.12.187
package inject

import javax.servlet.http.HttpServletRequest
import java.util.ResourceBundle
import java.util.function.Supplier
import java.util.logging._


object CLRFInjectionLogs {
  var req = null
}

class CLRFInjectionLogs {
  def javaUtilLogging(req: HttpServletRequest): Unit = {
    val tainted = req.getParameter("test")
    val safe = "safe"
    val logger = Logger.getLogger(classOf[Nothing].getName)
    logger.setLevel(Level.ALL)
    val handler = new ConsoleHandler
    handler.setLevel(Level.ALL)
    logger.addHandler(handler)
    logger.config(tainted)
    logger.entering(tainted, safe)
    logger.entering("safe", safe, tainted)
    logger.entering(safe, "safe", Array[String](tainted))
    logger.exiting(safe, tainted)
    logger.exiting(safe, "safe", tainted)
    logger.fine(tainted)
    logger.finer(tainted.trim)
    logger.finest(tainted)
    logger.info(tainted)
    logger.log(Level.INFO, tainted)
    logger.log(Level.INFO, tainted, safe)
    logger.log(Level.INFO, "safe", Array[String](tainted))
    logger.log(Level.INFO, tainted)
    logger.logp(Level.INFO, tainted, safe, "safe")
    logger.logp(Level.INFO, safe, "safe", tainted, safe)
    logger.logp(Level.INFO, "safe", safe.toLowerCase, safe, Array[String](tainted))
    logger.logrb(Level.INFO, tainted, "safe", "bundle", safe, Array[String](safe))
    logger.severe(tainted + "safe" + safe)

    logger.warning(tainted.replaceAll("\n", "")) // still insecure (CR not replaced)

    // these should not be reported
    logger.fine(safe)
    logger.log(Level.INFO, "safe".toUpperCase, safe + safe)
    logger.logp(Level.INFO, safe, safe, safe, Array[String](safe))
    logger.logrb(Level.INFO, safe, safe, tainted + "bundle", safe) // bundle name can be tainted

    logger.info(tainted.replace('\n', ' ').replace('\r', ' '))
    var encoded = tainted.replace("\r", "").toUpperCase
    encoded = "safe" + encoded.toLowerCase
    logger.warning(encoded.replace("\n", " (new line)"))
    logger.fine(tainted.replaceAll("[\r\n]+", ""))
  }
}
