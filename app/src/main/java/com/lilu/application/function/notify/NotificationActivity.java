package com.lilu.application.function.notify;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lilu.application.Constance;
import com.lilu.application.R;
import com.lilu.application.function.statuslayout.StatusLayoutActivity;
import com.lilu.base.activity.RecyclerViewActivity;
import com.lilu.base.utils.notification.NotificationHelper;
import com.lilu.base.utils.notification.NotificationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.lilu.base.utils.notification.NotificationUtils.LEVEL1;
import static com.lilu.base.utils.notification.NotificationUtils.LEVEL2;
import static com.lilu.base.utils.notification.NotificationUtils.LEVEL3;
import static com.lilu.base.utils.notification.NotificationUtils.LEVEL4;

/**
 * Description:
 * 通知适配界面
 * @author lilu on 2020/12/15
 * No one knows this better than me
 */
@Route(path = Constance.ACTIVITY_NOTIFY)
public class NotificationActivity extends RecyclerViewActivity {


    private NotificationAdapter mAdapter;
    private List<NotifyEntity> mList = new ArrayList<>();


    @Override
    public void setAdapter(RecyclerView baseRv) {

        NotifyEntity entity1 = new NotifyEntity(1,"基本通知栏消息","这是一条基本的通知栏消息");
        NotifyEntity entity2 = new NotifyEntity(2,"跳转activity","用于测试是否能跳转到activity");
        NotifyEntity entity3 = new NotifyEntity(3,"测试返回任务栈","用于测试返回任务站的通知栏消息");
        NotifyEntity entity4 = new NotifyEntity(4,"测试跳转到特殊的activity","用于测试跳转到特殊Activity的通知栏消息");

        NotifyEntity entity5 = new NotifyEntity(5,"大图通知","大图片的一个通知");
        NotifyEntity entity6 = new NotifyEntity(6,"大段文字","一段很长很长文字的通知");
        NotifyEntity entity7 = new NotifyEntity(7,"邮件","收到新邮件");
        NotifyEntity entity8 = new NotifyEntity(8,"联系人","联系人发来新消息");
        NotifyEntity entity9 = new NotifyEntity(9,"操作按钮","包含操作按钮的通知");
        NotifyEntity entity10 = new NotifyEntity(10,"确定的进度条","包含精度条的通知");
        NotifyEntity entity11 = new NotifyEntity(11,"不确定进度","不确定进度的进度条");
        NotifyEntity entity12 = new NotifyEntity(12,"锁屏公开范围","可以设置锁屏的公开范围");
        NotifyEntity entity13 = new NotifyEntity(13,"紧急消息","显示紧急消息");

        mList.add(entity1);
        mList.add(entity2);
        mList.add(entity3);
        mList.add(entity4);
        mList.add(entity5);
        mList.add(entity6);
        mList.add(entity7);
        mList.add(entity8);
        mList.add(entity9);
        mList.add(entity10);
        mList.add(entity11);
        mList.add(entity12);
        mList.add(entity13);


        baseRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mAdapter = new NotificationAdapter(mList);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {

                NotifyEntity item = mList.get(position);
                switch (position){
                    case 0:
                        new NotificationHelper.Builder(NotificationActivity.this, LEVEL1)
                                .title(item.getTitle())
                                .text(item.getContent())
                                .showNotify(item.getId());
                        break;
                    case 1:
                        new NotificationHelper.Builder(NotificationActivity.this, LEVEL1)
                                .title(item.getTitle())
                                .text(item.getContent())
                                .intent(new Intent(NotificationActivity.this, StatusLayoutActivity.class))
                                .showNotify(item.getId());
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        new NotificationHelper.Builder(NotificationActivity.this, LEVEL1)
                                .title(item.getTitle())
                                .text(item.getContent())
                                .style(new NotificationCompat.BigPictureStyle()
                                        .bigLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.img_big))
                                        .bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.img_big))
                                .setSummaryText("大图片二级")
                                .setBigContentTitle("大图片二级标题"))
                                .intent(new Intent(NotificationActivity.this, StatusLayoutActivity.class))
                                .showNotify(item.getId());
                        break;
                    case 5:
                        new NotificationHelper.Builder(NotificationActivity.this, LEVEL1)
                                .title(item.getTitle())
                                .text(item.getContent())
                                .style(new NotificationCompat.BigTextStyle()
                                        .bigText("大大大大大大蚊子大大大大大大蚊子大大大大大大蚊子大大大大大大蚊子大大大大大大蚊子大大大大大大蚊子大大大大大大蚊子大大大大大大蚊子")
                                        .setSummaryText("大蚊子二级")
                                        .setBigContentTitle("大蚊子二级标题"))
                                .intent(new Intent(NotificationActivity.this, StatusLayoutActivity.class))
                                .showNotify(item.getId());
                        break;
                    case 6:
                        new NotificationHelper.Builder(NotificationActivity.this, LEVEL1)
                                .title(item.getTitle())
                                .text(item.getContent())
                                .style(new NotificationCompat.InboxStyle()
                                        .addLine("第一条邮件")
                                        .addLine("第二条邮件")
                                        .addLine("第三条邮件")
                                        .setSummaryText("邮件二级")
                                        .setBigContentTitle("邮件二级标题"))
                                .intent(new Intent(NotificationActivity.this, StatusLayoutActivity.class))
                                .showNotify(item.getId());
                        break;
                    case 7:

                        Person person = new Person.Builder()
                                .setIcon(IconCompat.createWithResource(NotificationActivity.this,R.drawable.tab1_normal))
                                .setName("llilu")
                                .build();
                        new NotificationHelper.Builder(NotificationActivity.this, LEVEL1)
                                .title(item.getTitle())
                                .text(item.getContent())
                                .style(new NotificationCompat.MessagingStyle(person)
                                .addMessage(new NotificationCompat.MessagingStyle.Message("第一",System.currentTimeMillis(),person))
                                .addMessage(new NotificationCompat.MessagingStyle.Message("第2",System.currentTimeMillis(),person))
                                .addMessage(new NotificationCompat.MessagingStyle.Message("第3",System.currentTimeMillis(),person))
                                .setConversationTitle("额外的什么东西"))
                                .intent(new Intent(NotificationActivity.this, StatusLayoutActivity.class))
                                .showNotify(item.getId());
                        break;

                    case 8:
                        NotificationCompat.Builder builder = new NotificationHelper.Builder(NotificationActivity.this, LEVEL1)
                                .title(item.getTitle())
                                .text(item.getContent())
                                .getBuilder();

                        Intent readIntent = new Intent(NotificationActivity.this,NotifyReceiver.class);
                        PendingIntent readAction = PendingIntent.getBroadcast(NotificationActivity.this,10,readIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                        Intent detailIntent = new Intent(NotificationActivity.this,StatusLayoutActivity.class);
                        PendingIntent detailAction = PendingIntent.getActivity(NotificationActivity.this,11,detailIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                        builder.addAction(R.drawable.tab1_normal,"已读",readAction);
                        builder.addAction(R.drawable.tab2_normal,"详情",detailAction);

                        NotificationManagerCompat.from(NotificationActivity.this).notify(item.getId(),builder.build());
                        break;
                    case 9:
                        NotificationCompat.Builder builder9 = new NotificationHelper.Builder(NotificationActivity.this, LEVEL1)
                                .title(item.getTitle())
                                .text(item.getContent())
                                .getBuilder();

                        builder9.setProgress(100,0,false);
                        NotificationManagerCompat.from(NotificationActivity.this).notify(item.getId(),builder9.build());

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                builder9.setProgress(100,20,false);
                                NotificationManagerCompat.from(NotificationActivity.this).notify(item.getId(),builder9.build());
                            }
                        },3000);

                        break;

                    case 10:
                        NotificationCompat.Builder builder10 = new NotificationHelper.Builder(NotificationActivity.this, LEVEL1)
                                .title(item.getTitle())
                                .text(item.getContent())
                                .getBuilder();

                        builder10.setProgress(0,0,true);
                        NotificationManagerCompat.from(NotificationActivity.this).notify(item.getId(),builder10.build());
                        break;
                    case 11:
                        NotificationCompat.Builder builder11 = new NotificationHelper.Builder(NotificationActivity.this, LEVEL1)
                                .title(item.getTitle())
                                .text(item.getContent())
                                .getBuilder();
                        builder11.setPriority(NotificationCompat.VISIBILITY_PUBLIC);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                NotificationManagerCompat.from(NotificationActivity.this).notify(item.getId(),builder11.build());
                            }
                        },3000);
                        break;
                    default:

                        NotificationCompat.Builder builder12 = new NotificationHelper.Builder(NotificationActivity.this, LEVEL1)
                                .title(item.getTitle())
                                .text(item.getContent())
                                .getBuilder();

                        Intent fullIntent = new Intent(NotificationActivity.this,ImportantActivity.class);
                        PendingIntent fullPending = PendingIntent.getActivity(NotificationActivity.this,11,fullIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                        builder12.setFullScreenIntent(fullPending,true);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                NotificationManagerCompat.from(NotificationActivity.this).notify(item.getId(),builder12.build());
                            }
                        },3000);


                        break;
                }
            }
        });

        baseRv.setAdapter(mAdapter);

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);

        setTitle("通知适配");


    }
}
