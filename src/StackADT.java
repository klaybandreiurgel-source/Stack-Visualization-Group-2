/*
Description: This program implements an array-based stack with a
maximum capacity of 10 elements. It supports PUSH, POP, PEEK,
isEmpty, isFull, size, and CLEAR operations.

Programmed by: Gonzaga, Lumanta, BSIT, 48143, Data Structure and Algorithms
Last Modified: October 21, 2026
Version: 1.0
Acknowledgements: OpenAI ChatGPT (GPT-6) assisted with drafting and explaining
the Java stack implementation.
Prompt used: "Assist me create a Java Stack ADT using an array
with PUSH, POP, PEEK, isEmpty, isFull, size, and CLEAR operations."
*/

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

 public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("STACK EMPTY: Cannot peek, stack is empty");
        }
        return data[top];
    }

    public void clear() {
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == MAX_CAPACITY - 1;
    }

    public int size() {
        return top + 1;
    }

    public int capacity() {
        return MAX_CAPACITY;
    }

    public int[] toArray() {
        int[] copy = new int[size()];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = data[i];
        }
        return copy;
    }
}
