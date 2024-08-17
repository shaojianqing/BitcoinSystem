package sjq.bitcoin.message.data;

import sjq.bitcoin.hash.Hash;

public class InventoryItem {

    public static final int TYPE_ERROR = 0;

    public static final int TYPE_TRANSACTION = 1;

    public static final int TYPE_BLOCK = 2;

    public static final int TYPE_FILTERED_BLOCK = 3;

    public static final int TYPE_CMPCT_BLOCK = 4;

    public static final int TYPE_WITNESS_TRANSACTION = 0x40000001;

    public static final int TYPE_WITNESS_BLOCK = 0x40000002;

    public static final int TYPE_FILTERED_WITNESS_BLOCK = 0x40000003;

    private int type;

    private Hash hash;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Hash getHash() {
        return hash;
    }

    public void setHash(Hash hash) {
        this.hash = hash;
    }
}