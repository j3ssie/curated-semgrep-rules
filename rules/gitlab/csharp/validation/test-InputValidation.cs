// License: LGPL-3.0 License (c) security-code-scan
using System.Web.Mvc;
public class InputValidation
{
    [HttpPost]
    [ValidateAntiForgeryToken]
    [ValidateInput(false)]
    public ActionResult ControllerMethod1(string input) {
        return null;
    }

    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult ControllerMethod2(string input) {
        return null;
    }
}
