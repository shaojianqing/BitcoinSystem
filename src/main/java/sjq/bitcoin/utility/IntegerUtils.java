package sjq.bitcoin.utility;

public class IntegerUtils {

    public static long toUnsignedLong(int x) {
        return ((long) x) & 0xFFFFFFFFL;
    }
}
