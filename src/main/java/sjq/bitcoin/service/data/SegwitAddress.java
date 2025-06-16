package sjq.bitcoin.service.data;

import sjq.bitcoin.script.BitcoinNetwork;
import sjq.bitcoin.script.ScriptType;
import sjq.bitcoin.service.data.exception.AddressException;
import sjq.bitcoin.utility.Bech32Utils;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Optional;
import java.util.stream.Stream;

public class SegwitAddress implements BitcoinAddress {

    public static final int WITNESS_PROGRAM_LENGTH_PKH = 20;

    public static final int WITNESS_PROGRAM_LENGTH_SH = 32;

    public static final int WITNESS_PROGRAM_LENGTH_TR = 32;

    public static final int WITNESS_PROGRAM_MIN_LENGTH = 2;

    public static final int WITNESS_PROGRAM_MAX_LENGTH = 40;

    private static final byte[] P2A_SCRIPT = { 0x4e, 0x73 };

    private final BitcoinNetwork network;

    private final short witnessVersion;

    private final byte[] witnessProgram;

    private final ScriptType type;

    public static SegwitAddress fromHash(BitcoinNetwork network, byte[] hashBytes) {
        return new SegwitAddress(network, 0, hashBytes, ScriptType.P2WPKH);
    }

    public static SegwitAddress fromProgram(BitcoinNetwork network, int witnessVersion, byte[] witnessProgram) {
        return new SegwitAddress(network, witnessVersion, witnessProgram, ScriptType.P2TR);
    }

    private SegwitAddress(BitcoinNetwork network, int witnessVersion, byte[] witnessProgram, ScriptType type) throws AddressException {
        if (witnessVersion < 0 || witnessVersion > 16) {
            throw new AddressException("Invalid script version: " + witnessVersion);
        }
        if (witnessProgram.length < WITNESS_PROGRAM_MIN_LENGTH || witnessProgram.length > WITNESS_PROGRAM_MAX_LENGTH) {
            throw new AddressException("Invalid length: " + witnessProgram.length);
        }
        if (witnessVersion == 0 &&
                witnessProgram.length != WITNESS_PROGRAM_LENGTH_PKH &&
                witnessProgram.length != WITNESS_PROGRAM_LENGTH_SH) {
            throw new AddressException("Invalid length for address version 0: " + witnessProgram.length);
        }

        if (witnessVersion == 1 && witnessProgram.length != WITNESS_PROGRAM_LENGTH_TR
                && !isPayToAnchorOutputScript(witnessVersion, witnessProgram))
            throw new AddressException("Invalid length for address version 1: " + witnessProgram.length);
        this.network = normalizeNetwork(network);
        this.witnessVersion = (short) witnessVersion;
        this.witnessProgram = witnessProgram;
        this.type = type;
    }

    private boolean isPayToAnchorOutputScript(int witnessVersion, byte[] witnessProgram) {
        return witnessVersion == 1 && Arrays.equals(P2A_SCRIPT, witnessProgram);
    }

    private static BitcoinNetwork normalizeNetwork(BitcoinNetwork network) {
        // SegwitAddress does not distinguish between the SIGNET and TESTNET, normalize to TESTNET
        if (network == BitcoinNetwork.SIGNET) {
            return BitcoinNetwork.TESTNET;
        }
        return network;
    }

    @Override
    public ScriptType getScriptType() {
        return type;
    }

    @Override
    public String getStringFormat() {
        String segwitAddressHrp = SegwitHrp.ofNetwork(network).toString();
        Bech32Utils.Encoding encoding = (witnessVersion == 0) ?  Bech32Utils.Encoding.BECH32 : Bech32Utils.Encoding.BECH32M;
        return Bech32Utils.encode(encoding, segwitAddressHrp, Bech32Utils.Bech32Bytes.ofSegwit(witnessVersion, witnessProgram));
    }

    public enum SegwitHrp {
        BC(BitcoinNetwork.MAINNET),
        TB(BitcoinNetwork.TESTNET, BitcoinNetwork.SIGNET),
        BCRT(BitcoinNetwork.REGTEST);

        private final EnumSet<BitcoinNetwork> networks;

        SegwitHrp(BitcoinNetwork n) {
            networks = EnumSet.of(n);
        }

        SegwitHrp(BitcoinNetwork n1, BitcoinNetwork n2) {
            networks = EnumSet.of(n1, n2);
        }

        /**
         * Get the HRP in lowercase. To get uppercase, use {@link SegwitHrp#name()}
         * @return HRP in lowercase.
         */
        public String toString() {
            return name().toLowerCase();
        }

        /**
         * @param hrp uppercase or lowercase HRP
         * @return the corresponding enum
         * @throws IllegalArgumentException if unknown string
         */
        public static SegwitHrp of(String hrp) {
            return SegwitHrp.valueOf(hrp.toUpperCase());
        }

        /**
         * @param hrp uppercase or lowercase HRP
         * @return Optional containing the corresponding enum or empty if not found
         */
        public static Optional<SegwitHrp> find(String hrp) {
            try {
                return Optional.of(SegwitHrp.of(hrp));
            } catch(IllegalArgumentException iae) {
                return Optional.empty();
            }
        }

        /**
         * @param network network enum
         * @return the corresponding enum
         */
        public static SegwitHrp ofNetwork(BitcoinNetwork network) {
            return Stream.of(SegwitHrp.values())
                    .filter(hrp -> hrp.networks.contains(network))
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);
        }
    }
}
