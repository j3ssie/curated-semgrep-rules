// License: LGPL-3.0 License (c) find-sec-bugs
package perm

import java.lang.reflect.ReflectPermission
import java.security.CodeSource
import java.security.PermissionCollection
import java.security.Policy
import java.util


class DangerousPermissions extends Policy {
  def danger(cs: CodeSource): Unit = {
    val pc: PermissionCollection = super.getPermissions(cs)
    pc.add(new ReflectPermission("suppressAccessChecks"))
  }

  def danger2(pc: PermissionCollection): Unit = {
    pc.add(new RuntimePermission("createClassLoader"))
  }

  def danger3(pc: PermissionCollection): Unit = {
    val perm = new RuntimePermission("createClassLoader")
    pc.add(perm)
  }

  def danger4(pc: PermissionCollection): Unit = {
    val perm = new ReflectPermission("suppressAccessChecks")
    pc.add(perm)
  }

  def ok(cs: CodeSource): Unit = {
    val perm = new ReflectPermission("suppressAccessChecks")
    val list = new Array[ReflectPermission](1)
    list(0) = perm
  }
}
