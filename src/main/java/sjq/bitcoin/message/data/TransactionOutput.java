package sjq.bitcoin.message.data;

import sjq.bitcoin.message.TransactionMessage;
import sjq.bitcoin.monetary.Coin;
import sjq.bitcoin.network.exception.ProtocolException;
import sjq.bitcoin.utility.ByteUtils;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class TransactionOutput {

    private TransactionMessage parentTransaction;

    private Coin value;

    private byte[] scriptPubKey;

    public static TransactionOutput read(TransactionMessage parentTransaction,
                                         ByteBuffer buffer) throws BufferUnderflowException, ProtocolException {
        TransactionOutput transactionOutput = new TransactionOutput();
        transactionOutput.parentTransaction = parentTransaction;
        transactionOutput.value = Coin.read(buffer);
        transactionOutput.scriptPubKey = ByteUtils.readLengthPrefixedBytes(buffer);

        return transactionOutput;
    }

    public ByteBuffer write(ByteBuffer buffer) throws BufferOverflowException {
        value.write(buffer);
        ByteUtils.writeLengthPrefixedBytes(scriptPubKey, buffer);
        return buffer;
    }

    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(messageSize());
        buffer = write(buffer);
        return buffer.array();
    }

    private int messageSize() {
        int size = 8;
        size += VariableInteger.sizeOf(scriptPubKey.length) + scriptPubKey.length;
        return size;
    }

    public TransactionMessage getParentTransaction() {
        return parentTransaction;
    }

    public void setParentTransaction(TransactionMessage parentTransaction) {
        this.parentTransaction = parentTransaction;
    }

    public Coin getValue() {
        return value;
    }

    public void setValue(Coin value) {
        this.value = value;
    }

    public byte[] getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(byte[] scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }
}
