// License: LGPL-3.0 License (c) find-sec-bugs
package crypto

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.security.MessageDigest
import java.util._


class CustomMessageDigest extends MessageDigest("WEAK") {
  private val buffer = new ByteArrayOutputStream

  override protected def engineUpdate(input: Byte): Unit = {
    buffer.write(input)
  }

  @Override protected def engineUpdate(input: Array[Byte], offset: Int, len: Int): Unit = {
    try buffer.write(input)
    catch {
      case e: IOException =>
        throw new RuntimeException(e)
    }
  }

  override protected def engineDigest = {
    val content = buffer.toByteArray
    Arrays.copyOf(content, 8)
  }

  override protected def engineReset(): Unit = {
    buffer.reset
  }
}
