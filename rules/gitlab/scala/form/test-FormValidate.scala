// License: LGPL-3.0 License (c) find-sec-bugs
package form

import org.apache.struts.validator.ValidatorForm

class FormValidate extends ValidatorForm {
  private var name: String = null
  private var email: String = null

  def getName = name

  def setName(n: String) {
    this.name = n
  }

  def getEmail = email

  def setEmail(email: String) {
    this.email = email
  }
}
