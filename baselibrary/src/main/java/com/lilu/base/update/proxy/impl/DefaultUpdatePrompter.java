/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lilu.base.update.proxy.impl;

import android.content.Context;

import com.lilu.base.update.entity.PromptEntity;
import com.lilu.base.update.entity.UpdateEntity;
import com.lilu.base.update.proxy.IUpdatePrompter;
import com.lilu.base.update.proxy.IUpdateProxy;
import com.lilu.base.utils.logger.Logger;
import com.lilu.base.update.UpdateDialogActivity;
import com.lilu.base.update.UpdateDialogFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;


/**
 * 默认的更新提示器
 *
 * @author xuexiang
 * @since 2018/7/2 下午4:05
 */
public class DefaultUpdatePrompter implements IUpdatePrompter {

    /**
     * 显示版本更新提示
     *
     * @param updateEntity 更新信息
     * @param updateProxy  更新代理
     * @param promptEntity 提示界面参数
     */
    @Override
    public void showPrompt(@NonNull UpdateEntity updateEntity, @NonNull IUpdateProxy updateProxy, @NonNull PromptEntity promptEntity) {
        Context context = updateProxy.getContext();
        if (context == null) {
            Logger.e("showPrompt failed, context is null!");
            return;
        }
        if (context instanceof FragmentActivity) {
            UpdateDialogFragment.show(((FragmentActivity) context).getSupportFragmentManager(), updateEntity, new DefaultPrompterProxyImpl(updateProxy), promptEntity);
        } else {
            UpdateDialogActivity.show(context, updateEntity, new DefaultPrompterProxyImpl(updateProxy), promptEntity);
        }
    }
}
