package sjq.bitcoin.message;

public class TransactionMessage implements Message {

    public int messageSize() {
        return 0;
    }

    public byte[] serialize() {
        return new byte[0];
    }
}
