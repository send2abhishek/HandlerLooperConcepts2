package com.attra.handlerlooperconcepts_2;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DownloadHandler extends Handler {




    @Override
    public void handleMessage(Message msg) {


        SongDownload(msg.obj.toString());

    }



    private void SongDownload(String song){

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Bundle bundle=new Bundle();
        bundle.putString("MSG-KEY",song);
        Message message=Message.obtain();
        message.setData(bundle);
        MainActivity.handler.sendMessage(message);
        Log.d("laura", "SongDownload: "+ song);



    }
}
