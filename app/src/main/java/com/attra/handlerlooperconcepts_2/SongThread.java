package com.attra.handlerlooperconcepts_2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SongThread extends Thread {

    private Handler handler;
    private String songs[] ={"Song1","Song2","Song3"};

    public SongThread(Handler handler) {
       this.handler=handler;
    }

    @Override
    public void run() {

        for(String song:songs){
            SongDownload(song);
        }

    }

    private void SongDownload(String song){

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Bundle bundle=new Bundle();
        bundle.putString("MSG-DATA",song);
        Message msg=new Message();
        msg.setData(bundle);
        handler.sendMessage(msg);
    }
}
