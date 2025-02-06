package sjq.bitcoin.logger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Buffer {

    private static final int DEFAULT_CAPACITY = 100;

    private Queue<String> queue;

    private int capacity;

    public Buffer() {
        this.capacity = DEFAULT_CAPACITY;
        this.queue = new LinkedList<>();
    }

    public synchronized void log(String content) {
        queue.offer(content);
        if (queue.size() > capacity) {
            queue.poll();
        }
    }

    public synchronized String build() {
        Iterator<String> iterator = queue.iterator();
        StringBuilder builder = new StringBuilder();
        while(iterator.hasNext()) {
            builder.append(iterator.next());
        }
        return builder.toString();
    }
}
