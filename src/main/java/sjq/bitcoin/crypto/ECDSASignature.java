package sjq.bitcoin.crypto;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.util.Properties;
import sjq.bitcoin.crypto.exception.SignatureDecodeException;

import java.io.IOException;
import java.math.BigInteger;

import static sjq.bitcoin.crypto.ECDSAKey.HALF_CURVE_ORDER;

public class ECDSASignature {

    protected final BigInteger r;

    protected final BigInteger s;

    public ECDSASignature(BigInteger r, BigInteger s) {
        this.r = r;
        this.s = s;
    }

    public static ECDSASignature decodeFromDER(byte[] signature) throws SignatureDecodeException {
        ASN1InputStream decoder = null;
        try {
            // BouncyCastle by default is strict about parsing ASN.1 integers. We relax this check, because some
            // Bitcoin signatures would not parse.
            Properties.setThreadOverride("org.bouncycastle.asn1.allow_unsafe_integer", true);
            decoder = new ASN1InputStream(signature);
            final ASN1Primitive seqObj = decoder.readObject();
            if (seqObj == null)
                throw new SignatureDecodeException("Reached past end of ASN.1 stream.");
            if (!(seqObj instanceof DLSequence))
                throw new SignatureDecodeException("Read unexpected class: " + seqObj.getClass().getName());
            final DLSequence seq = (DLSequence) seqObj;
            ASN1Integer r, s;
            try {
                r = (ASN1Integer) seq.getObjectAt(0);
                s = (ASN1Integer) seq.getObjectAt(1);
            } catch (ClassCastException e) {
                throw new SignatureDecodeException(e);
            }
            // OpenSSL deviates from the DER spec by interpreting these values as unsigned, though they should not be
            // Thus, we always use the positive versions. See: http://r6.ca/blog/20111119T211504Z.html
            return new ECDSASignature(r.getPositiveValue(), s.getPositiveValue());
        } catch (IOException e) {
            throw new SignatureDecodeException(e);
        } finally {
            if (decoder != null)
                try { decoder.close(); } catch (IOException x) {}
            Properties.removeThreadOverride("org.bouncycastle.asn1.allow_unsafe_integer");
        }
    }

    public boolean isCanonical() {
        return s.compareTo(HALF_CURVE_ORDER) <= 0;
    }

    public BigInteger getR() {
        return r;
    }

    public BigInteger getS() {
        return s;
    }
}
