package sjq.bitcoin.message;

import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.NetworkAddress;

public class AddressV1Message extends AddressMessage implements Message {

    public static String COMMAND = "addr";

    public AddressV1Message() {
        super.protocolVersion = NetworkAddress.PROTOCOL_VERSION_1;
    }

    public String getCommand() {
        return COMMAND;
    }
}
