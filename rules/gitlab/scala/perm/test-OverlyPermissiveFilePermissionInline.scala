// License: LGPL-3.0 License (c) find-sec-bugs
package perm

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.attribute.PosixFilePermission
import java.nio.file.attribute.PosixFilePermissions
import java.util


class OverlyPermissiveFilePermissionInline {
  @throws[IOException]
  def dangerInline(path: Nothing): Unit = {
    Files.setPosixFilePermissions(path, PosixFilePermissions.fromString("rw-rw-rw-"))
  }

  @throws[IOException]
  def dangerInline2(path: Nothing): Unit = {
    val perms = PosixFilePermissions.fromString("rw-rw-rw-")
    Files.setPosixFilePermissions(path, perms)
  }

  @throws[IOException]
  def okInline(path: Nothing): Unit = {
    Files.setPosixFilePermissions(path, PosixFilePermissions.fromString("rw-rw----"))
  }
}
