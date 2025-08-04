package sjq.bitcoin.crypto;

import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.math.ec.ECPoint;
import sjq.bitcoin.hash.Hash;

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
}
