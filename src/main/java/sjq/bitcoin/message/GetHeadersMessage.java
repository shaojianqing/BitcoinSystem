package sjq.bitcoin.message;

public class GetHeadersMessage implements Message {

    public int messageSize() {
        return 0;
    }

    public byte[] serialize() {
        return new byte[0];
    }
}
