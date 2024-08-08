package sjq.bitcoin.utility;

public class ThreadUtils {

    public static void run(Runnable task, String name) {
        Thread thread = new Thread(task, name);
        thread.start();
    }
}
