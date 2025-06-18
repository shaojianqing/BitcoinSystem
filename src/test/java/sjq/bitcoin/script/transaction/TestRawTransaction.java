package sjq.bitcoin.script.transaction;

import org.junit.Assert;
import org.junit.Test;
import sjq.bitcoin.constant.Constants;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.TransactionMessage;
import sjq.bitcoin.message.data.TransactionOutput;
import sjq.bitcoin.script.BitcoinNetwork;
import sjq.bitcoin.script.ScriptProgram;
import sjq.bitcoin.service.data.LegacyAddress;
import sjq.bitcoin.utility.HexUtils;

import java.nio.ByteBuffer;
import java.util.List;

public class TestRawTransaction {

    private static final String rawTransactionData = "0100000002381ff8929a3f9601d46b2799e3e4ce615b085ac12a0a8f0b2966045bc81318bf040000008a47304402205c13e70aeb6e82a682d758feae215317d18d1156cfbfdf08ba1e44539293343f02200730c6f7c5a0b85d2eb3b76169ad0c174521a332e6754bcda3f5304cbd0bb8fb0141048cc0b94178715f03ed3d0bceb368191d0fdd7fc16d806567f6f2c45aecafb8f53e5ef849564072189b9b4f8bfe1564da776567ba359cfb0c05e839bcf65371abffffffff818b17addf6d8d391f2a709902ddcfe821597de42e4ff27a0608a9e0b96aba1a010000008a4730440220764408c7e6641938d9de301e2ba631d6e184df20f558fe19b4ab6ce054fb61e602202945b2e0aa89c9954483767418fe79b06d89a45a5f53f57a1a4ca1abc696c3bf014104dc47ebcbc3a1edd4d657b630e65d9958cebfd4de2e001afdaa566ffb4f0db663dd206f4624bf38694f022c79d8535c5185f8effcca4aa0f6507205b4a9f5038fffffffff02f5483a00000000001976a9148acf7021339881af075cc749f0cdce9eeb6d373c88aceba37016000000001976a914c8bf444d872bf6f869c9f0231707765836d13d4088ac00000000";

    private static final String originTransactionHash = "3f6450cc84fc69303dafd4d628ef11fdab40c08b71ddbb0546114a965f9c9323";

    private static final String originPubKeyStr1 = "76a9148acf7021339881af075cc749f0cdce9eeb6d373c88ac";

    private static final String originLegacyAddress1 = "1DexkX3UBLjL4o4EcnMDmzzeUzFYBsDtGr";

    private static final String originPubKeyStr2 = "76a914c8bf444d872bf6f869c9f0231707765836d13d4088ac";

    private static final String originLegacyAddress2 = "1KJTGpNzYsFibLmq9WaTGAXQbhRFUgnG3z";

    @Test
    public void validate() throws Exception {

        byte[] rawTransaction = HexUtils.parseHex(rawTransactionData);
        ByteBuffer buffer = ByteBuffer.wrap(rawTransaction);
        TransactionMessage transaction = TransactionMessage.read(buffer, Constants.VERSION_CURRENT);

        List<TransactionOutput> transactionOutputs = transaction.getTransactionOutputs();
        Assert.assertEquals(transactionOutputs.size(), 2);

        TransactionOutput transactionOutput1 = transactionOutputs.get(0);
        byte[] pubKey1 = transactionOutput1.getScriptPubKey();
        ScriptProgram scriptProgram1 = ScriptProgram.parse(pubKey1);
        if (scriptProgram1.isP2PKH()) {
            byte[] addressBytes1 =  scriptProgram1.extractHashFromP2PKH();
            LegacyAddress legacyAddress1 = LegacyAddress.fromPubKeyHash(BitcoinNetwork.MAINNET, addressBytes1);

            String actualPubKeyStr1 = HexUtils.formatHex(transactionOutput1.getScriptPubKey());
            Assert.assertEquals("script format not equal!", originPubKeyStr1, actualPubKeyStr1);
            Assert.assertEquals("formatted address!", originLegacyAddress1, legacyAddress1.getStringFormat());

            Logger.info("The pubKey1 is:%s", originPubKeyStr1);
            Logger.info("The formatted address1:%s", legacyAddress1.getStringFormat());
        }

        TransactionOutput transactionOutput2 = transactionOutputs.get(1);
        byte[] pubKey2 = transactionOutput2.getScriptPubKey();
        ScriptProgram scriptProgram2 = ScriptProgram.parse(pubKey2);
        if (scriptProgram2.isP2PKH()) {
            byte[] addressBytes2 =  scriptProgram2.extractHashFromP2PKH();
            LegacyAddress legacyAddress2 = LegacyAddress.fromPubKeyHash(BitcoinNetwork.MAINNET, addressBytes2);

            String actualPubKeyStr2 = HexUtils.formatHex(transactionOutput2.getScriptPubKey());
            Assert.assertEquals("script format not equal!", originPubKeyStr2, actualPubKeyStr2);
            Assert.assertEquals("formatted address!", originLegacyAddress2, legacyAddress2.getStringFormat());

            Logger.info("The pubKey2 is:%s", originPubKeyStr2);
            Logger.info("The formatted address2:%s", legacyAddress2.getStringFormat());
        }

        Hash transactionHash = transaction.getTransactionHash();
        Logger.info("The transaction hash:%s", transactionHash.hexValue());
        Assert.assertEquals("script format not equal!", originTransactionHash, transactionHash.hexValue());
    }
}
