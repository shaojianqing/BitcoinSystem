package sjq.bitcoin.message;

import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.NetworkAddress;
import sjq.bitcoin.message.data.VariableInteger;
import sjq.bitcoin.network.exception.ProtocolException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public abstract class AddressMessage extends BaseMessage implements Message {

    protected static final long MAX_ADDRESSES = 1000;

    protected List<NetworkAddress> addressList = new ArrayList<NetworkAddress>();

    public AddressMessage(String command, int protocolVersion) {
        super(command, protocolVersion);
    }

    @Override
    public byte[] serializeMessage() throws IOException {
        return new byte[0];
    }

    @Override
    public void deserializeMessage(byte[] data) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        VariableInteger addressCount = VariableInteger.read(buffer);

        if (addressCount.longValue() > MAX_ADDRESSES) {
            throw new ProtocolException("network address count is large than the maximum limit!");
        }

        for (int i=0; i<addressCount.intValue(); ++i) {
            NetworkAddress address = NetworkAddress.read(buffer, protocolVersion);
            addressList.add(address);
        }
    }

    public List<NetworkAddress> getAddressList() {
        return addressList;
    }
}
