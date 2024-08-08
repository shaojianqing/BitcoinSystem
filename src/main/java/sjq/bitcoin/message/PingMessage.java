package sjq.bitcoin.message;

public class PingMessage implements Message {

    public int messageSize() {
        return 0;
    }

    public byte[] serialize() {
        return new byte[0];
    }
}

