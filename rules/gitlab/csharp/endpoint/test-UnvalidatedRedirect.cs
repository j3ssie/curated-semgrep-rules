// License: LGPL-3.0 License (c) security-code-scan
using System;
using Microsoft.AspNetCore.Mvc;

public class UnvalidatedRedirect : Controller
{
    [HttpGet]
    public IActionResult LogOn(string returnUrl) 
    {
        if (!String.IsNullOrEmpty(returnUrl))
        {
            return Redirect(returnUrl);
        }
        return Redirect(returnUrl);
    }

    [HttpGet]
    public IActionResult LogOn2(string returnUrl) 
    {
        if (Url.IsLocalUrl(returnUrl))
        {
            return Redirect(returnUrl);
        }
        return Redirect("x");
    }
}

