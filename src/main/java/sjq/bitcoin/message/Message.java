package sjq.bitcoin.message;

public interface Message {

    int messageSize();

    byte[] serialize();
}