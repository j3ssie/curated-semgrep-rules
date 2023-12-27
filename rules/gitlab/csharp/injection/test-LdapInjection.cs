// License: LGPL-3.0 License (c) security-code-scan

using System.DirectoryServices;
using System.Web.Mvc;

public class LdapInjection: Controller
{
  public void Do()
  {
        var input = Console.ReadLine();
        var searcher = new DirectorySearcher();
        searcher.Filter = "(cn=" + input + ")";
        searcher.Path = "(cn=" + input + ")";

        searcher.FindAll();
  }

  private void AddPathParameter(UriBuilder uriBuilder)
  {
        uriBuilder.Path = "abc" + Console.ReadLine();
  }
}
