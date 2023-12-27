// License: LGPL-3.0 License (c) find-sec-bugs
import javax.el.*;
import javax.faces.context.FacesContext;

public class ELInjection {

    public String valueExpr(String expression) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
        ELContext elContext = context.getELContext();
        ValueExpression vex = expressionFactory.createValueExpression(elContext, expression, String.class);
        return (String) vex.getValue(elContext);
    }

    public MethodInfo methodExpr(String expression) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
        ELContext elContext = context.getELContext();
        MethodExpression ex = expressionFactory.createMethodExpression(elContext, expression, String.class, null);
        return ex.getMethodInfo(elContext);
    }
    
}
