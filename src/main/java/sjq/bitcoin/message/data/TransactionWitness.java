package sjq.bitcoin.message.data;

import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.utility.ByteUtils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class TransactionWitness {

    private List<WitnessData> witnessDataList = new ArrayList<>();

    public static TransactionWitness read(ByteBuffer buffer) {
        TransactionWitness witness = new TransactionWitness();
        VariableInteger witnessDataCount = VariableInteger.read(buffer);
        int witnessCount = Math.min(witnessDataCount.intValue(), Constants.MAX_INITIAL_ARRAY_LENGTH);
        witness.witnessDataList = new ArrayList<>(witnessCount);
        for (int i=0;i<witnessDataCount.intValue();++i) {
            WitnessData witnessData = WitnessData.read(buffer);
            witness.witnessDataList.add(witnessData);
        }
        return witness;
    }

    public ByteBuffer write(ByteBuffer buffer) {
        VariableInteger witnessDataCount = VariableInteger.of(witnessDataList.size());
        witnessDataCount.write(buffer);
        for (WitnessData witnessData:witnessDataList) {
            witnessData.write(buffer);
        }
        return buffer;
    }

    private int messageSize() {
        int size = VariableInteger.sizeOf(witnessDataList.size());
        for (WitnessData witnessData:witnessDataList) {
            size += witnessData.messageSize();
        }
        return size;
    }

    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(messageSize());
        buffer = write(buffer);
        return buffer.array();
    }

    public List<WitnessData> getWitnessDataList() {
        return witnessDataList;
    }

    public void setWitnessDataList(List<WitnessData> witnessDataList) {
        this.witnessDataList = witnessDataList;
    }

    public static class WitnessData {
        private byte[] data;

        public static WitnessData read(ByteBuffer buffer) {
            WitnessData witnessData = new WitnessData();
            witnessData.data = ByteUtils.readLengthPrefixedBytes(buffer);
            return witnessData;
        }

        public ByteBuffer write(ByteBuffer buffer) {
            ByteUtils.writeLengthPrefixedBytes(data, buffer);
            return buffer;
        }

        private int messageSize() {
            return VariableInteger.sizeOf(data.length) + data.length;
        }

        public byte[] getData() {
            return data;
        }

        public void setData(byte[] data) {
            this.data = data;
        }
    }
}
