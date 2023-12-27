// License: LGPL-3.0 License (c) find-sec-bugs
package xxe

import org.xml.sax.InputSource
import org.xml.sax.SAXException
import org.xml.sax.XMLReader
import org.xml.sax.helpers.DefaultHandler
import org.xml.sax.helpers.XMLReaderFactory
import javax.xml.parsers.ParserConfigurationException
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream


object XMLRdr {
  @throws[ParserConfigurationException]
  @throws[SAXException]
  @throws[IOException]
  private def receiveXMLStream(inStream: InputStream, defHandler: DefaultHandler): Unit = { // ...
    val reader = XMLReaderFactory.createXMLReader
    reader.parse(new InputSource(inStream))
  }

  @throws[ParserConfigurationException]
  @throws[SAXException]
  @throws[IOException]
  def main(args: Array[String]): Unit = {
    val xmlString = "<?xml version=\"1.0\"?>" + "<!DOCTYPE test [ <!ENTITY foo SYSTEM \"C:/Code/public.txt\"> ]><test>&foo;</test>" // Tainted input
    val is = new ByteArrayInputStream(xmlString.getBytes)
    receiveXMLStream(is, new DefaultHandler)
  }
}

