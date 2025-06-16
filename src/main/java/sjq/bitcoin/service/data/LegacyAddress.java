package sjq.bitcoin.service.data;

import sjq.bitcoin.script.BitcoinNetwork;
import sjq.bitcoin.script.ScriptType;
import sjq.bitcoin.service.data.exception.AddressException;
import sjq.bitcoin.utility.Base58Utils;

import java.util.Arrays;

public class LegacyAddress implements BitcoinAddress {

    public static final int ADDRESS_LENGTH = 20;

    private final BitcoinNetwork network;

    private final ScriptType type;

    private final byte[] address;

    private LegacyAddress(BitcoinNetwork network, byte[] address, ScriptType type) {
        this.network = network;
        this.address = address;
        this.type = type;
    }

    public static LegacyAddress fromPubKeyHash(BitcoinNetwork network, byte[] hash160) {
        return new LegacyAddress(network, hash160, ScriptType.P2PKH);
    }

    public static LegacyAddress fromScriptHash(BitcoinNetwork network, byte[] hash160) {
        return new LegacyAddress(network, hash160, ScriptType.P2SH);
    }

    public static LegacyAddress fromBase58Address(BitcoinNetwork network, String base58Address) {
        byte[] versionAndHash = Base58Utils.decodeChecked(base58Address);
        byte[] hashData = Arrays.copyOfRange(versionAndHash, 1, versionAndHash.length);
        int version = versionAndHash[0] & 0xFF;
        if (version == network.getLegacyP2PKHHeader()) {
            return new LegacyAddress(network, hashData, ScriptType.P2PKH);
        } else if (version == network.getLegacyP2SHHeader()) {
            return new LegacyAddress(network, hashData, ScriptType.P2SH);
        } else {
            throw new AddressException(String.format("The base58 address is invalid with %s", base58Address));
        }
    }

    public byte[] getRawAddress() {
        return address;
    }

    @Override
    public ScriptType getScriptType() {
        return type;
    }

    @Override
    public String getStringFormat() {
        int version;
        if (type == ScriptType.P2PKH) {
            version = network.getLegacyP2PKHHeader();
        } else if (type == ScriptType.P2SH) {
            version = network.getLegacyP2SHHeader();
        } else {
            throw new AddressException("invalid address type for legacy address!");
        }
        return Base58Utils.encodeChecked(version, address);
    }
}
