package sjq.bitcoin.script;

import java.util.Stack;

public class OperandStack {

    public static final int MAX_SCRIPT_ELEMENT_SIZE = 520;

    private final Stack<byte[]> stack = new Stack<>();

    public void push(byte[] item) {
        if (stack.size()>MAX_SCRIPT_ELEMENT_SIZE) {
            throw new ScriptException("operand stack is overflow!");
        }
        stack.push(item);
    }

    public byte[] pop() {
        return stack.pop();
    }

    public byte[] peek() {
        return stack.peek();
    }

    public byte[] get(int index) {
        if (index>=stack.size()) {
            throw new ScriptException("operand stack is overflow!");
        }
        return stack.get(index);
    }

    public void remove(int index) {
        if (index>=stack.size()) {
            throw new ScriptException("operand stack is overflow!");
        }
        stack.remove(index);
    }

    public boolean peekSuccess() {
        stack.peek();
        return true;
    }

    public int size() {
        return stack.size();
    }
}
