package sjq.bitcoin.message;

public class VersionReqMessage implements Message {

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