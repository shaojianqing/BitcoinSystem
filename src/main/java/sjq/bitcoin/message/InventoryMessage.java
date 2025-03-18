package sjq.bitcoin.message;

import sjq.bitcoin.message.base.Message;

public class InventoryMessage extends ItemListMessage implements Message {

    public static String COMMAND = "inv";

    public InventoryMessage() {
        super(COMMAND);
    }
}
