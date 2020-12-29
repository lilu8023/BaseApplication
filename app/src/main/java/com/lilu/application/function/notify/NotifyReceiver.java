package com.lilu.application.function.notify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lilu.base.utils.logger.Logger;

/**
 * Description:
 *
 * @author lilu on 2020/12/29
 * No one knows this better than me
 */
public class NotifyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.i("已读收到");
    }
}
