// License: LGPL-3.0 License (c) find-sec-bugs
package strings

import java.net.IDN
import java.net.URI
import java.text.Normalizer

class ImproperUnicode {
  def dangerToUpperEquals(s: String) = {
    s.toUpperCase().equals("TEST")
  }

  def dangerToUpperEqualIgnoreCase(s: String) = {
    s.toUpperCase().equalsIgnoreCase("TEST")
  }

  def dangerToUpperIndexOf(s: String) = {
    s.toUpperCase().indexOf("T")
  }

  def dangerToLowerEquals(s: String) = {
    s.toLowerCase().equals("test")
  }

  def dangerToLowerEqualIgnoreCase(s: String) = {
    s.toLowerCase().equalsIgnoreCase("test")
  }

  def dangerToLowerIndexOf(s: String) = {
    s.toLowerCase().indexOf("t")
  }

  def dangerURI(uri: URI) = {
    uri.toASCIIString()
  }

  def dangerIDN(input: String) = {
    IDN.toASCII(input)
  }

  def dangerNormalize(s: String) = {
    Normalizer.normalize(s.toUpperCase, Normalizer.Form.NFKC).equals("ADMIN")
  }
}
