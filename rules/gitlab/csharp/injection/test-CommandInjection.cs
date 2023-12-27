// License: LGPL-3.0 License (c) security-code-scan
using System.Diagnostics;

class CommandInjection
{
	static void ProcessWithStartInfoDirect()
	{
		var p = new Process();
		p.StartInfo.FileName = "exportLegacy.exe";
		p.StartInfo.Arguments = " -user " + Console.ReadLine() + " -role user";
		p.Start();
	}

	static void ProcessWithStartInfoObject()
	{
		var info = new ProcessStartInfo(Console.ReadLine());
		info.Arguments = Console.ReadLine();
		Process.Start(info);
	}

	static void ProcessDirect()
	{
		Process.Start(Console.ReadLine());
	}

	static void Safe()
	{
		var program = "notepad.exe";
		Process.Start(program);
	}
}
