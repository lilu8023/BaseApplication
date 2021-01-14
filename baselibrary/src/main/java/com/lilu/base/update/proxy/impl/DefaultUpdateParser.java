package com.lilu.base.update.proxy.impl;

import com.lilu.base.update.VersionUpdate;
import com.lilu.base.update.entity.NewVersionEntity;
import com.lilu.base.update.entity.UpdateEntity;
import com.lilu.base.update.proxy.IUpdateParser;
import com.lilu.base.update.utils.UpdateUtils;
import com.lilu.base.utils.StringUtils;


/**
 * Description:
 * 默认版本更新解析器
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class DefaultUpdateParser implements IUpdateParser {


    /**
     * 进行本地版本判断[防止服务端出错，本来是不需要更新，但是服务端返回是需要更新]
     *
     * @param checkResult
     * @return
     */
    private NewVersionEntity doLocalCompare(NewVersionEntity checkResult) {
        if (checkResult.getUpdateStatus() != NewVersionEntity.NO_NEW_VERSION) { //服务端返回需要更新
            int lastVersionCode = checkResult.getVersionCode();
            if (lastVersionCode <= UpdateUtils.getVersionCode(VersionUpdate.getContext())) { //最新版本小于等于现在的版本，不需要更新
                checkResult.setRequireUpgrade(NewVersionEntity.NO_NEW_VERSION);
            }
        }
        return checkResult;
    }

    @Override
    public UpdateEntity entityParse(NewVersionEntity version){
            if (version != null && version.getCode() == 0) {
                version = doLocalCompare(version);

                UpdateEntity updateEntity = new UpdateEntity();
                if (version.getUpdateStatus() == NewVersionEntity.NO_NEW_VERSION) {
                    updateEntity.setHasUpdate(false);
                } else {
                    if (version.getUpdateStatus() == NewVersionEntity.HAVE_NEW_VERSION_FORCED_UPLOAD) {
                        updateEntity.setForce(true);
                    }
                    updateEntity.setHasUpdate(true)
                            .setUpdateContent(version.getModifyContent())
                            .setVersionCode(version.getVersionCode())
                            .setVersionName(version.getVersionName())
                            .setDownloadUrl(version.getDownloadUrl())
                            .setSize(version.getApkSize())
                            .setMd5(version.getApkMd5());
                }
                return updateEntity;
            }
        return null;
    }
}
