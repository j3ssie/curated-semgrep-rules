// License: LGPL-3.0 License (c) find-sec-bugs
package unsafe

import play.api.Configuration
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject

class SensitiveDataExposure @Inject() (cc: ControllerComponents) extends AbstractController(cc){

  def doGet(configuration: Configuration, value: String) =  Action{
    val configElement = configuration.underlying.getString(value)
    Ok("Hello " + configElement + " !")
  }


  def doGet2(configuration: Configuration, value: String) = Action {
    val configElement = configuration.underlying.getString(value)
    Ok("Hello " + configElement + " !")
  }
}
