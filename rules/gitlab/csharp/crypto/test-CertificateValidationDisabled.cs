// License: LGPL-3.0 License (c) security-code-scan
using System.Net;

class CertificateValidationDisabled
{
  static void CustomValidationCallback()
  {
    ServicePointManager.ServerCertificateValidationCallback += (sender, cert, chain, sslPolicyErrors) => true;
  }

  // Meant to simulate a legitimate custom cert validation function (one that's not hardcoded to return
  // true).
  static void CustomValidationWithImplementation()
  {
    ServicePointManager.ServerCertificateValidationCallback += (sender, cert, chain, sslPolicyErrors) =>
    {
      Random rnd = new Random();

      return rnd.Next(1) == 1;
    };
  }
}