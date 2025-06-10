package sjq.bitcoin.script;

public enum BitcoinNetwork {
    MAINNET("org.bitcoin.main", 0x00, 0x05),
    TESTNET("org.bitcoin.test", 0x6f, 0xc4),
    SIGNET("org.bitcoin.signet", 0x6f, 0xc4),
    REGTEST("org.bitcoin.regtest", 0x6f, 0xc4);

    private final String id;

    private final int legacyP2PKHHeader;

    private final int legacyP2SHHeader;

    BitcoinNetwork(String id, int legacyP2PKHHeader, int legacyP2SHHeader) {
        this.id = id;
        this.legacyP2PKHHeader = legacyP2PKHHeader;
        this.legacyP2SHHeader = legacyP2SHHeader;
    }

    public String getId() {
        return id;
    }

    public int getLegacyP2PKHHeader() {
        return legacyP2PKHHeader;
    }

    public int getLegacyP2SHHeader() {
        return legacyP2SHHeader;
    }
}
