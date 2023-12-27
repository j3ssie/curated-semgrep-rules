// License: LGPL-3.0 License (c) find-sec-bugs
package inject

import javax.el._
import javax.faces.context.FacesContext


class ELInjection {
  def valueExpr(expression: String) = {
    val context = FacesContext.getCurrentInstance
    val expressionFactory = context.getApplication.getExpressionFactory
    val elContext = context.getELContext
    val vex = expressionFactory.createValueExpression(elContext, expression, classOf[Nothing])
    vex.getValue(elContext).asInstanceOf[Nothing]
  }

  def methodExpr(expression: String) = {
    val context = FacesContext.getCurrentInstance
    val expressionFactory = context.getApplication.getExpressionFactory
    val elContext = context.getELContext
    val ex = expressionFactory.createMethodExpression(elContext, expression, classOf[Nothing], null)
    ex.getMethodInfo(elContext)
  }
}
