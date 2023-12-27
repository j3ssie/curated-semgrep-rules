// License: LGPL-3.0 License (c) find-sec-bugs
package xpathi;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;

public class XpathInjection {

    public static void main(String[] args) throws Exception {

        Document doc = null;

        String input = args.length != 0 ? args[1] : "guess' or '1'='1";

        String query = "//groups/group[@id='" + input + "']/writeAccess/text()";

        System.out.println(">> XPath.compile()");
        {
            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression expr = xpath.compile(query);
        }

        System.out.println(">> XPath.evaluate()");
        {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String result = xpath.evaluate(query, doc);
            System.out.println("result=" + result);
        }

        //Safe (The next sample should not be mark)
        System.out.println(">> Safe");
        {
            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression expr = xpath.compile("//groups/group[@id='admin']/writeAccess/text()");
        }
    }

    public static NodeList evaluateXPath(Document doc, XPathExpression xpath) throws XPathExpressionException {
        return (NodeList) xpath.evaluate(doc, XPathConstants.NODESET);
    }

}
