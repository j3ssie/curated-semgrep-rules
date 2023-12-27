// License: LGPL-3.0 License (c) find-sec-bugs

package crypto;

import java.security.*;
import java.security.spec.RSAKeyGenParameterSpec;

/**
 * The key size might need to be adjusted in the future.
 * http://en.wikipedia.org/wiki/Key_size#Asymmetric_algorithm_key_lengths
 */
public class InsufficientKeySizeRsa {

    public KeyPair weakKeySize1() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512); //BAD

        return keyGen.generateKeyPair();
    }

    public KeyPair weakKeySize2() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(128, new SecureRandom()); //BAD //Different signature

        return keyGen.generateKeyPair();
    }


    public KeyPair weakKeySize3ParameterSpec() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(new RSAKeyGenParameterSpec(128,RSAKeyGenParameterSpec.F4)); //BAD

        KeyPair key = keyGen.generateKeyPair();
        return key;
    }

    public KeyPair weakKeySize4ParameterSpec() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(new RSAKeyGenParameterSpec(128,RSAKeyGenParameterSpec.F4), new SecureRandom()); //BAD

        KeyPair key = keyGen.generateKeyPair();
        return key;
    }

    public KeyPair weakKeySize5Recommended() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024); //BAD with lower priority

        return keyGen.generateKeyPair();
    }

    public KeyPair okKeySizeParameterSpec() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(new RSAKeyGenParameterSpec(2048,RSAKeyGenParameterSpec.F4)); //Different signature

        return keyGen.generateKeyPair();
    }

    public KeyPair weakKeySizeWithProviderString() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BC");
        keyGen.initialize(1024); //BAD with lower priority

        return keyGen.generateKeyPair();
    }

    public KeyPair weakKeySizeWithProviderObject1() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", new ExampleProvider());
        keyGen.initialize(1024); //BAD with lower priority

        return keyGen.generateKeyPair();
    }

    public KeyPair weakKeySizeWithProviderObject2() throws NoSuchAlgorithmException {
        Provider p = new ExampleProvider("info");
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", p);
        keyGen.initialize(1024); //BAD with lower priority

        return keyGen.generateKeyPair();
    }

    public KeyPair strongKeySizeWithProviderString() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BC");
        keyGen.initialize(2048); // OK: n >= 2048

        return keyGen.generateKeyPair();
    }


    private class ExampleProvider extends Provider {
        ExampleProvider() {
            this("example");
        }

        ExampleProvider(String info) {
            super("example", 0.0, info);
        }
    }

}
