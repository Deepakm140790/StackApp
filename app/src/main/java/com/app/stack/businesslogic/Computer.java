package com.app.stack.businesslogic;

import com.app.stack.interfaces.IComputer;
import com.app.stack.util.PrintLog;

import static com.app.stack.util.Constants.CALL;
import static com.app.stack.util.Constants.MULT;
import static com.app.stack.util.Constants.PRINT;
import static com.app.stack.util.Constants.PUSH;
import static com.app.stack.util.Constants.RET;
import static com.app.stack.util.Constants.STOP;

public class Computer extends ComputerBase implements IComputer {
    // TAG name
    private static final String TAG = "Computer";
    //Array
    public final int[] array;
    //Initial stack position
    public int pointerAddress = -1;
    //Stack capacity
    public final int capacity;

    // Constructor
    protected Computer(int size) {
        array = new int[size];
        capacity = size;
    }

    //Get stack pointerAddress
    public int getPointerAddress() {
        PrintLog.d(TAG, "Pointer address is " + pointerAddress);
        return pointerAddress;
    }

    // Set stack pointerAddress
    private int setPointerAddress(int address) {
        if (address > capacity) {
            PrintLog.e(TAG, "Couldn't able to set the address because address > capacity");
            return -1;
        }
        pointerAddress = address - 1;
        PrintLog.d(TAG, "Set address to " + address + "; Pointer address is set to " + pointerAddress);
        return 0;
    }

    @Override
    public int setAddress(int address) {
        return setPointerAddress(address);
    }

    @Override
    public void insert(String str) {
        operation(str, -1);
    }

    @Override
    public void insert(String str, int value) {
        operation(str, value);
    }

    @Override
    public void execute() {
        //TODO - what operation?
    }

    private void operation(final String operation, final int value) {
        switch (operation) {
            case MULT:
                PrintLog.d(TAG, "----- Start of multiply -----");
                //Pop 2 elements from the stack
                int x1 = pop(pointerAddress, array);
                getPointerAddress();
                int x2 = pop(pointerAddress, array);
                getPointerAddress();

                //Multiply
                int result = multiply(x1, x2);

                //Push the result to stack
                push(result, pointerAddress, capacity, array);
                getPointerAddress();
                PrintLog.d(TAG, "----- End of multiply -----");
                break;

            case CALL:
                call(value);
                break;

            case PRINT:
                print();
                break;

            case PUSH:
                push(value, pointerAddress, capacity, array);
                break;

            case RET:
                ret();
                break;

            case STOP:
                stop();
                break;

            default:
                PrintLog.e(TAG, "Invalid operation");
                break;
        }
    }

    public int multiply(int x1, int x2) {
        int result = x1 * x2;
        PrintLog.d(TAG, "MULTIPLY result is " + result);
        return result;
    }

    private void call(int address) {
        //Set pointerAddress to specific address
        setPointerAddress(address);
    }

    private void ret() {
        //Pop address from stack and set PC to address
        pop(pointerAddress, array);
    }

    private void print() {
        //Pop value from stack and print it
        pop(pointerAddress, array);
    }

    // Function to add an element x in the stack
    public int push(int x, int pointer, int capacity, int[] array) {
        if (isFull(pointer, capacity)) {
            PrintLog.e(TAG, "OverFlow error");
            return -1;
        }

        try {
            //Increase the stack size by 1 and inserting the value to stack
            int address = ++pointerAddress;
            array[address] = x;
            PrintLog.d(TAG, "Push " + x + " to stack address " + address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Function to pop top element from the stack
    public int pop(int pointer, int[] array) {
        // check for stack underflow
        if (isEmpty(pointer)) {
            PrintLog.e(TAG, "UnderFlow error");
            return -1;
        }

        int popElement = array[pointer];
        //Decrease stack size by 1 and return the popped element
        pointerAddress--;
        PrintLog.d(TAG, "Pop " + popElement + " from stack address " + pointer);
        return popElement;
    }

    // Function to check if the stack is full or not
    public Boolean isFull(int pointer, int capacity) {
        return pointer == capacity - 1;
    }

    // Function to check if the stack is empty or not
    public Boolean isEmpty(int pointer) {
        return pointer == -1;
    }
}
