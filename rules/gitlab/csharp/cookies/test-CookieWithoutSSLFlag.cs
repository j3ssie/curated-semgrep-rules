// License: LGPL-3.0 License (c) security-code-scan

using System.Net;

class CookieWithoutSSLFlag
{
    static void BadCookie()
    {
        var cookie = new Cookie();
        cookie.Secure = false;
        var request = (HttpWebRequest)WebRequest.Create("");
        request.CookieContainer.Add(cookie);
    }

    static void GoodCookie()
    {
        var cookie = new Cookie();
        cookie.Secure = true;
        var request = (HttpWebRequest)WebRequest.Create("");
        request.CookieContainer.Add(cookie);
    }
}