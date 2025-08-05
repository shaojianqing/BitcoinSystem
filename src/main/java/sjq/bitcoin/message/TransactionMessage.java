package sjq.bitcoin.message;

import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.*;
import sjq.bitcoin.network.exception.ProtocolException;
import sjq.bitcoin.utility.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class TransactionMessage extends BaseMessage implements Message {

    public static String COMMAND = "tx";

    private long messageVersion;

    private List<TransactionInput> transactionInputs = new ArrayList<>();

    private List<TransactionOutput> transactionOutputs = new ArrayList<>();

    private TransactionLockTime transactionLockTime;

    private TransactionMessage(int protocolVersion) {
        super(COMMAND, protocolVersion);
    }

    public static TransactionMessage coinbaseTransaction(byte[] scriptData) {
        TransactionMessage transactionMessage = new TransactionMessage(Constants.VERSION_CURRENT);
        TransactionInput transactionInput = TransactionInput.coinbaseTransactionInput(transactionMessage, scriptData);
        transactionMessage.addTransactionInput(transactionInput);

        return transactionMessage;
    }

    public static TransactionMessage createTransactionMessage(int protocolVersion) {
        return new TransactionMessage(protocolVersion);
    }

    public static TransactionMessage read(ByteBuffer buffer,
                                          int protocolVersion) throws BufferUnderflowException, ProtocolException {
        TransactionMessage transaction = new TransactionMessage(protocolVersion);
        transaction.messageVersion = ByteUtils.readUint32LE(buffer);
        transaction.readTransactionInputs(buffer);
        byte hasWitnessFlag = 0x00;
        if (transaction.transactionInputs.size()==0 && allowTransactionWitness(protocolVersion)) {
            hasWitnessFlag = ByteUtils.readByte(buffer);
            if (hasWitnessFlag!=0) {
                transaction.readTransactionInputs(buffer);
                transaction.readTransactionOutputs(buffer);
            } else {
                transaction.transactionOutputs = new ArrayList<>();
            }
        } else {
            transaction.readTransactionOutputs(buffer);
        }

        if ((hasWitnessFlag&0x01)!=0 && allowTransactionWitness(protocolVersion)) {
            hasWitnessFlag^=0x01;
            transaction.readTransactionWitness(buffer);
        }

        if (hasWitnessFlag!=0x00) {
            throw new ProtocolException("unknown transaction optional data");
        }

        long lockTimeValue = ByteUtils.readUint32LE(buffer);
        transaction.transactionLockTime = TransactionLockTime.of(lockTimeValue);

        return transaction;
    }

    private void readTransactionInputs(ByteBuffer buffer) {
        VariableInteger inputCount = VariableInteger.read(buffer);
        int capacity = Math.min(inputCount.intValue(), Constants.MAX_INITIAL_ARRAY_LENGTH);
        transactionInputs = new ArrayList<>(capacity);
        for (int i=0; i<inputCount.intValue();++i) {
            transactionInputs.add(TransactionInput.read(this, buffer));
        }
    }

    private void readTransactionOutputs(ByteBuffer buffer) {
        VariableInteger outputCount = VariableInteger.read(buffer);
        int capacity = Math.min(outputCount.intValue(), Constants.MAX_INITIAL_ARRAY_LENGTH);
        transactionOutputs = new ArrayList<>(capacity);
        for (int i=0;i<outputCount.intValue();++i) {
            TransactionOutput transactionOutput = TransactionOutput.read(this, buffer);
            transactionOutput.setTransactionOutputIndex((long) i);
            transactionOutputs.add(transactionOutput);
        }
    }

    private void readTransactionWitness(ByteBuffer buffer) {
        for (TransactionInput transactionInput:transactionInputs) {
            transactionInput.setTransactionWitness(TransactionWitness.read(buffer));
        }
    }

    public void addTransactionInput(TransactionInput transactionInput) {
        transactionInputs.add(transactionInput);
    }

    public void addTransactionOutput(TransactionOutput transactionOutput) {
        transactionOutputs.add(transactionOutput);
    }

    public void replaceTransactionInput(TransactionInput transactionInput, int index){
        TransactionInput originInput = transactionInputs.get(index);
        originInput.setParentTransaction(null);

        transactionInput.setParentTransaction(this);
        transactionInputs.set(index, transactionInput);
    }

    private static boolean allowTransactionWitness(int protocolVersion) {
        return (protocolVersion & Constants.SERIALIZE_TRANSACTION_NO_WITNESS) == 0
                && protocolVersion >= Constants.TRANSACTION_WITNESS_VERSION;
    }

    public Hash getTransactionHash() throws IOException {
        byte[] rawTransactionData = serializeMessage();
        return Hash.wrapReversed(Hash.calculateTwice(rawTransactionData));
    }

    public Hash getWitnessTransactionHash() throws IOException {
        return Hash.ZERO_HASH;
    }

    public boolean isCoinbaseTransaction() {
        if (transactionInputs.size()==1) {
            TransactionInput transactionInput = transactionInputs.get(0);
            return transactionInput.isCoinbaseTransactionInput();
        }
        return false;
    }

    public byte[] serializeMessage() throws IOException {
        ByteArrayOutputStream outputStream = serializeToStream(false);
        return outputStream.toByteArray();
    }

    private ByteArrayOutputStream serializeToStream(boolean useSegWitness) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteUtils.writeInt32LE(messageVersion, outputStream);
        if (useSegWitness) {
            outputStream.write(0x00);
            outputStream.write(0x01);
        }

        VariableInteger inputCount = VariableInteger.of(transactionInputs.size());
        outputStream.write(inputCount.serialize());
        for (TransactionInput transactionInput:transactionInputs) {
            outputStream.write(transactionInput.serialize());
        }

        VariableInteger outputCount = VariableInteger.of(transactionOutputs.size());
        outputStream.write(outputCount.serialize());
        for (TransactionOutput transactionOutput:transactionOutputs) {
            outputStream.write(transactionOutput.serialize());
        }

        if (useSegWitness) {
            for (TransactionInput transactionInput:transactionInputs) {
                outputStream.write(transactionInput.getTransactionWitness().serialize());
            }
        }

        ByteUtils.writeInt32LE(transactionLockTime.rawValue(), outputStream);
        return outputStream;
    }

    public long getMessageVersion() {
        return messageVersion;
    }

    public void setMessageVersion(long messageVersion) {
        this.messageVersion = messageVersion;
    }

    public List<TransactionInput> getTransactionInputs() {
        return transactionInputs;
    }

    public void setTransactionInputs(List<TransactionInput> transactionInputs) {
        this.transactionInputs = transactionInputs;
    }

    public List<TransactionOutput> getTransactionOutputs() {
        return transactionOutputs;
    }

    public void setTransactionOutputs(List<TransactionOutput> transactionOutputs) {
        this.transactionOutputs = transactionOutputs;
    }

    public TransactionLockTime getTransactionLockTime() {
        return transactionLockTime;
    }

    public void setTransactionLockTime(TransactionLockTime transactionLockTime) {
        this.transactionLockTime = transactionLockTime;
    }
}