// License: MIT (c) GitLab Inc.
package ldap

import javax.naming.directory.SearchControls

class EntryPoisoning {
  private val scope = 0
  private val countLimit = 0
  private val timeLimit = 0
  private val attributes = null
  private val deref = false

  def unsafe1(): Unit = {
    new SearchControls(scope, countLimit, timeLimit, attributes, true, //!! It will flag line 14 ... the beginning of the call
      deref)
  }

  def unsafe2(): Unit = {
    val ctrl = new SearchControls()
    ctrl.setReturningObjFlag(true) //!!

  }

  def safe1(): Unit = {
    new SearchControls(scope, countLimit, timeLimit, attributes, false, //OK
      deref)
  }

  def safe2(): Unit = {
    val ctrl = new SearchControls()
    ctrl.setReturningObjFlag(false)
  }
}
