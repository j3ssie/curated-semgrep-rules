// License: LGPL-3.0 License (c) security-code-scan
using System;
using System.Web;
using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;

public class PathTraversal : Controller
{
    [HttpGet]
    public string Get(string myParam)
    {
        byte[] fileBytes = System.IO.File.ReadAllBytes(myParam);
        return "ok";
    }

    public IActionResult Download(string fileName)
    {
        byte[] fileBytes = System.IO.File.ReadAllBytes(fileName);
        return null;
    }
}

