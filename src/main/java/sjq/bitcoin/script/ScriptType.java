package sjq.bitcoin.script;

public enum ScriptType {

    P2PKH("P2PKH", 1),    // pay to pubkey hash (aka pay to address)
    P2PK("P2PK", 2),      // pay to pubkey
    P2SH("P2SH", 3),      // pay to script hash
    P2WPKH("P2WPKH", 4),  // pay to witness pubkey hash
    P2WSH("P2WSH", 5),    // pay to witness script hash
    P2TR("P2TR", 6);      // pay to taproot address

    private String name;

    private Integer type;

    ScriptType(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
