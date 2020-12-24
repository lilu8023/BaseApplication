package com.lilu.application.function.notify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lilu.application.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Description:
 *
 * @author lilu on 2020/12/22
 * No one knows this better than me
 */
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private Context mContext;
    private List<String> mList;

    public NotificationAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_notification,parent,false);
        return new NotificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {

        holder.item_notify_bt.setText(mList.get(position));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder{

        AppCompatButton item_notify_bt;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            item_notify_bt = itemView.findViewById(R.id.item_notify_bt);
        }
    }
}
