// License: MIT Copyright (c) 2022-Present GitLab B.V.
using System.Security.Cryptography;

class WeakCipherMode
{
  public static readonly int BlockBitSize = 128;
  public static readonly int KeyBitSize = 256;

  static void WeakCipher()
  {
    new AesManaged
    {
      KeySize = KeyBitSize,
      BlockSize = BlockBitSize,
      Mode = CipherMode.OFB,
      Padding = PaddingMode.PKCS7
    };

    var des = DES.Create();

    des.Mode = CipherMode.CTS;
  }
}