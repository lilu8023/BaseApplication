package com.lilu.application.function.main.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lilu.application.R;
import com.lilu.application.function.main.entity.MainMenuEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


/**
 * Description:
 *
 * @author lilu on 2020/12/23
 * No one knows this better than me
 */
public class MainMenuAdapter extends BaseQuickAdapter<MainMenuEntity,BaseViewHolder> {

    public MainMenuAdapter(@Nullable List<MainMenuEntity> data) {
        super(R.layout.item_main_menu, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MainMenuEntity mainMenuEntity) {

        baseViewHolder.setText(R.id.item_menu_tv,mainMenuEntity.getName());

    }
}
