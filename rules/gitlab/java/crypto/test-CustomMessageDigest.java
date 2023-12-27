// License: LGPL-3.0 License (c) find-sec-bugs
package crypto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;

public class CustomMessageDigest extends MessageDigest {
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    protected CustomMessageDigest() {
        super("WEAK");
    }

    @Override
    protected void engineUpdate(byte input) {
        buffer.write(input);
    }

    @Override
    protected void engineUpdate(byte[] input, int offset, int len) {
        try {
            buffer.write(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected byte[] engineDigest() {
        byte[] content = buffer.toByteArray();
        return Arrays.copyOf(content, 8);
    }

    @Override
    protected void engineReset() {
        buffer.reset();
    }

}
