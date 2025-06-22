package com.amonlexasoftware.yaradio.support.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.amonlexasoftware.yaradio.models.RadioModel;

public class Notification{

    public static final String channelID = "ChannedID";


    public static final String ACTIONPREVIOUS = "actionprevious";
    public static final String CHANNEL_PLAY = "actionplay";
    public static final String CHANNEL_NEXT = "actionnext";

    public static android.app.Notification notification;

    public static void createNotification(Context context, RadioModel radioModel, int playButton, int pos) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context,"tag");

            Bitmap icon = BitmapFactory.decodeResource(context.getResources(),radioModel.getLogo());

            notification = new NotificationCompat.Builder(context,channelID)
                    .setSmallIcon(radioModel.getLogo())
                    .setContentTitle(radioModel.getName())
                    .setContentText(radioModel.getDescription())
                    .setLargeIcon(icon)
                    .setOnlyAlertOnce(true)
                    .setShowWhen(false)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build();

            notificationManagerCompat.notify(1,notification);
        }
    }

}
