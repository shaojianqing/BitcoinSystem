package sjq.bitcoin.message;

import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.InventoryItem;
import sjq.bitcoin.message.data.VariableInteger;
import sjq.bitcoin.network.protocol.ProtocolException;
import sjq.bitcoin.utility.ByteUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class InventoryMessage extends BaseMessage implements Message {

    public static String COMMAND = "inv";

    private static final int MAX_INVENTORY_ITEMS = 50000;

    private static final int INVENTORY_ITEM_LENGTH = 36;

    private List<InventoryItem> inventoryItemList;

    public InventoryMessage() {
        super(COMMAND);
        this.inventoryItemList = new ArrayList<>();
    }

    @Override
    protected byte[] serializeMessage() throws IOException {
        return new byte[0];
    }

    @Override
    public void deserializeMessage(byte[] data) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        VariableInteger itemCount = VariableInteger.read(buffer);

        if (itemCount.intValue()>MAX_INVENTORY_ITEMS) {
            throw new ProtocolException("inventory item count exceeds max inventory item count!");
        }

        for (int i=0;i<itemCount.intValue();++i) {
            int type = ByteUtils.readInt32LE(buffer);
            Hash hash = Hash.read(buffer);
            inventoryItemList.add(new InventoryItem(type, hash));
        }
    }
}
