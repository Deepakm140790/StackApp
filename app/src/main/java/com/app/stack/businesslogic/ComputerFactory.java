package com.app.stack.businesslogic;

import com.app.stack.interfaces.IComputer;
import com.app.stack.util.Constants;
import com.app.stack.util.PrintLog;

public class ComputerFactory {
    // TAG name
    private static final String TAG = "ComputerFactory";

    public IComputer getApproach(String approach, int size) {
        switch (approach) {
            case Constants.APPROACH1:
                return new Computer(size);

            case Constants.APPROACH2:
                return new Computer2(size);

            default:
                PrintLog.e(TAG, "Invalid approach");
                break;
        }
        return null;
    }
}
