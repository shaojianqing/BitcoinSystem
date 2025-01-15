package sjq.bitcoin.crypto;

import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.utility.ByteUtils;
import sjq.bitcoin.utility.HexUtils;

import java.math.BigInteger;
import java.security.Security;

public class ECDSAKey {

    public static final String CURVE_NAME = "secp256k1";

    public static final X9ECParameters CURVE_PARAMS;

    public static final ECDomainParameters CURVE_INSTANCE;

    private static final boolean PUBKEY_COMPRESSED = true;

    private byte[] privateKey;

    private byte[] publicKey;

    static {
        Security.addProvider(new BouncyCastleProvider());
        CURVE_PARAMS = CustomNamedCurves.getByName(CURVE_NAME);
        CURVE_INSTANCE = new ECDomainParameters(CURVE_PARAMS.getCurve(),
                CURVE_PARAMS.getG(), CURVE_PARAMS.getN(), CURVE_PARAMS.getH());
    }

    private ECDSAKey() {
    }

    public static ECDSAKey getInstance(byte[] privateKey) {
        BigInteger privateKeyValue = ByteUtils.bytesToBigInteger(privateKey);
        FixedPointCombMultiplier multiplier = new FixedPointCombMultiplier();
        ECPoint point = multiplier.multiply(CURVE_INSTANCE.getG(), privateKeyValue);

        ECDSAKey ecdsaKey = new ECDSAKey();
        ecdsaKey.privateKey = privateKey;
        ecdsaKey.publicKey = point.getEncoded(PUBKEY_COMPRESSED);

        return ecdsaKey;
    }

    public ECDSASignature calculateSignature(Hash hash) {
        return calculateSignature(hash.byteData());
    }

    public ECDSASignature calculateSignature(byte[] data) {
        BigInteger privateKeyValue = ByteUtils.bytesToBigInteger(privateKey);
        ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));
        ECPrivateKeyParameters privKey = new ECPrivateKeyParameters(privateKeyValue, CURVE_INSTANCE);
        signer.init(true, privKey);
        BigInteger[] components = signer.generateSignature(data);
        return new ECDSASignature(components[0], components[1]);
    }


    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }


}
