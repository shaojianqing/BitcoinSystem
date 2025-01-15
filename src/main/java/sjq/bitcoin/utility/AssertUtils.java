package sjq.bitcoin.utility;

public class AssertUtils {

    public static void notNull(Object object, String message) {
        if (object==null) {
            throw new IllegalStateException(message);
        }
    }
}
