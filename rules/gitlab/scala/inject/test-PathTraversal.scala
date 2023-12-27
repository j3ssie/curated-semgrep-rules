// License: LGPL-3.0 License (c) find-sec-bugs
package inject

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.io.RandomAccessFile
import java.net.URI
import java.net.URISyntaxException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


object PathTraversal {
  private[inject] val safefinalString = "SAFE"

  // DETECTS: PATH_TRAVERSAL_IN
  @throws[IOException]
  @throws[URISyntaxException]
  def unsafe(input: String): Unit = {
    new File(input)
    new File("test/" + input, "misc.jpg")
    new RandomAccessFile(input, "r")
    new FileReader(input)
    new FileInputStream(input)
    new FileWriter("safe".toUpperCase)
    new File(new String("safe"))
    File.createTempFile(input, "safe")
    File.createTempFile("safe", input)
    new File(safefinalString)
  }
}

class PathTraversal { // nio path traversal, DETECTS: PATH_TRAVERSAL_IN
  def loadFile(path: String): Unit = {
    Paths.get(path)
    Paths.get(path, "foo")
    Paths.get(path, "foo", "bar")
    Paths.get("foo", path)
    Paths.get("foo", "bar", path)
    Paths.get("foo")
    Paths.get("foo", "bar")
    Paths.get("foo", "bar", "allsafe")
  }

  @throws[IOException]
  def tempDir(input: String): Unit = {
    val p = Paths.get("/")
    Files.createTempFile(p, input, "")
    Files.createTempFile(p, "", input)
    Files.createTempFile(input, "")
    Files.createTempFile("", input)
    Files.createTempDirectory(p, input)
    Files.createTempDirectory(input)
  }

  // DETECTS: PATH_TRAVERSAL_OUT
  @throws[IOException]
  def pathTraversalWrite(input: String): Unit = {
    new FileWriter(input)
    new FileWriter(input, true)
    new FileWriter(input)
    new FileWriter(input, true)
  }
}
