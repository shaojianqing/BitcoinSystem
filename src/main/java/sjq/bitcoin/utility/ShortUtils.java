package sjq.bitcoin.utility;

public class ShortUtils {

    /**
     * Converts short into a unsigned integer
     *
     * @param x to convert into a unsigned integer
     * @return the converted unsigned integer
     */
    public static int toUnsignedInt(short x) {
        return ((int) x) & 0xFFFF;
    }
}
