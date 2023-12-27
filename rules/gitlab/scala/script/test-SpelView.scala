// License: LGPL-3.0 License (c) find-sec-bugs
package script

import org.springframework.context.expression.MapAccessor
import org.springframework.expression.Expression
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import org.springframework.util.PropertyPlaceholderHelper
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver
import org.springframework.web.servlet.View
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

import java.util
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.util._


abstract class SpelView(val template: String) extends View {
  var resolver: PlaceholderResolver
  final private val parser = new SpelExpressionParser()
  final private val context = new StandardEvaluationContext()

  this.context.addPropertyAccessor(new MapAccessor())
  this.resolver = (name: String) => {
      val expression = parser.parseExpression(name) //BOOM!
      val value = expression.getValue(context)
      null
  }
  override def getContentType = "text/html"

  @throws[Exception]
  def render(model: java.util.Map[String, _], request: HttpServletRequest, response: HttpServletResponse): Unit = {
    val path = ServletUriComponentsBuilder.fromContextPath(request).build.getPath
    context.setRootObject(model)
    val helper = new PropertyPlaceholderHelper("${", "}")
    val result = helper.replacePlaceholders(template, resolver)
    response.setContentType(getContentType)
    response.getWriter.append(result)
  }
}
