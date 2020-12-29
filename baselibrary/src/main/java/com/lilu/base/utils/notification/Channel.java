package com.lilu.base.utils.notification;

import android.net.Uri;

/**
 * Description:
 * 通知实体类
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class Channel {

    private String channelId;               //尾翼渠道ID
    private CharSequence name;              //用户可见名称
    /**
     * android 8.0                                                                          android 7.0
     * IMPORTANCE_HIGH          紧急，发出声音并作为警告通知出现                            PRIORITY_HIGH 或者 PRIORITY_MAX
     * IMPORTANCE_DEFAULT       高，发出声音                                                PRIORITY_DEFAULT
     * IMPORTANCE_LOW           中，没有声音                                                PRIORITY_LOW
     * IMPORTANCE_MIN           低，没有声音并且不会出现在状态栏中，并且通知会被折叠        PRIORITY_MIN
     */
    private int importance;                 //重要级别
    private String description;             //描述
    /**
     * VISIBILITY_PUBLIC：显示完整的通知。
     * VISIBILITY_SECRET：在锁定屏幕上不显示通知的任何信息。
     * VISIBILITY_PRIVATE：只显示基本信息，例如通知的图标和内容标题，但隐藏通知内容。
     */
    private int lockScreenVisibility;       //锁定屏幕公开范围
    private long[] vibrationPattern;        //震动模式
    private Uri sound;                      //声音

//, int lockScreenVisibility, long[] vibrationPattern, Uri sound
    public Channel(String channelId, CharSequence name, int importance, String description) {
        this.channelId = channelId;
        this.name = name;
        this.importance = importance;
        this.description = description;
//        this.lockScreenVisibility = lockScreenVisibility;
//        this.vibrationPattern = vibrationPattern;
//        this.sound = sound;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public CharSequence getName() {
        return name;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLockScreenVisibility() {
        return lockScreenVisibility;
    }

    public void setLockScreenVisibility(int lockScreenVisibility) {
        this.lockScreenVisibility = lockScreenVisibility;
    }

    public Uri getSound() {
        return sound;
    }

    public void setSound(Uri sound) {
        this.sound = sound;
    }

    public long[] getVibrationPattern() {
        return vibrationPattern;
    }

    public void setVibrationPattern(long[] vibrationPattern) {
        this.vibrationPattern = vibrationPattern;
    }


    /*private Channel(Builder builder){
        this.channelId = builder.channelId;
        this.name = builder.name;
        this.importance = builder.importance;
        this.description = builder.description;
        this.lockScreenVisibility = builder.lockScreenVisibility;
        this.vibrationPattern = builder.vibrationPattern;
        this.sound = builder.sound;
    }

    public static class Builder {

        private String channelId;               //尾翼渠道ID
        private CharSequence name;              //用户可见名称
        *//**
         * android 8.0                                                                          android 7.0
         * IMPORTANCE_HIGH          紧急，发出声音并作为警告通知出现                            PRIORITY_HIGH 或者 PRIORITY_MAX
         * IMPORTANCE_DEFAULT       高，发出声音                                                PRIORITY_DEFAULT
         * IMPORTANCE_LOW           中，没有声音                                                PRIORITY_LOW
         * IMPORTANCE_MIN           低，没有声音并且不会出现在状态栏中，并且通知会被折叠        PRIORITY_MIN
         *//*
        private int importance;                 //重要级别
        private String description;             //描述
        private int lockScreenVisibility;       //锁定屏幕公开范围
        private long[] vibrationPattern;        //震动模式
        private Uri sound;                      //声音


        public Builder() {
        }

        public Builder channelId(String channelId){
            this.channelId = channelId;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder importance(int importance){
            this.importance = importance;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder lockScreenVisibility(int lockScreenVisibility){
            this.lockScreenVisibility = lockScreenVisibility;
            return this;
        }

        public Builder vibrationPattern(long[] vibrationPattern){
            this.vibrationPattern = vibrationPattern;
            return this;
        }

        public Builder sound(Uri sound){
            this.sound = sound;
            return this;
        }

        public Channel build(){
            return new Channel(this);
        }
    }*/
}
