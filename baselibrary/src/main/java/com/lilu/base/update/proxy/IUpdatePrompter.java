package com.lilu.base.update.proxy;

import com.lilu.base.update.entity.PromptEntity;
import com.lilu.base.update.entity.UpdateEntity;

import androidx.annotation.NonNull;


/**
 * Description:
 * 版本更新提示器，展现最新版本信息
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public interface IUpdatePrompter {

    /**
     * 显示版本更新提示
     * 处理了弹窗形式，activity or dialog fragment
     * @param updateEntity 更新信息
     * @param updateProxy  更新代理
     * @param promptEntity 提示界面参数
     */
    void showPrompt(@NonNull UpdateEntity updateEntity, @NonNull IUpdateProxy updateProxy, @NonNull PromptEntity promptEntity);
}
