package sjq.bitcoin.message;

import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.NetworkAddress;

public class AddressV1Message extends AddressMessage implements Message {

    public static String COMMAND = "addr";

    public AddressV1Message() {
        super(COMMAND, NetworkAddress.PROTOCOL_VERSION_1);
    }
}
