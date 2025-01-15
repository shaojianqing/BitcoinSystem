package sjq.bitcoin.crypto;

import java.math.BigInteger;

public class ECDSASignature {

    public final BigInteger r;

    public final BigInteger s;

    public ECDSASignature(BigInteger r, BigInteger s) {
        this.r = r;
        this.s = s;
    }
}
