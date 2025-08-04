package sjq.bitcoin.monetary;

import sjq.bitcoin.utility.ByteUtils;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class Coin {

    public static final long ONE_COIN = 100_000_000;

    public static Coin ZERO = new Coin(0);

    public static Coin ONE = new Coin(ONE_COIN);

    public static Coin NEGATIVE = new Coin(-1);

    private long value;

    private Coin(long value) {
        this.value = value;
    }

    public static Coin of(long value) {
        return new Coin(value);
    }

    public static Coin read(ByteBuffer buffer) throws BufferUnderflowException {
        long value = ByteUtils.readInt64LE(buffer);
        return new Coin(value);
    }

    public ByteBuffer write(ByteBuffer buffer) {
        ByteUtils.writeInt64LE(value, buffer);
        return buffer;
    }

    public Coin multiply(final long factor) {
        long value = Math.multiplyExact(this.value, factor);
        return new Coin(value);
    }

    public Coin divide(final long factor) {
        long value = this.value/factor;
        return new Coin(value);
    }

    public long satoshiValue() {
        return value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
