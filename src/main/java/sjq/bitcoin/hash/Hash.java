package sjq.bitcoin.hash;

import sjq.bitcoin.utility.ByteUtils;
import sjq.bitcoin.utility.HexUtils;

import java.nio.ByteBuffer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Hash {

    public static final int HASH_LENGTH = 32;

    public static final Hash ZERO_HASH = wrap(new byte[HASH_LENGTH]);

    private byte[] value;

    private Hash(byte[] rawBytes) {
        checkHash(rawBytes);
        value = rawBytes;
    }

    private void checkHash(byte[] rawBytes) {
        if (rawBytes.length != HASH_LENGTH) {
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

    public byte[] byteData() {
        return value;
    }

    public byte[] serialize() {
        return ByteUtils.reverseBytes(value);
    }

    public static byte[] calculateTwice(byte[] content) {
        MessageDigest digest = newSha256Digest();
        return digest.digest(digest.digest(content));
    }

    public static byte[] calculateTwice(byte[] content1, byte[] content2) {
        MessageDigest digest = newSha256Digest();
        digest.update(content1);
        digest.update(content2);
        return digest.digest(digest.digest());
    }

    public static MessageDigest newSha256Digest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String hexValue() {
        return HexUtils.formatHex(value);
    }

    @Override
    public String toString() {
        return HexUtils.formatHex(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hash hash = (Hash) o;
        return Arrays.equals(value, hash.value);
    }
}
