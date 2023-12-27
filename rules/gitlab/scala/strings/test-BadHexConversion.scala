// License: LGPL-3.0 License (c) find-sec-bugs
package strings

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class BadHexConversion {
  @throws[NoSuchAlgorithmException]
  @throws[UnsupportedEncodingException]
  def danger(text: String) = {
    val md: MessageDigest = MessageDigest.getInstance("SHA-256")
    val resultBytes = md.digest(text.getBytes("UTF-8"))
    val stringBuilder = new StringBuilder
    for (b <- resultBytes) {
      stringBuilder.append(Integer.toHexString(b))
    }
    stringBuilder.toString
  }

  @throws[NoSuchAlgorithmException]
  @throws[UnsupportedEncodingException]
  def danger2(text: String) = {
    val md: MessageDigest = MessageDigest.getInstance("SHA-256")
    val resultBytes = md.digest(text.getBytes("UTF-8"))
    val stringBuilder = new StringBuilder
    var i = 0
    val resultBytesLength = resultBytes.length
    while ({
      i < resultBytesLength
    }) {
      val b = resultBytes(i)
      stringBuilder.append(Integer.toHexString(b))
      i += 1
    }
    stringBuilder.toString
  }
}
