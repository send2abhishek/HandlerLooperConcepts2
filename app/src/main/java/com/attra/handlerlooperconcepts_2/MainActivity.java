package com.attra.handlerlooperconcepts_2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mainText;
    private Handler handler;
// we are trying to send message from background thread to main Thread
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainText=findViewById(R.id.activity_main_text);

        //Handler class reference will persist till activity persist in the memory
       handler=new Handler(getMainLooper()){

           @Override
           public void handleMessage(Message msg) {

               String data=msg.getData().getString("MSG-DATA");
               mainText.setText(data);
           }
       };
    }

    public void RunCode(View view) {

        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Bundle bundle=new Bundle();
                bundle.putString("MSG-DATA","Data Received from background thread");

                Message message=new Message();
                message.setData(bundle);

                handler.sendMessage(message);
            }
        };

        // Created the background thread
        Thread thread=new Thread(runnable);
        thread.setName("My Thread 1");
        thread.start();
    }


}
