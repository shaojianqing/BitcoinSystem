/*
 * Copyright by the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sjq.bitcoin.utility;

import sjq.bitcoin.message.data.VariableInteger;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


/**
 * Utility methods for bit, byte, and integer manipulation and conversion. Most of these were moved here
 * from {@code org.bitcoinj.core.Utils}.
 */
public class ByteUtils {

    /**
     * Converts byte into a unsigned integer
     *
     * @param x to convert into a unsigned integer
     * @return the converted unsigned integer
     */
    public static int toUnsignedInt(byte x) {
        int result = x & 0xFF;
        return result;
    }

    /**
     * Converts an array of bytes into a positive BigInteger. This is the inverse of
     * {@link #bytesToBigInteger(byte[])}.
     *
     * @param bytes to convert into a BigInteger
     * @return the converted BigInteger
     */
    public static BigInteger bytesToBigInteger(byte[] bytes) {
        return new BigInteger(1, bytes);
    }

    /**
     * Write a 16-bit integer to a given buffer in little-endian format.
     * <p>
     * The value is expected as an unsigned {@code int} as per the Java Unsigned Integer API.
     *
     * @param val value to be written
     * @param buf buffer to be written into
     * @return the buffer
     * @throws BufferOverflowException if the value doesn't fit the remaining buffer
     */
    public static ByteBuffer writeInt16LE(int val, ByteBuffer buf) throws BufferOverflowException {
        return buf.order(ByteOrder.LITTLE_ENDIAN).putShort((short) val);
    }

    /**
     * Write a 16-bit integer to a given buffer in big-endian format.
     * <p>
     * The value is expected as an unsigned {@code int} as per the Java Unsigned Integer API.
     *
     * @param val value to be written
     * @param buf buffer to be written into
     * @return the buffer
     * @throws BufferOverflowException if the value doesn't fit the remaining buffer
     */
    public static ByteBuffer writeInt16BE(int val, ByteBuffer buf) throws BufferOverflowException {
        return buf.order(ByteOrder.BIG_ENDIAN).putShort((short) val);
    }

    /**
     * Write a 32-bit integer to a given buffer in little-endian format.
     * <p>
     * The value is expected as a signed or unsigned {@code int}. If you've got an unsigned {@code long} as per the
     * Java Unsigned Integer API, use {@link #writeInt32LE(long, ByteBuffer)}.
     *
     * @param val value to be written
     * @param buf buffer to be written into
     * @return the buffer
     * @throws BufferOverflowException if the value doesn't fit the remaining buffer
     */
    public static ByteBuffer writeInt32LE(int val, ByteBuffer buf) throws BufferOverflowException {
        return buf.order(ByteOrder.LITTLE_ENDIAN).putInt(val);
    }

    /**
     * Write a 32-bit integer to a given buffer in little-endian format.
     * <p>
     * The value is expected as an unsigned {@code long} as per the Java Unsigned Integer API.
     *
     * @param val value to be written
     * @param buf buffer to be written into
     * @return the buffer
     * @throws BufferOverflowException if the value doesn't fit the remaining buffer
     */
    public static ByteBuffer writeInt32LE(long val, ByteBuffer buf) throws BufferOverflowException {
        return buf.order(ByteOrder.LITTLE_ENDIAN).putInt((int) val);
    }

    /**
     * Write a 32-bit integer to a given byte array in little-endian format, starting at a given offset.
     * <p>
     * The value is expected as an unsigned {@code long} as per the Java Unsigned Integer API.
     *
     * @param val    value to be written
     * @param out    buffer to be written into
     * @param offset offset into the buffer
     * @throws ArrayIndexOutOfBoundsException if offset points outside of the buffer, or
     *                                        if the value doesn't fit the remaining buffer
     */
    public static void writeInt32LE(long val, byte[] out, int offset) throws ArrayIndexOutOfBoundsException {
        writeInt32LE(val, ByteBuffer.wrap(out, offset, out.length - offset));
    }

    /**
     * Write a 32-bit integer to a given buffer in big-endian format.
     * <p>
     * The value is expected as a signed or unsigned {@code int}.
     *
     * @param val value to be written
     * @param buf buffer to be written into
     * @return the buffer
     * @throws BufferOverflowException if the value doesn't fit the remaining buffer
     */
    public static ByteBuffer writeInt32BE(int val, ByteBuffer buf) throws BufferOverflowException {
        return buf.order(ByteOrder.BIG_ENDIAN).putInt((int) val);
    }

    /**
     * Write a 32-bit integer to a given byte array in big-endian format, starting at a given offset.
     * <p>
     * The value is expected as a signed or unsigned {@code int}.
     *
     * @param val    value to be written
     * @param out    buffer to be written into
     * @param offset offset into the buffer
     * @throws ArrayIndexOutOfBoundsException if offset points outside of the buffer, or
     *                                        if the value doesn't fit the remaining buffer
     */
    public static void writeInt32BE(int val, byte[] out, int offset) throws ArrayIndexOutOfBoundsException {
        writeInt32BE(val, ByteBuffer.wrap(out, offset, out.length - offset));
    }

    /**
     * Write a 64-bit integer to a given buffer in little-endian format.
     * <p>
     * The value is expected as a signed or unsigned {@code long}.
     *
     * @param val value to be written
     * @param buf buffer to be written into
     * @return the buffer
     * @throws BufferOverflowException if the value doesn't fit the remaining buffer
     */
    public static ByteBuffer writeInt64LE(long val, ByteBuffer buf) throws BufferOverflowException {
        return buf.order(ByteOrder.LITTLE_ENDIAN).putLong(val);
    }

    /**
     * Write a 64-bit integer to a given byte array in little-endian format, starting at a given offset.
     * <p>
     * The value is expected as a signed or unsigned {@code long}.
     *
     * @param val    value to be written
     * @param out    buffer to be written into
     * @param offset offset into the buffer
     * @throws ArrayIndexOutOfBoundsException if offset points outside of the buffer, or
     *                                        if the value doesn't fit the remaining buffer
     */
    public static void writeInt64LE(long val, byte[] out, int offset) throws ArrayIndexOutOfBoundsException {
        writeInt64LE(val, ByteBuffer.wrap(out, offset, out.length - offset));
    }

    /**
     * Write a 16-bit integer to a given output stream in little-endian format.
     * <p>
     * The value is expected as an unsigned {@code int} as per the Java Unsigned Integer API.
     *
     * @param val    value to be written
     * @param stream stream to be written into
     * @throws IOException if an I/O error occurs
     */
    public static void writeInt16LE(int val, OutputStream stream) throws IOException {
        byte[] buf = new byte[2];
        writeInt16LE(val, ByteBuffer.wrap(buf));
        stream.write(buf);
    }

    /**
     * Write a 16-bit integer to a given output stream in big-endian format.
     * <p>
     * The value is expected as an unsigned {@code int} as per the Java Unsigned Integer API.
     *
     * @param val    value to be written
     * @param stream stream to be written into
     * @throws IOException if an I/O error occurs
     */
    public static void writeInt16BE(int val, OutputStream stream) throws IOException {
        byte[] buf = new byte[2];
        writeInt16BE(val, ByteBuffer.wrap(buf));
        stream.write(buf);
    }

    /**
     * Write a 32-bit integer to a given output stream in little-endian format.
     * <p>
     * The value is expected as a signed or unsigned {@code int}. If you've got an unsigned {@code long} as per the
     * Java Unsigned Integer API, use {@link #writeInt32LE(long, OutputStream)}.
     *
     * @param val    value to be written
     * @param stream stream to be written into
     * @throws IOException if an I/O error occurs
     */
    public static void writeInt32LE(int val, OutputStream stream) throws IOException {
        byte[] buf = new byte[4];
        writeInt32LE(val, ByteBuffer.wrap(buf));
        stream.write(buf);
    }

    /**
     * Write a 32-bit integer to a given output stream in little-endian format.
     * <p>
     * The value is expected as an unsigned {@code long} as per the Java Unsigned Integer API.
     *
     * @param val    value to be written
     * @param stream stream to be written into
     * @throws IOException if an I/O error occurs
     */
    public static void writeInt32LE(long val, OutputStream stream) throws IOException {
        byte[] buf = new byte[4];
        writeInt32LE(val, ByteBuffer.wrap(buf));
        stream.write(buf);
    }

    /**
     * Write a 32-bit integer to a given output stream in big-endian format.
     * <p>
     * The value is expected as a signed or unsigned {@code int}.
     *
     * @param val    value to be written
     * @param stream stream to be written into
     * @throws IOException if an I/O error occurs
     */
    public static void writeInt32BE(int val, OutputStream stream) throws IOException {
        byte[] buf = new byte[4];
        writeInt32BE(val, ByteBuffer.wrap(buf));
        stream.write(buf);
    }

    /**
     * Write a 64-bit integer to a given output stream in little-endian format.
     * <p>
     * The value is expected as a signed or unsigned {@code long}.
     *
     * @param val    value to be written
     * @param stream stream to be written into
     * @throws IOException if an I/O error occurs
     */
    public static void writeInt64LE(long val, OutputStream stream) throws IOException {
        byte[] buf = new byte[8];
        writeInt64LE(val, ByteBuffer.wrap(buf));
        stream.write(buf);
    }

    /**
     * Write a 64-bit integer to a given output stream in little-endian format.
     * <p>
     * The value is expected as an unsigned {@link BigInteger}.
     *
     * @param val    value to be written
     * @param stream stream to be written into
     * @throws IOException if an I/O error occurs
     */
    public static void writeInt64LE(BigInteger val, OutputStream stream) throws IOException {
        byte[] bytes = val.toByteArray();
        if (bytes.length > 8) {
            throw new RuntimeException("Input too large to encode into a uint64");
        }
        bytes = reverseBytes(bytes);
        stream.write(bytes);
        if (bytes.length < 8) {
            for (int i = 0; i < 8 - bytes.length; i++)
                stream.write(0);
        }
    }

    /**
     * Read 2 bytes from the buffer as unsigned 16-bit integer in big endian format.
     * @param buf buffer to be read from
     * @throws BufferUnderflowException if the read value extends beyond the remaining bytes of the buffer
     */
    public static int readUint16BE(ByteBuffer buf) throws BufferUnderflowException {
        return Short.toUnsignedInt(buf.order(ByteOrder.BIG_ENDIAN).getShort());
    }

    /**
     * Read 4 bytes from the buffer as unsigned 32-bit integer in little endian format.
     * @param buf buffer to be read from
     * @throws BufferUnderflowException if the read value extends beyond the remaining bytes of the buffer
     */
    public static long readUint32LE(ByteBuffer buf) throws BufferUnderflowException {
        return Integer.toUnsignedLong(buf.order(ByteOrder.LITTLE_ENDIAN).getInt());
    }

    /**
     * Read 4 bytes from the byte array (starting at the offset) as signed 32-bit integer in little endian format.
     * @param buf buffer to be read from
     * @return read integer
     * @throws BufferUnderflowException if the read value extends beyond the remaining bytes of the buffer
     */
    public static int readInt32LE(ByteBuffer buf) throws BufferUnderflowException {
        return buf.order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    /**
     * Read 4 bytes from the byte array (starting at the offset) as signed 32-bit integer in big endian format.
     * @param buf buffer to be read from
     * @return read integer
     * @throws BufferUnderflowException if the read value extends beyond the remaining bytes of the buffer
     */
    public static int readInt32BE(ByteBuffer buf) throws BufferUnderflowException {
        return buf.order(ByteOrder.BIG_ENDIAN).getInt();
    }

    /**
     * Read 8 bytes from the buffer as signed 64-bit integer in little endian format.
     * @param buf buffer to be read from
     * @throws BufferUnderflowException if the read value extends beyond the remaining bytes of the buffer
     */
    public static long readInt64LE(ByteBuffer buf) throws BufferUnderflowException {
        return buf.order(ByteOrder.LITTLE_ENDIAN).getLong();
    }

    /**
     * Read 8 bytes from the byte array (starting at the offset) as signed 64-bit integer in little endian format.
     * @param bytes buffer to be read from
     * @param offset offset into the buffer
     * @throws ArrayIndexOutOfBoundsException if offset points outside of the buffer, or
     *                                        if the read value extends beyond the remaining bytes of the buffer
     */
    public static long readInt64LE(byte[] bytes, int offset) throws ArrayIndexOutOfBoundsException {
        return readInt64LE(ByteBuffer.wrap(bytes, offset, bytes.length - offset));
    }

    /**
     * First read a {@link VariableInteger} from the buffer and use it to determine the number of bytes to be read. Then read
     * that many bytes into the byte array to be returned. This construct is frequently used by Bitcoin protocols.
     *
     * @param buf buffer to read from
     * @return read bytes
     * @throws BufferUnderflowException if the read value extends beyond the remaining bytes of the buffer
     */
    public static byte[] readLengthPrefixedBytes(ByteBuffer buf) throws BufferUnderflowException {
        VariableInteger length = VariableInteger.read(buf);
        byte[] bytes = new byte[length.intValue()];
        buf.get(bytes);
        return bytes;
    }

    public static byte[] readBytesByLength(ByteBuffer buf, int length) throws BufferUnderflowException {
        byte[] bytes = new byte[length];
        buf.get(bytes);
        return bytes;
    }

    /**
     * Returns a copy of the given byte array in reverse order.
     */
    public static byte[] reverseBytes(byte[] bytes) {
        // We could use the XOR trick here but it's easier to understand if we don't. If we find this is really a
        // performance issue the matter can be revisited.
        byte[] buf = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++)
            buf[i] = bytes[bytes.length - 1 - i];
        return buf;
    }

    /**
     * MPI encoded numbers are produced by the OpenSSL BN_bn2mpi function. They consist of
     * a 4 byte big endian length field, followed by the stated number of bytes representing
     * the number in big endian format (with a sign bit).
     * @param includeLength indicates whether the 4 byte length field should be included
     */
    public static byte[] encodeMPI(BigInteger value, boolean includeLength) {
        if (value.equals(BigInteger.ZERO)) {
            if (!includeLength)
                return new byte[] {};
            else
                return new byte[] {0x00, 0x00, 0x00, 0x00};
        }
        boolean isNegative = value.signum() < 0;
        if (isNegative)
            value = value.negate();
        byte[] array = value.toByteArray();
        int length = array.length;
        if ((array[0] & 0x80) == 0x80)
            length++;
        if (includeLength) {
            byte[] result = new byte[length + 4];
            System.arraycopy(array, 0, result, length - array.length + 3, array.length);
            writeInt32BE(length, result, 0);
            if (isNegative)
                result[4] |= 0x80;
            return result;
        } else {
            byte[] result;
            if (length != array.length) {
                result = new byte[length];
                System.arraycopy(array, 0, result, 1, array.length);
            }else
                result = array;
            if (isNegative)
                result[0] |= 0x80;
            return result;
        }
    }


    /**
     * @see #encodeCompactBits(BigInteger)
     */
    public static long encodeCompactBits(BigInteger value) {
        long result;
        int size = value.toByteArray().length;
        if (size <= 3)
            result = value.longValue() << 8 * (3 - size);
        else
            result = value.shiftRight(8 * (size - 3)).longValue();
        // The 0x00800000 bit denotes the sign.
        // Thus, if it is already set, divide the mantissa by 256 and increase the exponent.
        if ((result & 0x00800000L) != 0) {
            result >>= 8;
            size++;
        }
        result |= (long) size << 24;
        result |= value.signum() == -1 ? 0x00800000 : 0;
        return result;
    }

    /**
     * Concatenate two byte arrays
     * @param b1 first byte array
     * @param b2 second byte array
     * @return new concatenated byte array
     */
    public static byte[] concat(byte[] b1, byte[] b2) {
        byte[] result = new byte[b1.length + b2.length];
        System.arraycopy(b1, 0, result, 0, b1.length);
        System.arraycopy(b2, 0, result, b1.length, b2.length);
        return result;
    }
}
