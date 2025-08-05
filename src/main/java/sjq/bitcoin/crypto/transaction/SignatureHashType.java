package sjq.bitcoin.crypto.transaction;

public enum SignatureHashType {
    ALL((byte)1),

    NONE((byte)2),

    SINGLE((byte)3),

    ANYONECANPAY((byte)0x80),

    ANYONECANPAY_ALL((byte)0x81),

    ANYONECANPAY_NONE((byte)0x82),

    ANYONECANPAY_SINGLE((byte)0x83),

    UNSET((byte)0);

    public final byte value;

    SignatureHashType(byte value) {
        this.value = value;
    }

    public static SignatureHashType getByValue(byte value) {
        for (SignatureHashType type:values()) {
            if (type.value == value) {
                return type;
            }
        }
        return SignatureHashType.UNSET;
    }
}
