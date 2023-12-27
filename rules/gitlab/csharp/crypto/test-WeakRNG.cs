// License: LGPL-3.0 License (c) security-code-scan
using System.Security.Cryptography;

class WeakRNG
{
    static string UnsafeRNG()
    {
        var rnd = new Random();
        byte[] buffer = new byte[16];
        rnd.NextBytes(buffer);
        rnd.Next();
        rnd.NextDouble();
        // ...and so forth.
        return BitConverter.ToString(buffer);
    }
    
    static string Safe()
    {
        var rnd = RandomNumberGenerator.Create();
        byte[] buffer = new byte[16];
        rnd.GetBytes(buffer);
        return BitConverter.ToString(buffer);
    }
}