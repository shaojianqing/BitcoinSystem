package sjq.bitcoin.crypto;

import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.math.ec.ECPoint;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.utility.HexUtils;

public class ECDSATool {

    public static boolean verifySignature(Hash hash, byte[] publicKey, ECDSASignature signature) {
        return verifySignature(hash.byteData(), publicKey, signature);
    }

    public static boolean verifySignature(byte[] data, byte[] publicKey, ECDSASignature signature) {
        ECDSASigner signer = new ECDSASigner();
        ECPoint point = ECDSAKey.CURVE_INSTANCE.getCurve().decodePoint(publicKey);
        ECPublicKeyParameters params = new ECPublicKeyParameters(point, ECDSAKey.CURVE_INSTANCE);
        signer.init(false, params);
        return signer.verifySignature(data, signature.r, signature.s);
    }

    public static void main(String[] args) {
        try {

            byte[] hash = HexUtils.parseHex("915eefcd223e4eca132aa0f85b8db8c3c1fb36eaab523c202fa3ba4eef52bc4b");

            ECDSAKey ecdsaKey = ECDSAKey.getInstance(hash);

            System.out.println(HexUtils.formatHex(ecdsaKey.getPrivateKey()));
            System.out.println(HexUtils.formatHex(ecdsaKey.getPublicKey()));

            byte[] content = "shaojianqing".getBytes();
            ECDSASignature signature = ecdsaKey.calculateSignature(content);
            boolean success = ECDSATool.verifySignature(content, ecdsaKey.getPublicKey(), signature);

            System.out.println("verify success:" + success);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
