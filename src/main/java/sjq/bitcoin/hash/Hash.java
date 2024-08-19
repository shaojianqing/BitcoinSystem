package sjq.bitcoin.hash;

import sjq.bitcoin.utility.ByteUtils;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Hash {

    public static final int HASH_LENGTH = 32;

    private byte[] value = new byte[HASH_LENGTH];

    private Hash(byte[] rawBytes) {
        checkHash(rawBytes);
        value = rawBytes;
    }

    private void checkHash(byte[] rawBytes) {
        if (rawBytes.length!=HASH_LENGTH) {
            throw new IllegalArgumentException("hash length is not correct!");
        }
    }

    public static Hash wrap(byte[] rawBytes) {
        return new Hash(rawBytes);
    }

    public static Hash wrapReversed(byte[] rawBytes) {
        return wrap(ByteUtils.reverseBytes(rawBytes));
    }

    public static Hash read(ByteBuffer buffer) {
        byte[] rawBytes = new byte[HASH_LENGTH];
        buffer.get(rawBytes);
        rawBytes = ByteUtils.reverseBytes(rawBytes);
        return new Hash(rawBytes);
    }

    public static Hash calculate(byte[] content) {
        MessageDigest digest = newSha256Digest();
        digest.update(content, 0, content.length);
        return new Hash(digest.digest());
    }

    public static byte[] calculateTwice(byte[] content) {
        MessageDigest digest = newSha256Digest();
        digest.update(content, 0, content.length);
        return digest.digest(digest.digest());
    }

    public static MessageDigest newSha256Digest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);  // Can't happen.
        }
    }
}
