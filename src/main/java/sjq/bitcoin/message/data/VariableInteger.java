package sjq.bitcoin.message.data;

import sjq.bitcoin.utility.ByteUtils;
import sjq.bitcoin.utility.IntegerUtils;
import sjq.bitcoin.utility.ShortUtils;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class VariableInteger {
    private final long value;
    private final int originalSize;
    private static final int SIZE_BYTE = 1; // 1 data byte
    private static final int SIZE_SHORT = 1 + 2; // 1 marker + 2 data bytes
    private static final int SIZE_INT = 1 + 4; // 1 marker + 4 data bytes
    private static final int SIZE_LONG = 1 + 8; // 1 marker + 8 data bytes

    /**
     * Constructs a new VarInt with the given unsigned long value.
     *
     * @param value the unsigned long value (beware widening conversion of negatives!)
     */
    public static VariableInteger of(long value) {
        return new VariableInteger(value, sizeOf(value));
    }

    /**
     * Constructs a new VarInt with the value parsed from the specified offset of the given buffer.
     *
     * @param buf the buffer containing the value
     * @param offset the offset of the value
     * @throws ArrayIndexOutOfBoundsException if offset points outside of the buffer, or
     *                                        if the value doesn't fit the remaining buffer
     */
    public static VariableInteger ofBytes(byte[] buf, int offset) throws ArrayIndexOutOfBoundsException {
        return read(ByteBuffer.wrap(buf, offset, buf.length - offset));
    }

    /**
     * Constructs a new VarInt by reading from the given buffer.
     *
     * @param buf buffer to read from
     * @throws BufferUnderflowException if the read value extends beyond the remaining bytes of the buffer
     */
    public static VariableInteger read(ByteBuffer buf) throws BufferUnderflowException {
        buf.order(ByteOrder.LITTLE_ENDIAN);
        int first = ByteUtils.toUnsignedInt(buf.get());
        long value;
        int originallyEncodedSize;
        if (first < 253) {
            value = first;
            originallyEncodedSize = SIZE_BYTE;
        } else if (first == 253) {
            value = ShortUtils.toUnsignedInt(buf.getShort());
            originallyEncodedSize = SIZE_SHORT;
        } else if (first == 254) {
            value = IntegerUtils.toUnsignedLong(buf.getInt());
            originallyEncodedSize = SIZE_INT;
        } else {
            value = buf.getLong();
            originallyEncodedSize = SIZE_LONG;
        }
        return new VariableInteger(value, originallyEncodedSize);
    }

    private VariableInteger(long value, int originallyEncodedSize) {
        this.value = value;
        this.originalSize = originallyEncodedSize;
    }

    /** @deprecated use {@link #of(long)} */
    @Deprecated
    public VariableInteger(long value) {
        this.value = value;
        this.originalSize = getSizeInBytes();
    }

    /** @deprecated use {@link #ofBytes(byte[], int)} */
    @Deprecated
    public VariableInteger(byte[] buf, int offset) {
        VariableInteger copy = read(ByteBuffer.wrap(buf, offset, buf.length));
        value = copy.value;
        this.originalSize = copy.originalSize;
    }

    /**
     * Gets the value as a long. For values greater than {@link Long#MAX_VALUE} the returned long
     * will be negative. It is still to be interpreted as an unsigned value.
     *
     * @return value as a long
     */
    public long longValue() {
        return value;
    }

    /**
     * Determine if the value would fit an int, i.e. it is in the range of {@code 0} to {@link Integer#MAX_VALUE}.
     * If this is true, it's safe to call {@link #intValue()}.
     *
     * @return true if the value fits an int, false otherwise
     */
    public boolean fitsInt() {
        return value >= 0 && value <= Integer.MAX_VALUE;
    }

    /**
     * Gets the value as an unsigned int in the range of {@code 0} to {@link Integer#MAX_VALUE}.
     *
     * @return value as an unsigned int
     * @throws ArithmeticException if the value doesn't fit an int
     */
    public int intValue() throws ArithmeticException {
        return (int) value;
    }

    /**
     * Returns the original number of bytes used to encode the value if it was
     * deserialized from a byte array, or the minimum encoded size if it was not.
     */
    public int getOriginalSizeInBytes() {
        return originalSize;
    }

    /**
     * Returns the minimum encoded size of the value.
     */
    public final int getSizeInBytes() {
        return sizeOf(value);
    }

    /**
     * Returns the minimum encoded size of the given unsigned long value.
     *
     * @param value the unsigned long value (beware widening conversion of negatives!)
     */
    public static int sizeOf(long value) {
        // if negative, it's actually a very large unsigned long value
        if (value < 0) return SIZE_LONG;
        if (value < 253) return SIZE_BYTE;
        if (value <= 0xFFFFL) return SIZE_SHORT;
        if (value <= 0xFFFFFFFFL) return SIZE_INT;
        return SIZE_LONG;
    }

    /**
     * Allocates a byte array and serializes the value into its minimal representation.
     *
     * @return the minimal encoded bytes of the value
     */
    public byte[] serialize() {
        ByteBuffer buf = ByteBuffer.allocate(sizeOf(value));
        return write(buf).array();
    }

    /**
     * Write encoded value into the given buffer.
     *
     * @param buf buffer to write into
     * @return the buffer
     * @throws BufferOverflowException if the value doesn't fit the remaining buffer
     */
    public ByteBuffer write(ByteBuffer buf) throws BufferOverflowException {
        buf.order(ByteOrder.LITTLE_ENDIAN);
        switch (sizeOf(value)) {
            case 1:
                buf.put((byte) value);
                break;
            case 3:
                buf.put((byte) 253);
                buf.putShort((short) value);
                break;
            case 5:
                buf.put((byte) 254);
                buf.putInt((int) value);
                break;
            default:
                buf.put((byte) 255);
                buf.putLong(value);
                break;
        }
        return buf;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        // originalSize is not considered on purpose
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return value == ((VariableInteger) o).value;
    }

    @Override
    public int hashCode() {
        return new Long(value).hashCode();
    }
}
