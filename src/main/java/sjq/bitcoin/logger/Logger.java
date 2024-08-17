package sjq.bitcoin.logger;

import sjq.bitcoin.utility.DateUtils;

import java.util.Date;

public class Logger {

    private static final String DEBUG = "[DEBUG]";

    private static final String INFO = "[INFO]";

    private static final String WARN = "[WARN]";

    private static final String ERROR = "[ERROR]";

    public static void debug(String format, Object... args) {
        log(DEBUG, format, args);
    }

    public static void info(String format, Object... args) {
        log(INFO, format, args);
    }

    public static void warn(String format, Object... args) {
        log(WARN, format, args);
    }

    public static void error(String format, Object... args) {
        log(ERROR, format, args);
    }

    private static void log(String level, String format, Object... args) {
        String text = String.format(format, args);
        String timestamp = DateUtils.formatDate(new Date());
        String content = String.format("%s %s %s", timestamp, level, text);
        System.out.println(content);
    }
}
