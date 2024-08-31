package sjq.bitcoin.message;

import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.BlockHeader;
import sjq.bitcoin.message.data.VariableInteger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class HeadersMessage extends BaseMessage implements Message {

    public static String COMMAND = "headers";

    private VariableInteger headerCount;

    private List<BlockHeader> headerList;

    public HeadersMessage() {
        headerList = new ArrayList<BlockHeader>();
    }

    @Override
    protected byte[] serializeMessage() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(headerCount.serialize());

        for (int i=0;i<headerCount.intValue();++i) {
            BlockHeader header = headerList.get(i);
            outputStream.write(header.serialize());
        }

        return outputStream.toByteArray();
    }

    @Override
    public void deserializeMessage(byte[] data) throws Exception {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        this.headerCount = VariableInteger.read(buffer);

        for (int i=0;i<headerCount.intValue();++i) {
            BlockHeader header = new BlockHeader();
            header.read(buffer);
            headerList.add(header);
        }
    }

    public String getCommand() {
        return COMMAND;
    }

    public int getHeaderCount() {
        return headerCount.intValue();
    }

    public void setHeaderCount(int headerCount) {
        this.headerCount = VariableInteger.of(headerCount);
    }

    public List<BlockHeader> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<BlockHeader> headerList) {
        this.headerList = headerList;
    }
}
