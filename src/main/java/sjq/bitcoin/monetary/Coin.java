package sjq.bitcoin.monetary;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Coin {

    public static Coin ZERO = new Coin(0);

    private long value;

    public Coin(long value) {
        this.value = value;
    }

    public static Coin read(ByteBuffer buf) throws BufferUnderflowException {
        long value = buf.order(ByteOrder.LITTLE_ENDIAN).getLong();
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
