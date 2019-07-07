package com.grantburgess;

public class ZeroCapacityStack implements Stack {
    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void push(int n) {
        throw new BoundedStack.Overflow();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int pop() {
        throw new Underflow();
    }

    @Override
    public int top() {
        throw new Empty();
    }

    @Override
    public int find(int n) {
        return -1;
    }
}
