// License: LGPL-3.0 License (c) find-sec-bugs
package xxe

import javax.xml.XMLConstants
import javax.xml.stream.XMLStreamException
import javax.xml.transform._
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource
import java.io.InputStream
import java.io.StringWriter

class Trans {
  @throws[XMLStreamException]
  @throws[TransformerException]
  def loadXml(): Unit = {
    val in = getClass.getResourceAsStream("/testcode/xxe/simple_xxe.xml")
    val dos_in = getClass.getResourceAsStream("/testcode/xxe/dos_xxe.xml")
    val xslt_in = getClass.getResourceAsStream("/testcode/xxe/simple_xxe.xslt")
    if (in == null) System.out.println("Oups XML file not found.")
    if (dos_in == null) System.out.println("Oups XML DoS file not found.")
    if (xslt_in == null) System.out.println("Oups XSLT file not found.")
    val source = new StreamSource(in)
    val source_dos = new StreamSource(dos_in)
    val xslt = new StreamSource(xslt_in)
    parseXMLdefaultValue(source)
    //parseXMLDoS(source_dos);
    //parseXMLOneLiner(source);
    //parseXMLWithXslt(source, xslt);
    //parseXMLWithMissingAttributeStylesheet(source, xslt);
    //parseXMLWithMissingAttributeDtd(source, xslt);
    //parseXMLWithWrongFlag1(source);
    //parseXMLWithWrongFlag2(source);
  }

  @throws[XMLStreamException]
  @throws[TransformerException]
  def parseXMLdefaultValue(input: Source): Unit = {
    val factory = TransformerFactory.newInstance
    val transformer = factory.newTransformer
    val outWriter = new StringWriter
    val result = new StreamResult(outWriter)
    transformer.transform(input, result)
    outWriter.toString
  }

  @throws[XMLStreamException]
  @throws[TransformerException]
  def parseXMLDoS(input: Source): Unit = {
    val factory = TransformerFactory.newInstance
    val transformer = factory.newTransformer
    val outWriter = new StringWriter
    val result = new StreamResult(outWriter)
    transformer.transform(input, result)
    outWriter.toString
  }

  @throws[XMLStreamException]
  @throws[TransformerException]
  def parseXMLOneLiner(input: Source): Unit = {
    val transformer = TransformerFactory.newInstance.newTransformer
    val outWriter = new StringWriter
    val result = new StreamResult(outWriter)
    transformer.transform(input, result)
    outWriter.toString
  }

  @throws[XMLStreamException]
  @throws[TransformerException]
  def parseXMLWithXslt(input: Source, xslt: Source): Unit = {
    val transformer = TransformerFactory.newInstance.newTransformer(xslt)
    val outWriter = new StringWriter
    val result = new StreamResult(outWriter)
    transformer.transform(input, result)
    outWriter.toString
  }

  @throws[XMLStreamException]
  @throws[TransformerException]
  def parseXMLWithMissingAttributeStylesheet(input: Source, xslt: Source): Unit = {
    val factory = TransformerFactory.newInstance
    factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "")
    val transformer = factory.newTransformer(xslt)
    transformer.setOutputProperty(OutputKeys.INDENT, "yes")
    val outWriter = new StringWriter
    val result = new StreamResult(outWriter)
    transformer.transform(input, result)
    result.toString
  }

  @throws[XMLStreamException]
  @throws[TransformerException]
  def parseXMLWithMissingAttributeDtd(input: Source, xslt: Source): Unit = {
    val factory = TransformerFactory.newInstance
    factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "")
    val transformer = factory.newTransformer(xslt)
    transformer.setOutputProperty(OutputKeys.INDENT, "yes")
    val outWriter = new StringWriter
    val result = new StreamResult(outWriter)
    transformer.transform(input, result)
    result.toString
  }

  @throws[XMLStreamException]
  @throws[TransformerException]
  def parseXMLWithWrongFlag1(input: Source): Unit = {
    val factory = TransformerFactory.newInstance
    factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "all")
    factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "all")
    val transformer = factory.newTransformer
    transformer.setOutputProperty(OutputKeys.INDENT, "yes")
    val outWriter = new StringWriter
    val result = new StreamResult(outWriter)
    transformer.transform(input, result)
    result.toString
  }

  @throws[XMLStreamException]
  @throws[TransformerException]
  def parseXMLWithWrongFlag2(input: Source): Unit = {
    val factory = TransformerFactory.newInstance
    factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false)
    val transformer = factory.newTransformer
    transformer.setOutputProperty(OutputKeys.INDENT, "yes")
    val outWriter = new StringWriter
    val result = new StreamResult(outWriter)
    transformer.transform(input, result)
    result.toString
  }
}
