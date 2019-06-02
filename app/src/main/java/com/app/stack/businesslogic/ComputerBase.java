package com.app.stack.businesslogic;

import com.app.stack.util.PrintLog;

public class ComputerBase {
    // TAG name
    private static final String TAG = "ComputerBase";

    // Function to stop execution
    public void stop() {
        PrintLog.d(TAG, "Exit Program");
        System.exit(0);
    }

    // Function to print all stack elements
    public void printArray(int array[]) {
        for (int i = 0; i < array.length; i++) {
            PrintLog.d(TAG, "------------ Printing array from stack ------------");
            PrintLog.d(TAG, "Stack address = " + i + "  ---   Value " + array[i]);
        }
    }
}
