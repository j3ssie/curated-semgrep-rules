// License: LGPL-3.0 License (c) find-sec-bugs
package perm;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class OverlyPermissiveFilePermissionInline {

  public void dangerInline(Path path) throws IOException {
    Files.setPosixFilePermissions(path, PosixFilePermissions.fromString("rw-rw-rw-"));
  }

  public void dangerInline2(Path path) throws IOException {
    Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-rw-rw-");
    Files.setPosixFilePermissions(path, perms);
  }

  public void okInline(Path path) throws IOException {
    Files.setPosixFilePermissions(path, PosixFilePermissions.fromString("rw-rw----"));
  }
}
