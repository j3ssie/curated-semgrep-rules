// License: LGPL-3.0 License (c) find-sec-bugs
package xss

import org.owasp.encoder.Encode
import play.api.mvc.{AbstractController, ControllerComponents}
import javax.inject.Inject

class MVCApi @Inject() (cc: ControllerComponents) extends AbstractController(cc){

  def unsafe(value: String) =  Action{
    Ok(value)
  }

  def unsafe2(value: String) = Action {
    Ok(value + "test")
  }

  def safe(value: String) =  Action{
    val encoded = Encode.forHtml(value)
    Ok(encoded)
  }

  def safe2(value: String) =  Action{
    val encoded = Encode.forHtml(value + "test")
    Ok(encoded + "test")
  }
}
