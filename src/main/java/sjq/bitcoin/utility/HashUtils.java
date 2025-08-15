package sjq.bitcoin.utility;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import sjq.bitcoin.hash.Hash;

import java.security.MessageDigest;

public class HashUtils {

    private static final String SHA_1 = "SHA-1";

    private static final String SHA_256 = "SHA-256";

    public static byte[] sha1(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(SHA_1);
        return digest.digest(data);
    }

    public static byte[] sha256(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(SHA_256);
        return digest.digest(data);
    }

    public static byte[] hash256(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(SHA_256);
        byte[] result = digest.digest(data);
        return digest.digest(result);
    }

    public static byte[] sha256ToHash160(byte[] input) {
        byte[] sha256 = Hash.calculate(input);
        return ripeMd160(sha256);
    }

    public static byte[] ripeMd160(byte[] input) {
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(input, 0, input.length);
        byte[] ripeMdHash = new byte[20];
        digest.doFinal(ripeMdHash, 0);
        return ripeMdHash;
    }
}
