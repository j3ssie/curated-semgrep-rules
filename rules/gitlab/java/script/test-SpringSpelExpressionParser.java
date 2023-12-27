// License: LGPL-3.0 License (c) find-sec-bugs
// source: https://github.com/find-sec-bugs/find-sec-bugs/blob/master/findsecbugs-samples-java/src/test/java/testcode/script/SpelView.java
// hash: a7694d0
package script;

import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class SpringSpelExpressionParser implements View {
    private String template;

    private final SpelExpressionParser parser = new SpelExpressionParser();
    private final ExpressionParser parserVariant = new SpelExpressionParser();

    private final StandardEvaluationContext context = new StandardEvaluationContext();

    private PropertyPlaceholderHelper.PlaceholderResolver resolver;

    public void SpelView(String template) {
        this.template = template;
        this.context.addPropertyAccessor(new MapAccessor());
        this.resolver = name -> {
            try {
                // ruleid: java_script_rule-SpelExpressionParser
                Expression expression = this.parser.parseExpression(name);
                // ruleid: java_script_rule-SpelExpressionParser
                Expression expression2 = this.parserVariant.parseExpression(name);

                String tainted = name + "blah";
                // ruleid: java_script_rule-SpelExpressionParser
                Expression expression3 = this.parser.parseExpression(tainted);
                // ruleid: java_script_rule-SpelExpressionParser
                Expression expression4 = this.parserVariant.parseExpression(tainted);

                Object value = expression.getValue(context);
                return value == null ? null : value.toString();
            }
            catch (Exception e) {
                return null;
            }
        };
    }

    public void SpelViewParseRaw(String template) {
        this.template = template;
        this.context.addPropertyAccessor(new MapAccessor());
        this.resolver = name -> {
            try {
                // ruleid: java_script_rule-SpelExpressionParser
                Expression expression = this.parser.parseRaw(name);

                String tainted = name + "blah";
                // ruleid: java_script_rule-SpelExpressionParser
                Expression expression3 = this.parser.parseRaw(tainted);

                Object value = expression.getValue(context);
                return value == null ? null : value.toString();
            }
            catch (Exception e) {
                return null;
            }
        };
    }

    public String getContentType() {
        return "text/html";
    }

    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map<String, Object> map = new HashMap<>(model);
        String path = ServletUriComponentsBuilder.fromContextPath(request).build()
                .getPath();
        map.put("path", path==null ? "" : path);
        context.setRootObject(map);
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
        String result = helper.replacePlaceholders(template, resolver);
        response.setContentType(getContentType());
        response.getWriter().append(result);
    }
}
