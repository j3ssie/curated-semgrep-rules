// License: LGPL-3.0 License (c) find-sec-bugs
package random;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.util.Random;

public class PseudoRandom {
    static String randomVal =  Long.toHexString(new Random().nextLong());

    public static String generateSecretToken() {
        Random r = new Random();
        return Long.toHexString(r.nextLong());
    }

    public static String count() {
        return RandomStringUtils.random(10);
    }

    public static long getRandomVal() {
        return RandomUtils.nextLong();
    }
}
