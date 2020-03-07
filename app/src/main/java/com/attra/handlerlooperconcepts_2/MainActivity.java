package com.attra.handlerlooperconcepts_2;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mainText;
    private Handler handler;
    private ProgressDialog dialog;
// we are trying to send message from background thread to main Thread
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainText=findViewById(R.id.activity_main_text);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Operation in progress");
        dialog.setTitle("Please Wait");
        dialog.setCancelable(false);
        //Handler class reference will persist till activity persist in the memory
       handler=new Handler(getMainLooper()){

           @Override
           public void handleMessage(Message msg) {
               dialog.hide();
               String data=msg.getData().getString("MSG-DATA");
               mainText.setText(data);
           }
       };
    }

    public void RunCode(View view) {
        dialog.show();


        // Created the background thread
        Thread thread=new SongThread(handler);
        thread.setName("My Thread 1");
        thread.start();
    }


}
