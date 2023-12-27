// License: LGPL-3.0 License (c) find-sec-bugs
package xxe

import org.xml.sax.InputSource
import javax.xml.XMLConstants
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathExpression
import javax.xml.xpath.XPathFactory


object XPathXXE {
  @throws[Exception]
  def main(args: Array[String]): Unit = {
    safe(args(0))
    safe2(args(0))
    unsafe3(args(0))
  }

  @throws[Exception]
  def safe(str: String): Unit = {
    val df = DocumentBuilderFactory.newInstance
    df.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "")
    df.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "")
    df.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true)
    df.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true)

    val builder = df.newDocumentBuilder
    val xPathFactory = XPathFactory.newInstance
    val xpath = xPathFactory.newXPath
    val xPathExpr = xpath.compile("/xmlhell/text()")
    val result = xPathExpr.evaluate(builder.parse(str))
  }

  @throws[Exception]
  def safe2(str: String): Unit = {
    val df = DocumentBuilderFactory.newInstance
    df.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "")
    df.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "")
    val builder = df.newDocumentBuilder
    val xPathFactory = XPathFactory.newInstance
    val xpath = xPathFactory.newXPath
    val xPathExpr = xpath.compile("/xmlhell/text()")
    val result = xPathExpr.evaluate(builder.parse(str))
  }

  @throws[Exception]
  def unsafe3(str: String): Unit = {
    val df = DocumentBuilderFactory.newInstance
    val builder = df.newDocumentBuilder
    val xPathFactory = XPathFactory.newInstance
    val xpath = xPathFactory.newXPath
    val xPathExpr = xpath.compile("/xmlhell/text()")
    xPathExpr.evaluate(new InputSource(str), null)
  }
}
