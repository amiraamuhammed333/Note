package com.example.farida.note;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Random;

public class NoteAlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e ( "receiver","onreceive" );
        String title =intent.getStringExtra ( "title" );
        String desc=intent.getStringExtra ( "desc" );
        showNotification ( context,title,desc );

    }

    public void showNotification(Context context,String title,String desc){

        NotificationCompat.Builder builder = new NotificationCompat.Builder
                ( context,context.getString ( R.string.note_channel_id ) );

        builder.setContentTitle ( title )
       .setContentText ( desc )
                .setAutoCancel ( true )
                .setSmallIcon (R.drawable.ic_notification  )
                .setStyle (new  NotificationCompat.BigTextStyle().bigText("this is big text  " +
                        "  mhj jhghjk uddl ghssl ksj" +
                        "shjshs jshgj trsg hshjs jkj" +
                        "hsghs hsj hsjk sghj kjh" +
                        "shyst syusj hsjkkjh") );
        NotificationManager notificationManager = (NotificationManager) context.getSystemService ( Context.NOTIFICATION_SERVICE );

        Random random = new Random (  );
        notificationManager.notify (random.nextInt (100) ,builder.build () );
    }
}
