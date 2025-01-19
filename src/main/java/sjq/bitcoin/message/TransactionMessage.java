package sjq.bitcoin.message;

import sjq.bitcoin.configuration.MainnetConfiguration;
import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.base.BaseMessage;
import sjq.bitcoin.message.base.Message;
import sjq.bitcoin.message.data.*;
import sjq.bitcoin.network.protocol.ProtocolException;
import sjq.bitcoin.utility.ByteUtils;
import sjq.bitcoin.utility.HexUtils;

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
            transactionOutputs.add(TransactionOutput.read(this, buffer));
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

    private static boolean allowTransactionWitness(int protocolVersion) {
        return (protocolVersion & Constants.SERIALIZE_TRANSACTION_NO_WITNESS) == 0
                && protocolVersion >= Constants.TRANSACTION_WITNESS_VERSION;
    }

    public Hash getTransactionHash() throws IOException {
        byte[] rawTransactionData = serializeMessage();
        return Hash.wrapReversed(Hash.calculateTwice(rawTransactionData));
    }

    public boolean isCoinbaseTransaction() {
        if (transactionInputs.size()==1) {
            TransactionInput transactionInput = transactionInputs.get(0);
            return transactionInput.isCoinbaseTransactionInput();
        }
        return false;
    }

    protected byte[] serializeMessage() throws IOException {
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

    public TransactionLockTime getTransactionLockTime() {
        return transactionLockTime;
    }

    public void setTransactionLockTime(TransactionLockTime transactionLockTime) {
        this.transactionLockTime = transactionLockTime;
    }

    public static void main(String[] args) {
        /*String rawTransaction = "0100000000010161a76ac1c323d2e01668cafc15833c4b163074bcbe0088cf292674e11b71786d0300000000fdffffff040000000000000000536a4c5058325b545950060e87178b5d0a454ccfe703282a080d44b3b5e7fdd642d3922bb00068801f243240247bb816202efcc32345ebdaa87c6ff3055eb0d8662d119a14d738000d6c94062f000d3db100706ca086010000000000160014d22050accdfbad4a3a98e376facae17e2591e82ea086010000000000160014db14133a9dbb1d0e16b60513453e48b6ff2847a9c283af0000000000160014ef2df7d92fee707394a55ca8668d691535d76c040247304402205a20f506f2a649c2dca12ae05a48895ca47c9e6bf7e1eb96a0c788afe273498502202a993febdee588d6f2fb2bfda7e7ffd424fa2b34321c0036430db00a8f2fe3680121033db2ed1fce8a036e09a5937a494d3da8b465f386a8651dbc1a5f16ad343c362a00000000";
        byte[] rawData = HexUtils.parseHex(rawTransaction);
        ByteBuffer buffer = ByteBuffer.wrap(rawData);

        try {
            TransactionMessage transactionMessage = TransactionMessage.read(buffer, Constants.VERSION_CURRENT);
            System.out.println(HexUtils.formatHex(transactionMessage.serializeToStream(true).toByteArray()));
            System.out.println(transactionMessage.getTransactionHash().hexValue());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try {
            MainnetConfiguration configuration = (MainnetConfiguration) MainnetConfiguration.getConfiguration();
            List<TransactionMessage>  transactions = configuration.getGenesisTransactions();
            System.out.println(HexUtils.formatHex(transactions.get(0).serializeToStream(false).toByteArray()));
            System.out.println(transactions.get(0).getTransactionHash().hexValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
