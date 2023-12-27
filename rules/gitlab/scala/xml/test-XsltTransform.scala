// License: LGPL-3.0 License (c) find-sec-bugs
package xml

import org.apache.commons.io.IOUtils

import javax.xml.transform.{Source, Transformer, TransformerException, TransformerFactory, stream}
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource
import java.io.FileInputStream
import java.io.IOException

object XsltTransform {
  val FOLDER = "/testcode/xsl/"
}

class XsltTransform {
  @throws[TransformerException]
  def xslt1SafeStaticResource(): Unit = {
    val factory = TransformerFactory.newInstance
    val xslt = new StreamSource(getClass.getResourceAsStream(XsltTransform.FOLDER + "xsl_safe.xsl"))
    val transformer = factory.newTransformer(xslt)
    val text = new StreamSource(getClass.getResourceAsStream(XsltTransform.FOLDER + "input.xml"))
    transformer.transform(text, new StreamResult(System.out))
  }

  @throws[TransformerException]
  def xslt2UnsafeResource(input: String): Unit = {
    val factory = TransformerFactory.newInstance
    val xslt = new StreamSource(getClass.getResourceAsStream(input))
    val transformer = factory.newTransformer(xslt)
    val text = new StreamSource(getClass.getResourceAsStream(XsltTransform.FOLDER + "input.xml"))
    transformer.transform(text, new StreamResult(System.out))
  }

  @throws[TransformerException]
  def xslt3UnsafeResource(input: String): Unit = {
    val factory = TransformerFactory.newInstance
    val xslt = new StreamSource(getClass.getResourceAsStream(XsltTransform.FOLDER + input))
    val transformer = factory.newTransformer(xslt)
    val text = new StreamSource(getClass.getResourceAsStream(XsltTransform.FOLDER + "input.xml"))
    transformer.transform(text, new StreamResult(System.out))
  }

  @throws[TransformerException]
  def xslt4UnsafeResource(input: String): Unit = {
    val factory = TransformerFactory.newInstance
    var in: FileInputStream = null
    try {
      in = new FileInputStream(XsltTransform.FOLDER + input)
      val xslt = new StreamSource(in)
      val transformer = factory.newTransformer(xslt)
      val text = new StreamSource(getClass.getResourceAsStream(XsltTransform.FOLDER + "input.xml"))
      transformer.transform(text, new StreamResult(System.out))
    } catch {
      case e: IOException =>
        e.printStackTrace
    } finally IOUtils.closeQuietly(in)
  }

  @throws[TransformerException]
  def xslt5SafeResource(): Unit = {
    val input = "/safe.xsl"
    val factory = TransformerFactory.newInstance
    var in: FileInputStream = null
    try {
      in = new FileInputStream(XsltTransform.FOLDER + input)
      val xslt = new StreamSource(in)
      val transformer = factory.newTransformer(xslt)
      val text = new StreamSource(getClass.getResourceAsStream(XsltTransform.FOLDER + "input.xml"))
      transformer.transform(text, new StreamResult(System.out))
    } catch {
      case e: IOException =>
        e.printStackTrace
    } finally IOUtils.closeQuietly(in)
  }
}
