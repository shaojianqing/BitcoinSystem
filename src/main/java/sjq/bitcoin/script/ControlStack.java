package sjq.bitcoin.script;

import java.util.Stack;

public class ControlStack {

    private static final int MAX_CONTROL_DEPTH = 200;

    private final Stack<Boolean> stack = new Stack<>();

    public void push(Boolean value) {
        if (stack.size()>MAX_CONTROL_DEPTH) {
            throw new ScriptException("control stack is overflow!");
        }
        stack.push(value);
    }

    public Boolean pop() {
        return stack.pop();
    }

    public Boolean contain(Boolean value) {
        return stack.contains(value);
    }

    public Boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }
}
