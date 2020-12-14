package com.lilu.base.utils.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;

/**
 * Description:
 *
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class NotificationUtil {

    /** 通知渠道-(重要性级别-高：发出声音) */
    private static Channel LEVEL1 = new Channel("1","LEVEL1", NotificationManager.IMPORTANCE_DEFAULT,"",0,null,null);
    /** 通知渠道-(重要性级别-紧急：发出提示音，并以浮动通知的形式显示 & 锁屏显示 & 振动0.25s )*/
    private static Channel LEVEL2 = new Channel("2","LEVEL2", NotificationManager.IMPORTANCE_HIGH,"", NotificationCompat.VISIBILITY_PUBLIC,new long[]{0,250},null);
    /** 通知渠道-(重要性级别-中：无提示音) */
    private static Channel LEVEL3 = new Channel("3","LEVEL3", NotificationManager.IMPORTANCE_LOW,"",0,null,null);
    /** 通知渠道-(重要性级别-紧急：发出提示音，并以浮动通知的形式显示 & 锁屏显示 & 振动4s停2s再振动4s ) */
    private static Channel LEVEL4 = new Channel("4","LEVEL4", NotificationManager.IMPORTANCE_HIGH,"",NotificationCompat.VISIBILITY_PUBLIC,new long[]{0, 4000, 2000, 4000},null);


    public static void showNotificationLevel1(Context context,int id,String title,String text){

        NotificationCompat.Builder builder = NotificationHelper.createNotificationBuilder(context, LEVEL1,title,text,null);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(text));
        NotificationHelper.showNotify(context,id,builder.build());

    }

    public static void showNotificationLevel2(Context context,int id,String title,String text){

        NotificationCompat.Builder builder = NotificationHelper.createNotificationBuilder(context, LEVEL2,title,text,null);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(text));
        NotificationHelper.showNotify(context,id,builder.build());

    }

    public static void showNotificationLevel3(Context context,int id,String title,String text){

        NotificationCompat.Builder builder = NotificationHelper.createNotificationBuilder(context, LEVEL3,title,text,null);
        NotificationHelper.showNotify(context,id,builder.build());

    }

    public static void showNotificationLevel4(Context context,int id,String title,String text){

        NotificationCompat.Builder builder = NotificationHelper.createNotificationBuilder(context, LEVEL4,title,text,null);
        NotificationHelper.showNotify(context,id,builder.build());

    }

    /**
     * 构建应用通知的默认配置
     * @param
     */
    private Notification buildDefaultConfig(NotificationCompat.Builder builder){
        return builder.build();
    }
}
