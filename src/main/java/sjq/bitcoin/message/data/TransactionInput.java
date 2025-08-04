package sjq.bitcoin.message.data;

import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.TransactionMessage;
import sjq.bitcoin.network.exception.ProtocolException;
import sjq.bitcoin.utility.ByteUtils;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class TransactionInput {

    public static final long NO_SEQUENCE = 0xFFFFFFFFL;

    public static final byte[] EMPTY_SCRIPT = new byte[0];

    private TransactionMessage parentTransaction;

    private long sequence;

    private Hash fromTransactionHash;

    private long transactionOutputIndex;

    private byte[] scriptSignature;

    private TransactionWitness transactionWitness;

    public static TransactionInput coinbaseTransactionInput(TransactionMessage parentTransaction, byte[] scriptSignature) {
        TransactionInput transactionInput = new TransactionInput();
        transactionInput.parentTransaction = parentTransaction;
        transactionInput.fromTransactionHash = Hash.ZERO_HASH;
        transactionInput.transactionOutputIndex = Constants.MAX_UNSIGNED_INTEGER;
        transactionInput.sequence = NO_SEQUENCE;
        transactionInput.scriptSignature = scriptSignature;

        return transactionInput;
    }

    public static TransactionInput read(TransactionMessage parentTransaction, ByteBuffer buffer) throws BufferUnderflowException, ProtocolException {
        TransactionInput transactionInput = new TransactionInput();
        transactionInput.parentTransaction = parentTransaction;
        transactionInput.fromTransactionHash = Hash.read(buffer);
        transactionInput.transactionOutputIndex = ByteUtils.readUint32LE(buffer);
        transactionInput.scriptSignature = ByteUtils.readLengthPrefixedBytes(buffer);
        transactionInput.sequence = ByteUtils.readUint32LE(buffer);

        return transactionInput;
    }

    public TransactionInput cloneWithoutScript() {
        TransactionInput transactionInput = new TransactionInput();
        transactionInput.setParentTransaction(parentTransaction);
        transactionInput.setFromTransactionHash(fromTransactionHash);
        transactionInput.setTransactionOutputIndex(transactionOutputIndex);
        transactionInput.setTransactionWitness(transactionWitness);
        transactionInput.setScriptSignature(EMPTY_SCRIPT);
        transactionInput.setSequence(sequence);
        return transactionInput;
    }

    public TransactionInput cloneWithoutWitness() {
        TransactionInput transactionInput = new TransactionInput();
        transactionInput.setParentTransaction(parentTransaction);
        transactionInput.setFromTransactionHash(fromTransactionHash);
        transactionInput.setTransactionOutputIndex(transactionOutputIndex);
        transactionInput.setTransactionWitness(null);
        transactionInput.setScriptSignature(scriptSignature);
        transactionInput.setSequence(sequence);
        return transactionInput;
    }

    public TransactionInput cloneWithScript(byte[] scriptData) {
        TransactionInput transactionInput = new TransactionInput();
        transactionInput.setParentTransaction(parentTransaction);
        transactionInput.setFromTransactionHash(fromTransactionHash);
        transactionInput.setTransactionOutputIndex(transactionOutputIndex);
        transactionInput.setTransactionWitness(transactionWitness);
        transactionInput.setScriptSignature(scriptData);
        transactionInput.setSequence(sequence);
        return transactionInput;
    }

    public TransactionInput cloneWithSequence(long sequence) {
        TransactionInput transactionInput = new TransactionInput();
        transactionInput.setParentTransaction(parentTransaction);
        transactionInput.setFromTransactionHash(fromTransactionHash);
        transactionInput.setTransactionOutputIndex(transactionOutputIndex);
        transactionInput.setTransactionWitness(transactionWitness);
        transactionInput.setScriptSignature(scriptSignature);
        transactionInput.setSequence(sequence);
        return transactionInput;
    }

    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(messageSize());

        buffer.put(fromTransactionHash.serialize());
        ByteUtils.writeInt32LE(transactionOutputIndex, buffer);
        ByteUtils.writeLengthPrefixedBytes(scriptSignature, buffer);
        ByteUtils.writeInt32LE(sequence, buffer);

        return buffer.array();
    }

    public boolean isCoinbaseTransactionInput() {
        return Hash.ZERO_HASH.equals(fromTransactionHash) &&
                Constants.MAX_UNSIGNED_INTEGER.equals(transactionOutputIndex);
    }

    private int messageSize() {
        int size = 32 + 4;
        size += VariableInteger.sizeOf(scriptSignature.length) + scriptSignature.length;
        size += 4;
        return size;
    }

    public TransactionMessage getParentTransaction() {
        return parentTransaction;
    }

    public void setParentTransaction(TransactionMessage parentTransaction) {
        this.parentTransaction = parentTransaction;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public Hash getFromTransactionHash() {
        return fromTransactionHash;
    }

    public void setFromTransactionHash(Hash fromTransactionHash) {
        this.fromTransactionHash = fromTransactionHash;
    }

    public long getTransactionOutputIndex() {
        return transactionOutputIndex;
    }

    public void setTransactionOutputIndex(long transactionOutputIndex) {
        this.transactionOutputIndex = transactionOutputIndex;
    }

    public byte[] getScriptSignature() {
        return scriptSignature;
    }

    public void setScriptSignature(byte[] scriptSignature) {
        this.scriptSignature = scriptSignature;
    }

    public TransactionWitness getTransactionWitness() {
        return transactionWitness;
    }

    public void setTransactionWitness(TransactionWitness transactionWitness) {
        this.transactionWitness = transactionWitness;
    }
}
