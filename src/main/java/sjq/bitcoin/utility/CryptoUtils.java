package sjq.bitcoin.utility;

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
}
