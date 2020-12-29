package com.lilu.base.utils.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

/**
 * Description:
 *
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class NotificationUtils {

    /**
     * 重要性级别-紧急：发出提示音，并以浮动通知的形式显示 & 锁屏显示 & 振动4s停2s再振动4s
     * 适用语音电话提示
     */
    public static Channel LEVEL1 = new Channel("1","LEVEL1", NotificationManager.IMPORTANCE_HIGH,"紧急，发出声音并作为警告通知出现");
    /**
     * 重要性级别-高：发出提示音 & 锁屏显示
     * 适用及时聊天信息
     */
    public static Channel LEVEL2 = new Channel("2","LEVEL2", NotificationManager.IMPORTANCE_DEFAULT,"高，发出声音");
    /**
     * 重要性级别-中：无提示音 & 锁屏不显示
     * 适用推送
     */
    public static Channel LEVEL3 = new Channel("3","LEVEL3", NotificationManager.IMPORTANCE_LOW,"中，没有声音");
    /**
     * 重要性级别-低：无提示音 & 锁屏不显示
     */
    public static Channel LEVEL4 = new Channel("4","LEVEL4", NotificationManager.IMPORTANCE_MIN,"低，没有声音并且不会出现在状态栏中，并且通知会被折叠");


//    public static void showNotify(Context context, Channel level,int id, String title, String text, Intent intent){
//
//        NotificationCompat.Builder builder = NotificationHelper.createNotificationBuilder(context, level);
//        builder.setContentTitle(title);
//        builder.setContentText(text);
//        NotificationHelper.showNotify(context,id,builder.build());
//
//
//        new NotificationHelper.Builder(context,level).text("")
//        .title("");
//
//
//    }

}
