package sjq.bitcoin.message;

public class PingMessage implements Message {

    public static String COMMAND = "";

    public int messageSize() {
        return 0;
    }

    public byte[] serialize() {
        return new byte[0];
    }

    public void deserialize(byte[] data){

    }
}

