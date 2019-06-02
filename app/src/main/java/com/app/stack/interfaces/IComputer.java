package com.app.stack.interfaces;

public interface IComputer {
    // Set the stack pointer to specific location.
    int setAddress(int address);

    // Insert the element to stack.
    void insert(String str);
    void insert(String str, int value);

    void execute();
}
