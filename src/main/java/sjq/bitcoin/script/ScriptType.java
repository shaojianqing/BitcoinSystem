package sjq.bitcoin.script;

public enum ScriptType {

    P2PKH("pkh", 1),    // pay to pubkey hash (aka pay to address)
    P2PK("pk", 2),      // pay to pubkey
    P2SH("sh", 3),      // pay to script hash
    P2WPKH("wpkh", 4),  // pay to witness pubkey hash
    P2WSH("wsh", 5),    // pay to witness script hash
    P2TR("tr", 6);

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
