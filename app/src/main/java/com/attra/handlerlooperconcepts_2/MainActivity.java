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
    public static Handler handler;
    private ProgressDialog dialog;
    private String songs[] ={"Song1","Song2","Song3"};
    private SongThread songThread;
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

       // mainText.setText(data);

        handler=new Handler(getMainLooper()){

            @Override
            public void handleMessage(Message msg) {

                dialog.hide();
                mainText.setText(msg.getData().getString("MSG-KEY"));

            }
        };


        // Created the background thread
        songThread=new SongThread();
        songThread.setName("My Thread 1");
        songThread.start();
    }



    public void RunCode(View view) {
        dialog.show();

        for(String song:songs){

            // creates the reusable object of message class
            Message msg=Message.obtain();
            msg.obj=song;
            // sending data to message queue
            songThread.handler.sendMessage(msg);
        }

    }


}
