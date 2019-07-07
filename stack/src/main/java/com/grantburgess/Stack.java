package com.grantburgess;

public interface Stack {

    static Stack make(int capacity) {
        if (capacity == 0)
            return new ZeroCapacityStack();

        return new BoundedStack(capacity);
    }

    boolean isEmpty();

    void push(int n);

    int size();

    int pop();

    int top();

    int find(int n);

    public class Overflow extends RuntimeException { }

    public class Underflow extends RuntimeException { }

    public class IllegalCapacity extends RuntimeException { }

    public class Empty extends RuntimeException { }
}
