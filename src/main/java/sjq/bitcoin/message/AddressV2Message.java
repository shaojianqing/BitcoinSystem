package sjq.bitcoin.message;

import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.NetworkAddress;

public class AddressV2Message extends AddressMessage implements Message {

    public static String COMMAND = "addrv2";

    public AddressV2Message() {
        super(COMMAND, NetworkAddress.PROTOCOL_VERSION_2);
    }
}