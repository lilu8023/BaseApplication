package com.lilu.base.listener;


import androidx.recyclerview.widget.RecyclerView;

/**
 * Description:
 * recyclerview单项点击事件
 * @author lilu on 2020/12/15
 * No one knows this better than me
 */
public interface RecyclerViewItemClick {

    <VH extends RecyclerView.ViewHolder> void onRecyclerViewItemClick(int position, RecyclerView.Adapter<VH> adapter);
}
