package sjq.bitcoin.network;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class Services {
    /** A service bit that denotes whether the peer has a full copy of the block chain or not. */
    public static final int NODE_NETWORK = 1 << 0;
    /** A service bit that denotes whether the peer supports BIP37 bloom filters or not. The service bit is defined in BIP111. */
    public static final int NODE_BLOOM = 1 << 2;
    /** Indicates that a node can be asked for blocks and transactions including witness data. */
    public static final int NODE_WITNESS = 1 << 3;
    /** Indicates the node will service basic block filter requests (BIP157, BIP158). */
    public static final int NODE_COMPACT_FILTERS = 1 << 6;
    /** A service bit that denotes whether the peer has at least the last two days worth of blockchain (BIP159). */
    public static final int NODE_NETWORK_LIMITED = 1 << 10;
    /** Indicates the node supports BIP324 transport. */
    public static final int NODE_P2P_V2 = 1 << 11;
    /** A service bit used by Bitcoin-ABC to announce Bitcoin Cash nodes. */
    public static final int NODE_BITCOIN_CASH = 1 << 5;

    public static final int LENGTH = 8;

    private final long definition;

    private Services(long bits) {
        this.definition = bits;
    }

    public static Services wrap(long bits) {
        return new Services(bits);
    }

    public boolean has(long bitmask) {
        return (definition & bitmask) == bitmask;
    }

    public boolean has(Services services) {
        return (definition & services.definition) == services.definition;
    }

    public boolean any(long bitmask) {return (definition & bitmask) != 0;}

    public static Services none() {
        return new Services(0);
    }

    public ByteBuffer write(ByteBuffer buf) throws BufferOverflowException {
        buf.order(ByteOrder.LITTLE_ENDIAN).putLong(definition);
        return buf;
    }

    public byte[] serialize() {
        return write(ByteBuffer.allocate(LENGTH)).array();
    }

    public static Services read(ByteBuffer buf) throws BufferUnderflowException {
        return new Services(buf.order(ByteOrder.LITTLE_ENDIAN).getLong());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.definition == ((Services) o).definition;
    }

    public long getDefinition() {
        return definition;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{definition});
    }
}
