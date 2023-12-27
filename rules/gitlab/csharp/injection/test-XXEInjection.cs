// License: LGPL-3.0 License (c) security-code-scan
using System.Xml;

class XXEInjection
{
    static void XXEReaderSettings(string inputXml)
    {
        // DTD expansion is enabled by default
        XmlReaderSettings settings = new XmlReaderSettings();
        XmlReader reader = XmlReader.Create(inputXml, settings);
        
    }
    
    static void XXEDocumentSettings(string pathToXmlFile)
    {
        XmlDocument xmlDoc = new XmlDocument();
        xmlDoc.Load(pathToXmlFile);
        Console.WriteLine(xmlDoc.InnerText);

        XmlDocument anotherDoc = new XmlDocument { };
        anotherDoc.Load("/config.xml");
    }

    static void SafeReaderSettings(string inputXml)
    {
        var settings = new XmlReaderSettings();
        // Prior to .NET 4.0
        settings.ProhibitDtd = true; // default is false!
        // .NET 4.0 - .NET 4.5.2
        settings.DtdProcessing = DtdProcessing.Prohibit; // default is DtdProcessing.Parse!
        
        XmlReader reader = XmlReader.Create(inputXml, settings);
    }
    
    static void SafeDocumentSettings(string pathToXmlFile)
    {
        XmlDocument xmlDoc = new XmlDocument();
        xmlDoc.XmlResolver = null; // Setting this to NULL disables DTDs - Its NOT null by default.
        xmlDoc.Load(pathToXmlFile);
        Console.WriteLine(xmlDoc.InnerText);

        XmlDocument anotherDoc = new XmlDocument { XmlResolver = null };
        anotherDoc.Load("/config.xml");
    }
}