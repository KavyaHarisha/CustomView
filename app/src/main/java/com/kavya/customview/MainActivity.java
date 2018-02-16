package com.kavya.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.view_text);

        mTimer = new Timer();
        MyTimeTasker tasker = new MyTimeTasker();
        //schedule to change background color every second
        mTimer.schedule(tasker,1000,1000);

    }

    public class MyTimeTasker extends TimerTask {

        @Override
        public void run() {
            //Since we want to change something which is on hte UI
            //so we have to run on UI thread..
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Random random=new Random();//this is random generator
                    mTextView.setBackgroundColor(Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
                }
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //you have to stop the timer when is your activity has stopped
        //otherwise it will keep running in the background
        mTimer.cancel();
    }
}
