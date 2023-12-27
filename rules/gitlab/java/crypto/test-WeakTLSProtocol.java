// License: LGPL-3.0 License (c) find-sec-bugs
package crypto;

import java.security.NoSuchAlgorithmException;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

public class WeakTLSProtocol {

    public static void main(String[] args) {
        new DefaultHttpClient(); // BAD

        try {
          SSLContext.getInstance("SSL"); // BAD
          SSLContext.getInstance("TLS"); // BAD

          SSLContext.getInstance("TLSv1.0"); // WARN - should be v1.2 or above
          SSLContext.getInstance("TLSv1.1"); // WARN - should be v1.2 or above
          SSLContext.getInstance("DTLSv1.0"); // WARN - should be v1.2 or above
          SSLContext.getInstance("DTLSv1.1"); // WARN - should be v1.2 or above
          SSLContext.getInstance("TLSv1.2"); // OK
          SSLContext.getInstance("TLSv1.3"); // OK
        } catch (NoSuchAlgorithmException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }

    public static void danger2() {
        try {
          SSLContext serverSslContext = SSLContext.getInstance("TLS");
          SSLEngine serverEngine = serverSslContext.createSSLEngine();
          serverEngine.setEnabledProtocols(new String[]{"TLSv1.1"});
        } catch (NoSuchAlgorithmException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }

    public static void danger3() {
        try {
          SSLContext serverSslContext = SSLContext.getInstance("TLSv1.2");
          SSLEngine serverEngine = serverSslContext.createSSLEngine();
          serverEngine.setEnabledProtocols(new String[]{"TLSv1.1"}); // STILL DANGER SINCE OVERRIDE
        } catch (NoSuchAlgorithmException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }

    public static void ok1() {
        try {
          SSLContext serverSslContext = SSLContext.getInstance("TLS");
          SSLEngine serverEngine = serverSslContext.createSSLEngine();
          serverEngine.setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.3"});
        } catch (NoSuchAlgorithmException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }

    public static void ok2() {
        try {
          SSLContext serverSslContext = SSLContext.getDefault();
          SSLEngine serverEngine = serverSslContext.createSSLEngine();
          serverEngine.setEnabledProtocols(new String[]{"TLSv1.2"});
        } catch (NoSuchAlgorithmException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }

    public static void ok3() {
        try {
          SSLContext serverSslContext = SSLContext.getInstance("TLSv1.1");
          SSLEngine serverEngine = serverSslContext.createSSLEngine();
          serverEngine.setEnabledProtocols(new String[]{"DTLSv1.2"}); // OVERRIDES UNSAFE VERSION
        } catch (NoSuchAlgorithmException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }

}
