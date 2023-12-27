// License: LGPL-3.0 License (c) security-code-scan

using System.Xml;
using System.Xml.Linq;
using System.Xml.Schema;
using System.Xml.XPath;

class XPathInjection
{
    static void QueryWithUserInput()
    {
        var input = Console.ReadLine();
        var doc = new XmlDocument {XmlResolver = null};
        doc.Load("/config.xml");
        
        var results = doc.SelectNodes("/Config/Devices/Device[id='" + input + "']");

        var result = doc.SelectSingleNode("/Config/Devices/Device[id='" + input + "']")!;

        result.SelectSingleNode("/Config/Devices/Device[id='" + input + "']");

        XPathNavigator navigator = doc.CreateNavigator()!;
        navigator.Compile(input);
        navigator.Evaluate(input);
        navigator.Matches(input);
        navigator.Select(input);
        navigator.SelectAncestors(input, input, false);
        navigator.SelectChildren(input, input);
        navigator.SelectDescendants(input, input, false);
        navigator.SelectSingleNode(input);
    }

    static void LinqExtensionQueryWithUserInput()
    {
        var input = Console.ReadLine();
        XDocument d = XDocument.Parse("");  
        d.XPathEvaluate("/root/" + input);  
        d.XPathSelectElement("/root/" + input);  
        d.XPathSelectElements("/root/" + input);  
    }

    static void XPathWithUserInput()
    {
        var input = Console.ReadLine();
        XmlSchemaXPath path = new XmlSchemaXPath
        {
            XPath = input
        };

        var path2 = new XmlSchemaXPath();
        path2.XPath = input;
    }

    static void Safe()
    {
        var doc = new XmlDocument {XmlResolver = null};
        doc.Load("/config.xml");

        var results = doc.SelectNodes("/Config/Devices/Device[id='123']");

        XmlNode result = doc.SelectSingleNode("/Config/Devices/Device[id='123']")!;

        result.SelectSingleNode("/Config/Devices/Device[id='123']");
        
        XDocument d = XDocument.Parse("");
        d.XPathEvaluate("/root");
        d.XPathSelectElement("/root");
        d.XPathSelectElements("/root");

        var cookieParameterNames = new HashSet<string>(cookieParameters.Select(c => c.Name));
    }
}