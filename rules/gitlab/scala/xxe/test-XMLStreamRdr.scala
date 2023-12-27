// License: LGPL-3.0 License (c) find-sec-bugs
package xxe

import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamException
import javax.xml.stream.XMLStreamReader
import java.io.InputStream


class XMLStreamRdr {
  @throws[XMLStreamException]
  def loadXml(): Unit = {
    val in: InputStream = getClass.getResourceAsStream("/testcode/xxe/simple_xxe.xml")
    val dos_in: InputStream = getClass.getResourceAsStream("/testcode/xxe/dos_xxe.xml")
    if (in == null) System.out.println("Oups XML file not found.")
    if (dos_in == null) System.out.println("Oups XML DoS file not found.")
    //parseXMLSafe1(in);
    //parseXMLSafe2(in);
    //parseXMLSafe3(in);
    //parseXMLSafe4(in);
    // Testing for entity embedding (lol bomb)
    //parseXMLSafe1(dos_in);
    //parseXMLSafe2(dos_in);
    //parseXMLSafe3(dos_in);
    //parseXMLSafe4(dos_in);
  }

  @throws[XMLStreamException]
  def parseXMLSafe1(input: InputStream): Unit = {
    val factory = XMLInputFactory.newFactory
    factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false)
    factory.setProperty(XMLInputFactory.SUPPORT_DTD, false)
    val reader = factory.createXMLStreamReader(input)
    while ( {
      reader.hasNext
    }) reader.next
  }

  @throws[XMLStreamException]
  def parseXMLSafe2(input: InputStream): Unit = {
    val factory = XMLInputFactory.newFactory
    factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false)
    val reader = factory.createXMLStreamReader(input)
    while ( {
      reader.hasNext
    }) reader.next
  }

  @throws[XMLStreamException]
  def parseXMLSafe3(input: InputStream): Unit = {
    val factory = XMLInputFactory.newFactory
    factory.setProperty(XMLInputFactory.SUPPORT_DTD, false)
    val reader = factory.createXMLStreamReader(input)
    while ( {
      reader.hasNext
    }) reader.next
  }

  @throws[XMLStreamException]
  def parseXMLUnsafe(input: InputStream): Unit = {
    val factory = XMLInputFactory.newFactory
    factory.setProperty(XMLInputFactory.SUPPORT_DTD, true)
    val reader = factory.createXMLStreamReader(input)
    while ( {
      reader.hasNext
    }) reader.next
  }
}
