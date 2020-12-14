package com.lilu.base.utils.notification;

import android.net.Uri;

/**
 * Description:
 *
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class Channel {

    private String channelId;
    CharSequence name;
    int importance;
    String description;
    int lockScreenVisibility;
    long[] vibrationPattern;
    Uri sound;


    public Channel(String channelId, CharSequence name, int importance, String description, int lockScreenVisibility, long[] vibrationPattern, Uri sound) {
        this.channelId = channelId;
        this.name = name;
        this.importance = importance;
        this.description = description;
        this.lockScreenVisibility = lockScreenVisibility;
        this.vibrationPattern = vibrationPattern;
        this.sound = sound;
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
}
