// License: LGPL-3.0 License (c) find-sec-bugs
package strings;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BadHexConversion {

    public String danger(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] resultBytes = md.digest(text.getBytes("UTF-8"));

        StringBuilder stringBuilder = new StringBuilder();
        for(byte b :resultBytes) {
            stringBuilder.append( Integer.toHexString( b ) );
        }
        return stringBuilder.toString();
    }


    public String danger2(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] resultBytes = md.digest(text.getBytes("UTF-8"));

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, resultBytesLength = resultBytes.length; i < resultBytesLength; i++) {
            byte b = resultBytes[i];
            stringBuilder.append(Integer.toHexString(b));
        }
        return stringBuilder.toString();
    }
}
