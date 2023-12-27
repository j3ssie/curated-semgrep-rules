// License: LGPL-3.0 License (c) security-code-scan
using System.Xml.Xsl;

class UnsafeXSLTSettingsUsed
{
    static void Unsafe()
    {
        XslCompiledTransform transform = new XslCompiledTransform();
        XsltSettings settings = new XsltSettings() {EnableScript = true};
        transform.Load("foo", settings, null);

        XsltSettings settings2 = new XsltSettings();
        settings2.EnableScript = true;
        transform.Load("foo", settings2, null);
    }

    static void Safe()
    {
        XslCompiledTransform transform = new XslCompiledTransform();
        XsltSettings settings = new XsltSettings();
        transform.Load("foo", settings, null); 
    }
}