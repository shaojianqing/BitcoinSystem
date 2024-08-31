package sjq.bitcoin.logger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Buffer {

    private static final int DEFAULT_CAPACITY = 100;

    private Queue<String> queue;

    private int capacity = DEFAULT_CAPACITY;

    public Buffer() {
        this.capacity = DEFAULT_CAPACITY;
        this.queue = new LinkedList<String>();
    }

    public Buffer(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<String>();
    }

    public void log(String content) {
        queue.offer(content);
        if (queue.size() > capacity) {
            queue.poll();
        }
    }

    public String build() {
        Iterator<String> iterator = queue.iterator();
        StringBuilder builder = new StringBuilder();
        while(iterator.hasNext()) {
            builder.append(iterator.next());
        }
        return builder.toString();
    }
}
