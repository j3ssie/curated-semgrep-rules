// License: LGPL-3.0 License (c) security-code-scan

using System.DirectoryServices;

class LDAPFilterInjection
{
	static void Ldapi(string input)
	{
        var dir = new DirectoryEntry();
        dir.Path = $"GC://DC={input},DC=com";
	}
}
