package sjq.bitcoin.constant;

import sjq.bitcoin.utility.HexUtils;

public class GenesisParams {

    public static final byte[] GENESIS_TRANSACTION_INPUT_SCRIPT =
            HexUtils.parseHex("04ffff001d0104455468652054696d65732030332f4a616e2f32303039204368616e63656c6c6f72206f6e206272696e6b206f66207365636f6e64206261696c6f757420666f722062616e6b73");


    public static void main(String[] args) {
        String scriptMessage = new String(GENESIS_TRANSACTION_INPUT_SCRIPT);

        System.out.println(scriptMessage);
    }
}
