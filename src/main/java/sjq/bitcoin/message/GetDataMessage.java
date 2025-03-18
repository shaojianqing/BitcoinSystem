package sjq.bitcoin.message;

import sjq.bitcoin.message.base.Message;

public class GetDataMessage extends ItemListMessage implements Message {

    public static String COMMAND = "getdata";

    public GetDataMessage() {
        super(COMMAND);
    }
}
