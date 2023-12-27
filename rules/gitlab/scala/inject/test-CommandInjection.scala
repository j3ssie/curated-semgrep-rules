// License: LGPL-3.0 License (c) find-sec-bugs
// scaffold: dependencies=com.amazonaws.aws-java-sdk-simpledb@1.12.187
package inject

import java.io.IOException
import java.util.Arrays

class CommandInjection {
  @throws[IOException]
  def danger(cmd: String): Unit = {
    val r = Runtime.getRuntime
    r.exec(cmd)
    r.exec(Array[String]("test"))
    r.exec(Array[String]("bash", cmd))
    r.exec(Array[String]("/bin/sh", "-c", cmd))
  }

  def danger2(cmd: String): Unit = {
    val b = new ProcessBuilder()
    b.command(cmd)
    b.command("test")
    b.command(Arrays.asList("/bin/sh", "-c", cmd))
  }
}
