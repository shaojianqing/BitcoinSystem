package sjq.bitcoin.service.data;

import sjq.bitcoin.script.BitcoinNetwork;
import sjq.bitcoin.service.data.exception.AddressException;
import sjq.bitcoin.utility.Base58Utils;

public class LegacyAddress implements BitcoinAddress {

    public static final int ADDRESS_LENGTH = 20;

    public static final short TYPE_P2PKH = 1;

    public static final short TYPE_P2SH = 2;

    private BitcoinNetwork network;

    private byte[] address;

    private short type;

    private LegacyAddress(BitcoinNetwork network, byte[] address, short type) {
        this.network = network;
        this.address = address;
        this.type = type;
    }

    public static LegacyAddress fromPubKeyHash(BitcoinNetwork network, byte[] hash160) {
        return new LegacyAddress(network, hash160, LegacyAddress.TYPE_P2PKH);
    }

    public static LegacyAddress fromScriptHash(BitcoinNetwork network, byte[] hash160) {
        return new LegacyAddress(network, hash160, LegacyAddress.TYPE_P2SH);
    }

    public static LegacyAddress fromBase58Address(BitcoinNetwork network, String base58Address) {
        return null;
    }

    public byte[] getRawAddress() {
        return address;
    }

    @Override
    public String stringFormat() {
        int version;
        if (type == TYPE_P2PKH) {
            version = network.getLegacyP2PKHHeader();
        } else if (type == TYPE_P2SH) {
            version = network.getLegacyP2SHHeader();
        } else {
            throw new AddressException("invalid address type for legacy address!");
        }
        return Base58Utils.encodeChecked(version, address);
    }
}
