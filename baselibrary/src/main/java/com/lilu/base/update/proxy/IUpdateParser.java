package com.lilu.base.update.proxy;

import com.lilu.base.update.entity.NewVersionEntity;
import com.lilu.base.update.entity.UpdateEntity;

/**
 * Description:
 * 版本更新解析器，解析服务器返回的数据结果
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public interface IUpdateParser {


    /**P
     * 将请求的json结果解析为版本更新信息实体
     */
    UpdateEntity entityParse(NewVersionEntity version);


}
