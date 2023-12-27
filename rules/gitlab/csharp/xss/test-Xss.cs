// License: LGPL-3.0 License (c) security-code-scan
using System.Web;
using System.Web.Mvc;
class Xss {
    public class TestController : Controller
    {
        [HttpGet]
        public string Get(string myParam)
        {
            return "value " + myParam;
        }
        
        [HttpPost]
        [ValidateAntiForgeryToken]
        public string Post(string myParam)
        {
            return "value " + HttpUtility.HtmlEncode(myParam);
        }
    }
}

