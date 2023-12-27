// License: LGPL-3.0 License (c) find-sec-bugs
package xpathi

import org.w3c.dom.Document
import org.w3c.dom.NodeList
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathFactory

object XpathInjection {
  @throws[Exception]
  def main(args: Array[String]): Unit = {
    val doc: Document = null
    val input = args(1)
    val query = "//groups/group[@id='" + input + "']/writeAccess/text()"
    System.out.println(">> XPath.compile()")
    XPathFactory.newInstance.newXPath.evaluate(query, doc)

    System.out.println(">> XPath.evaluate()")
    System.out.println("result=" +  XPathFactory.newInstance.newXPath.evaluate(query, doc))

    XPathFactory.newInstance.newXPath.compile(query)

    //Safe (The next sample should not be mark)
    System.out.println(">> Safe")
    XPathFactory.newInstance.newXPath.compile("//groups/group[@id='admin']/writeAccess/text()")
  }

}
