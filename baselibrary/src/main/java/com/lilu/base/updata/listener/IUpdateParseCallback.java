package com.lilu.base.updata.listener;


import com.lilu.base.updata.entity.UpdateEntity;

/**
 * 异步解析的回调
 *
 * @author xuexiang
 * @since 2020-02-15 17:23
 */
public interface IUpdateParseCallback {

    /**
     * 解析结果
     *
     * @param updateEntity 版本更新信息实体
     */
    void onParseResult(UpdateEntity updateEntity);

}
