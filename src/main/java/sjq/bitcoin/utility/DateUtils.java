package sjq.bitcoin.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss.SSS");

    public static String formatDate(Date date) {
        return simpleDateFormat.format(date);
    }
}
