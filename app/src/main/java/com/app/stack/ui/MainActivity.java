package com.app.stack.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.app.stack.R;
import com.app.stack.businesslogic.ComputerFactory;
import com.app.stack.interfaces.IComputer;
import com.app.stack.util.Constants;
import com.app.stack.util.PrintLog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private static final int PRINT_TENTEN_BEGIN = 50;
    private static final int MAIN_BEGIN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    // Initialize UI elements
    private void init() {
        Button start = findViewById(R.id.bt_start);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                new BackgroundThread().execute();
                break;

            default:
                break;
        }
    }

    //Async task to execute in Background thread.
    private class BackgroundThread extends AsyncTask<String, String, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            PrintLog.d(TAG, "doInBackground");
            startExecution();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            PrintLog.d(TAG, "onPostExecute");
        }
    }

    private void startExecution() {
        // Approach 1
        PrintLog.d(TAG, "-------- Start of Approach1 execution --------");
        IComputer iComputer = new ComputerFactory().getApproach(Constants.APPROACH1, 100);
        iComputer.setAddress(PRINT_TENTEN_BEGIN);
        iComputer.insert(Constants.MULT);
        iComputer.insert(Constants.PRINT);
        iComputer.insert(Constants.RET);

        iComputer.setAddress(MAIN_BEGIN);
        iComputer.insert(Constants.PUSH, 1009);
        iComputer.insert(Constants.PRINT);
        iComputer.insert(Constants.PUSH, 6);
        iComputer.insert(Constants.PUSH, 101);
        iComputer.insert(Constants.PUSH, 10);
        iComputer.insert(Constants.CALL, PRINT_TENTEN_BEGIN);
//        iComputer.insert(Constants.STOP);
        PrintLog.d(TAG, "-------- End of Approach1 execution --------");

        // Approach 2
        PrintLog.d(TAG, "-------- Start of Approach2 execution --------");
        IComputer iComputer2 = new ComputerFactory().getApproach(Constants.APPROACH2, 100);
        iComputer2.setAddress(PRINT_TENTEN_BEGIN);
        iComputer2.insert(Constants.MULT);
        iComputer2.insert(Constants.PRINT);
        iComputer2.insert(Constants.RET);

        iComputer2.setAddress(MAIN_BEGIN);
        iComputer2.insert(Constants.PUSH, 1009);
        iComputer2.insert(Constants.PRINT);
        iComputer2.insert(Constants.PUSH, 6);
        iComputer2.insert(Constants.PUSH, 101);
        iComputer2.insert(Constants.PUSH, 10);
        iComputer2.insert(Constants.CALL, PRINT_TENTEN_BEGIN);
        iComputer2.insert(Constants.STOP);
        // FIXME - below statements will not execute because program is exited.
        iComputer.setAddress(MAIN_BEGIN);
        iComputer.execute();
        PrintLog.d(TAG, "-------- End of Approach2 execution --------");
    }
}
