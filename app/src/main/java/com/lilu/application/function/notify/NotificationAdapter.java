package com.lilu.application.function.notify;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lilu.application.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Description:
 *
 * @author lilu on 2020/12/22
 * No one knows this better than me
 */
public class NotificationAdapter extends BaseQuickAdapter<NotifyEntity, BaseViewHolder> {


    public NotificationAdapter(@Nullable List<NotifyEntity> data) {
        super(R.layout.item_notification, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, NotifyEntity item) {

        baseViewHolder.setText(R.id.item_notify_title_tv,item.getTitle());
        baseViewHolder.setText(R.id.item_notify_content_tv,item.getContent());

    }
}
