// License: LGPL-3.0 License (c) find-sec-bugs
package xml

import java.beans.XMLDecoder
import java.io.InputStream

object XmlDecodeUtil {
  def handleXml(in: InputStream) = {
    val d = new XMLDecoder(in)
    try {
      val result = d.readObject //Deserialization happen here
      result
    } finally d.close
  }

  def main(args: Array[String]): Unit = {
    val in = XmlDecodeUtil.getClass.getResourceAsStream("/testcode/xmldecoder/obj1.xml")
    XmlDecodeUtil.handleXml(in)
  }
}
