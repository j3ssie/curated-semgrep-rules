// License: LGPL-3.0 License (c) find-sec-bugs
package inject

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
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


class SpotbugsPathTraversal extends HttpServlet { // DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
  @Override
  @throws[ServletException]
  @throws[IOException]
  override protected def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val input = req.getParameter("input")
    new File(input + "/abs/path") // BAD, DETECTS: PT_RELATIVE_PATH_TRAVERSAL
  }

  @throws[ServletException]
  @throws[IOException]
  protected def danger2(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val input1 = req.getParameter("input1")
    new File(input1) // BAD
  }

  @throws[ServletException]
  @throws[IOException]
  @throws[URISyntaxException]
  protected def danger3(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val input = req.getParameter("test")
    new File(input)
    new File("test/" + input, "misc.jpg")
    new RandomAccessFile(input, "r") // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL

    new File(new URI(input))
    new FileReader(input)
    new FileInputStream(input)
    // false positive test
    new RandomAccessFile("safe", input)
    new FileWriter("safe".toUpperCase)
    new File(new URI("safe"))
    File.createTempFile(input, "safe")
    File.createTempFile("safe", input)
  }

  // nio path traversal
  def loadFile(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val path = req.getParameter("test")
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
  def tempDir(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val input = req.getParameter("test")
    val p = Paths.get("/")
    Files.createTempFile(p, input, "")
    Files.createTempFile(p, "", input)
    Files.createTempFile(input, "")
    Files.createTempFile("", input)
    Files.createTempDirectory(p, input)
    Files.createTempDirectory(input)
  }

  @throws[IOException]
  def writer(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val input = req.getParameter("test")
    new FileWriter(input)
    new FileWriter(input, true)
    new FileOutputStream(input)
    new FileOutputStream(input, true)
  }
}
