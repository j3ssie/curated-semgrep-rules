// License: LGPL-3.0 License (c) security-code-scan
using System.Security.Cryptography;

class WeakHashingFunction
{
    static void SHA1()
    {
        var str = new byte[10];
        var hashProvider = new SHA1CryptoServiceProvider();
        hashProvider.ComputeHash(str);

        System.Security.Cryptography.SHA1.Create();
    }

    static void MD5()
    {
        var str = new byte[10];
        var hashProvider = new MD5CryptoServiceProvider();
        hashProvider.ComputeHash(str);

        System.Security.Cryptography.MD5.Create();
    }
    
    static void Safe()
    {
        var str = new byte[10];
        var hashProvider = SHA256Managed.Create();
        var hash = hashProvider.ComputeHash(str);
    }
}