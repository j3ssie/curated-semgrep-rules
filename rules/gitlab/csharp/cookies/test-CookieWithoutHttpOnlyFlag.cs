// License: LGPL-3.0 License (c) security-code-scan

using System.Net;

class CookieWithoutHttpOnlyFlag
{
  static void BadCookie()
  {
    var cookie = new Cookie();
    cookie.HttpOnly = true;
    var request = (HttpWebRequest)WebRequest.Create("");
    request.CookieContainer.Add(cookie);
  }

  static void GoodCookie()
  {
    var cookie = new Cookie();
    cookie.HttpOnly = false;
    var request = (HttpWebRequest)WebRequest.Create("");
    request.CookieContainer.Add(cookie);
  }
}
