package sjq.bitcoin.message;

import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.data.InventoryItem;
import sjq.bitcoin.message.data.NetworkAddress;
import sjq.bitcoin.message.data.VariableInteger;
import sjq.bitcoin.network.exception.ProtocolException;
import sjq.bitcoin.utility.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public abstract class ItemListMessage extends BaseMessage {

    protected static final int MAX_INVENTORY_ITEMS = 50000;

    protected List<InventoryItem> inventoryItemList;

    public ItemListMessage(String command, int protocolVersion) {
        super(command, protocolVersion);
        this.inventoryItemList = new ArrayList<>();
    }

    public ItemListMessage(String command) {
        super(command, NetworkAddress.PROTOCOL_VERSION_1);
        this.inventoryItemList = new ArrayList<>();
    }

    @Override
    protected byte[] serializeMessage() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        VariableInteger itemCount = VariableInteger.of(inventoryItemList.size());
        outputStream.write(itemCount.serialize());

        for (InventoryItem item:inventoryItemList) {
            ByteUtils.writeInt32LE(item.getType(), outputStream);
            outputStream.write(item.getHash().serialize());
        }
        return outputStream.toByteArray();
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
            inventoryItemList.add(InventoryItem.createItem(type, hash));
        }
    }

    public void addInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItemList.add(inventoryItem);
    }
}
