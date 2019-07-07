package com.grantburgess;

public class BoundedStack implements Stack {
    private int capacity;
    private int size;
    private int elements[];

    public BoundedStack(int capacity) {
        if (capacity < 0)
            throw new IllegalCapacity();

        this.capacity = capacity;
        elements = new int[capacity];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(int n) {
        if (size == capacity)
            throw new Overflow();

        elements[size++] = n;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int pop() {
        if (isEmpty())
            throw new Underflow();

        return elements[--size];
    }

    @Override
    public int top() {
        if (isEmpty())
            throw new Empty();

        return elements[size - 1];
    }

    @Override
    public int find(int n) {
        for (int i = size - 1; i >= 0; i--)
            if (elements[i] == n)
                return (size - 1) - i;

        return -1;
    }
}
