package com.lilu.base.utils.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.lilu.base.R;
import com.lilu.base.utils.ObjectUtils;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

/**
 * Description:
 *
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class NotificationHelper {

    CharSequence title;
    CharSequence content;
    Intent intent;

    private static NotificationCompat.Builder createNotificationBuilder(Context context, Channel channel){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //初始化渠道  渠道编号 渠道名称 通知重要性
            NotificationChannel notificationChannel =  new NotificationChannel(channel.getChannelId(),channel.getName(),channel.getImportance());
            //通知描述
            notificationChannel.setDescription(channel.getDescription());
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return new NotificationCompat.Builder(context,channel.getChannelId())
                .setPriority(getPriority(channel))
                .setSmallIcon(R.drawable.ic_notification_small)
                .setAutoCancel(true);
    }


    public static class Builder{

        Context mContext;
        NotificationCompat.Builder builder;

        public Builder(Context context,Channel channel){
            this.mContext = context;
            builder = createNotificationBuilder(context,channel);
        }

        public Builder title(String title){
            builder.setContentTitle(title);
            return this;
        }

        public Builder text(String text){
            builder.setContentText(text);
            return this;
        }

        public NotificationCompat.Builder getBuilder() {
            return builder;
        }

        /*        public Builder bigIcon(int bigIcon){
            builder.setStyle(new NotificationCompat.BigPictureStyle()
            .bigLargeIcon(BitmapFactory.decodeResource(mContext.getResources(),bigIcon))
            .bigPicture(BitmapFactory.decodeResource(mContext.getResources(),bigIcon)));
            return this;
        }

        public Builder bigText(String bigText){
            builder.setStyle(new NotificationCompat.BigTextStyle()
            .bigText(bigText)
            .setSummaryText("二级标题"));
            return this;
        }

        public Builder style(){
            builder.setStyle(new NotificationCompat.InboxStyle()
                    .addLine("第一").addLine("第二").addLine("第三"));
            return this;
        }*/

        public Builder style(NotificationCompat.Style style){
            builder.setStyle(style);
            return this;
        }


        public Builder intent(Intent intent){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(mContext,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
              return this;
        }
        public void showNotify(int notifyId){

            NotificationManagerCompat.from(mContext).notify(notifyId,builder.build());

        }
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


    /**
     * 对安卓8.0进行通知适配
     * 渠道ID 渠道名称  通知重要性   三项必须
     * 通知描述可选
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void createChannel(Context context, Channel channel){
        //初始化渠道  渠道编号 渠道名称 通知重要性
        NotificationChannel notificationChannel =  new NotificationChannel(channel.getChannelId(),channel.getName(),channel.getImportance());
        //通知描述
        notificationChannel.setDescription(channel.getDescription());
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

    }

    private static int getPriority(Channel channel){

        switch (channel.getImportance()){
            case NotificationManager.IMPORTANCE_HIGH:
                return NotificationCompat.PRIORITY_HIGH;
            case NotificationManager.IMPORTANCE_LOW:
                return NotificationCompat.PRIORITY_LOW;
            case NotificationManager.IMPORTANCE_MIN:
                return NotificationCompat.PRIORITY_MIN;
            default:
                return NotificationCompat.PRIORITY_DEFAULT;
        }
    }

}
