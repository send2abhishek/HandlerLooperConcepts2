package com.attra.handlerlooperconcepts_2;



import android.os.Handler;
import android.os.Looper;


public class SongThread extends Thread {

    public Handler handler;



    @Override
    public void run() {

        //creates the looper and Message Queue for the background thread
        Looper.prepare();
        handler=new DownloadHandler();

        // it will loop the looper, means it will take one task at once and pass to the download handler class
        Looper.loop();

    }


}
