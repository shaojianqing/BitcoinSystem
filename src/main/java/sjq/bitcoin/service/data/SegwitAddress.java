package sjq.bitcoin.service.data;

import sjq.bitcoin.script.BitcoinNetwork;
import sjq.bitcoin.script.ScriptType;

public class SegwitAddress implements BitcoinAddress {

    public static final int WITNESS_PROGRAM_LENGTH_PKH = 20;

    public static final int WITNESS_PROGRAM_LENGTH_SH = 32;

    public static final int WITNESS_PROGRAM_LENGTH_TR = 32;

    public static final int WITNESS_PROGRAM_MIN_LENGTH = 2;

    public static final int WITNESS_PROGRAM_MAX_LENGTH = 40;

    public static SegwitAddress fromHash(BitcoinNetwork network, byte[] addressBytes) {
        return new SegwitAddress();
    }

    public static SegwitAddress fromProgram(BitcoinNetwork network, byte[] keyBytes) {
        return new SegwitAddress();
    }

    @Override
    public ScriptType getScriptType() {
        return null;
    }

    @Override
    public String getStringFormat() {
        return null;
    }
}
