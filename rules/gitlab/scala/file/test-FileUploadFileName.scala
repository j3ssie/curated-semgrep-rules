// License: LGPL-3.0 License (c) find-sec-bugs
package file

import org.apache.commons.fileupload.FileItem
import org.apache.commons.fileupload.FileUploadException
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.commons.fileupload.servlet.ServletFileUpload
import javax.servlet.http.HttpServletRequest
import java.util._
import scala.jdk.CollectionConverters._

class FileUploadFileName {
  @throws[FileUploadException]
  def handleFileCommon(req: HttpServletRequest): Unit = {
    val upload = new ServletFileUpload(new DiskFileItemFactory())
    val fileItems = upload.parseRequest(req)
    for (item <- fileItems.asScala) {
      println("Saving " + item.getName() + "...")
    }
  }
}
