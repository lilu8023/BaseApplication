package com.lilu.base.update.entity;

/**
 * Description:
 * 版本更新实体类
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class NewVersionEntity {
    /**
     * 无版本更新
     */
    public final static int NO_NEW_VERSION = 0; // 0:无版本更新
    /**
     * 有版本更新，不需要强制升级
     */
    public final static int HAVE_NEW_VERSION = 1; // 1:有版本更新，不需要强制升级
    /**
     * 有版本更新，需要强制升级
     */
    public final static int HAVE_NEW_VERSION_FORCED_UPLOAD = 2; // 2:有版本更新，需要强制升级
    /**
     * 请求返回码
     */
    private int Code;
    /**
     * 请求信息
     */
    private String Msg;

    /**
     * 更新的状态
     */
    private int UpdateStatus;
    /**
     * 最新版本号[根据版本号来判别是否需要升级]
     */
    private int VersionCode;
    /**
     * 最新APP版本的名称[用于展示的版本名]
     */
    private String VersionName;
    /**
     * APP更新时间
     */
    private String UploadTime;
    /**
     * APP变更的内容
     */
    private String ModifyContent;
    /**
     * 下载地址
     */
    private String DownloadUrl;
    /**
     * Apk MD5值
     */
    private String ApkMd5;
    /**
     * Apk大小【单位：KB】
     */
    private long ApkSize;

    public int getCode() {
        return Code;
    }

    public NewVersionEntity setCode(int code) {
        Code = code;
        return this;
    }

    public String getMsg() {
        return Msg;
    }

    public NewVersionEntity setMsg(String msg) {
        Msg = msg;
        return this;
    }

    public int getUpdateStatus() {
        return UpdateStatus;
    }

    public NewVersionEntity setRequireUpgrade(int updateStatus) {
        UpdateStatus = updateStatus;
        return this;
    }

    public String getUploadTime() {
        return UploadTime;
    }

    public NewVersionEntity setUploadTime(String uploadTime) {
        UploadTime = uploadTime;
        return this;
    }

    public int getVersionCode() {
        return VersionCode;
    }

    public NewVersionEntity setVersionCode(int versionCode) {
        VersionCode = versionCode;
        return this;
    }

    public String getVersionName() {
        return VersionName;
    }

    public NewVersionEntity setVersionName(String versionName) {
        VersionName = versionName;
        return this;
    }

    public String getModifyContent() {
        return ModifyContent;
    }

    public NewVersionEntity setModifyContent(String modifyContent) {
        ModifyContent = modifyContent;
        return this;
    }

    public String getDownloadUrl() {
        return DownloadUrl;
    }

    public NewVersionEntity setDownloadUrl(String downloadUrl) {
        DownloadUrl = downloadUrl;
        return this;
    }

    public String getApkMd5() {
        return ApkMd5;
    }

    public NewVersionEntity setApkMd5(String apkMd5) {
        ApkMd5 = apkMd5;
        return this;
    }

    public long getApkSize() {
        return ApkSize;
    }

    public NewVersionEntity setApkSize(long apkSize) {
        ApkSize = apkSize;
        return this;
    }

    @Override
    public String toString() {
        return "CheckVersionResult{" +
                "Code=" + Code +
                ", Msg='" + Msg + '\'' +
                ", UpdateStatus=" + UpdateStatus +
                ", VersionCode=" + VersionCode +
                ", VersionName='" + VersionName + '\'' +
                ", UploadTime='" + UploadTime + '\'' +
                ", ModifyContent='" + ModifyContent + '\'' +
                ", DownloadUrl='" + DownloadUrl + '\'' +
                ", ApkMd5='" + ApkMd5 + '\'' +
                ", ApkSize=" + ApkSize +
                '}';
    }
}
