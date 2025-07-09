package sjq.bitcoin.utility;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import sjq.bitcoin.hash.Hash;

import java.security.MessageDigest;

public class CryptoUtils {

    public static byte[] sha256(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(data);
    }

    public static byte[] hash256(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] result = digest.digest(data);
        return digest.digest(result);
    }

    public static byte[] sha256ToHash160(byte[] input) {
        byte[] sha256 = Hash.calculate(input);
        return digestRipeMd160(sha256);
    }

    public static byte[] digestRipeMd160(byte[] input) {
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(input, 0, input.length);
        byte[] ripmemdHash = new byte[20];
        digest.doFinal(ripmemdHash, 0);
        return ripmemdHash;
    }
}
