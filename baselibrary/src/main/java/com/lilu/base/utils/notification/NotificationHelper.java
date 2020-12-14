package com.lilu.base.utils.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.lilu.base.utils.ObjectUtils;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

/**
 * Description:
 *
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class NotificationHelper {


    public static NotificationCompat.Builder createNotificationBuilder(Context context, Channel channel, CharSequence title, CharSequence content, Intent intent){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel(context,channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channel.getChannelId())
                .setPriority(getLowVersionPriority(channel))
                .setVisibility(channel.getLockScreenVisibility())
                .setVibrate(channel.getVibrationPattern())
                .setSound(ObjectUtils.isEmpty(channel.getSound())?channel.getSound():Settings.System.DEFAULT_NOTIFICATION_URI)
                .setOnlyAlertOnce(true);

        if(!TextUtils.isEmpty(title)){
            builder.setContentTitle(title);
        }
        if(!TextUtils.isEmpty(content)){
            builder.setContentText(content);
        }
        if(intent != null){
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);

            if(NotificationManager.IMPORTANCE_HIGH == channel.getImportance()){
                builder.setFullScreenIntent(pendingIntent,false);
            }
        }

        return builder;

    }



    public static void showNotify(Context context,int id,Notification notification){

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id,notification);

    }


    public static void cancelNotify(Context context,int id){

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(id);

    }


    public static void cancelAll(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void createChannel(Context context, Channel channel){
        NotificationChannel notificationChannel =  new NotificationChannel(channel.getChannelId(),channel.getName(),channel.getImportance());
        notificationChannel.setDescription(channel.getDescription());
        notificationChannel.setVibrationPattern(channel.getVibrationPattern());
        if(!ObjectUtils.isEmpty(channel.getSound())){
            notificationChannel.setSound(Settings.System.DEFAULT_NOTIFICATION_URI,notificationChannel.getAudioAttributes());
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

    }

    private static int getLowVersionPriority(Channel channel){

        switch (channel.getImportance()){
            case NotificationManager.IMPORTANCE_HIGH:
                return NotificationCompat.PRIORITY_HIGH;
            case NotificationManager.IMPORTANCE_LOW:
                return NotificationManager.IMPORTANCE_LOW;
            case NotificationManager.IMPORTANCE_MIN:
                return NotificationManager.IMPORTANCE_MIN;
            default:
                return NotificationCompat.PRIORITY_DEFAULT;
        }
    }



}
