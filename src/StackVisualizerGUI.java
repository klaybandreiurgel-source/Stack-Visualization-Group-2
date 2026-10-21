public class MyStack {

    public static final int MAX_CAPACITY = 10;

    private final int[] data;
    private int top;

    public MyStack() {
        data = new int[MAX_CAPACITY];
        top = -1;
    }

    public void push(int value) {
        if (isFull()) {
            throw new IllegalStateException("STACK OVERFLOW: Cannot push, stack is full");
        }
        top++;
        data[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("STACK UNDERFLOW: Cannot pop, stack is empty");
        }
        int value = data[top];
        top--;
        return value;
    }
