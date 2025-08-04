package sjq.bitcoin.crypto.transaction;

import sjq.bitcoin.crypto.ECDSASignature;
import sjq.bitcoin.crypto.exception.SignatureDecodeException;

import java.math.BigInteger;

public class TransactionSignature extends ECDSASignature {

    private final SignatureHashType type;

    private TransactionSignature(BigInteger r, BigInteger s, SignatureHashType type) {
        super(r, s);
        this.type = type;
    }

    public static TransactionSignature decode(byte[] signature,
                                              boolean requireCanonicalSignature,
                                              boolean requireCanonicalSValue) throws SignatureDecodeException {
        ECDSASignature ecdsaSignature = ECDSASignature.decodeFromDER(signature);
        int signatureLength = signature.length;
        byte signatureHash = signature[signatureLength - 1];
        SignatureHashType signatureHashType = SignatureHashType.getByValue(signatureHash);
        return new TransactionSignature(ecdsaSignature.getR(), ecdsaSignature.getS(), signatureHashType);
    }

    public boolean isCanonicalSignature(byte[] signature) {
        return true;
    }

    public SignatureHashType getType() {
        return type;
    }
}
