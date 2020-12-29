package com.lilu.base.utils;

import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

import androidx.annotation.RequiresApi;

/**
 * Description:
 *
 * @author lilu on 2020/12/24
 * No one knows this better than me
 */
public class PathUtils {

    public static final String EXT_STORAGE_PATH = getExtStoragePath();

    public static final String EXT_STORAGE_DIR = EXT_STORAGE_PATH + File.separator;

    public static final String APP_EXT_STORAGE_PATH = EXT_STORAGE_DIR + "Android";

    public static final String EXT_DOWNLOADS_PATH = PathUtils.getExtDownloadsPath();

    public static final String EXT_PICTURES_PATH = PathUtils.getExtPicturesPath();

    public static final String EXT_DCIM_PATH = PathUtils.getExtDCIMPath();
    
    private PathUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取 Android 系统根目录
     * <pre>path: /system</pre>
     *
     * @return 系统根目录
     */
    public static String getRootPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * 获取 data 目录
     * <pre>path: /data</pre>
     *
     * @return data 目录
     */
    public static String getDataPath() {
        return Environment.getDataDirectory().getAbsolutePath();
    }

    /**
     * 获取缓存目录
     * <pre>path: data/cache</pre>
     *
     * @return 缓存目录
     */
    public static String getIntDownloadCachePath() {
        return Environment.getDownloadCacheDirectory().getAbsolutePath();
    }

    //===============================内置私有存储空间===========================================//

    /**
     * 获取此应用的缓存目录
     * <pre>path: /data/data/package/cache</pre>
     *
     * @return 此应用的缓存目录
     */
    public static String getAppIntCachePath() {
        return AppUtils.getContext().getCacheDir().getAbsolutePath();
    }

    /**
     * 获取此应用的文件目录
     * <pre>path: /data/data/package/files</pre>
     *
     * @return 此应用的文件目录
     */
    public static String getAppIntFilesPath() {
        return AppUtils.getContext().getFilesDir().getAbsolutePath();
    }

    /**
     * 获取此应用的数据库文件目录
     * <pre>path: /data/data/package/databases/name</pre>
     *
     * @param name 数据库文件名
     * @return 数据库文件目录
     */
    public static String getAppIntDbPath(String name) {
        return AppUtils.getContext().getDatabasePath(name).getAbsolutePath();
    }

    //===============================外置公共存储空间，这部分需要获取读取权限，并且在Android10以后文件读取都需要使用ContentResolver进行操作===========================================//

    /**
     * 是否是公有目录
     *
     * @return 是否是公有目录
     */
    public static boolean isPublicPath(File file) {
        if (file == null) {
            return false;
        }
        try {
            return isPublicPath(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 是否是公有目录
     *
     * @return 是否是公有目录
     */
    public static boolean isPublicPath(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return false;
        }
        return filePath.startsWith(EXT_STORAGE_PATH) && !filePath.startsWith(APP_EXT_STORAGE_PATH);
    }

    /**
     * 获取 Android 外置储存的根目录
     * <pre>path: /storage/emulated/0</pre>
     *
     * @return 外置储存根目录
     */
    public static String getExtStoragePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获取闹钟铃声目录
     * <pre>path: /storage/emulated/0/Alarms</pre>
     *
     * @return 闹钟铃声目录
     */
    public static String getExtAlarmsPath() {
        return Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS)
                .getAbsolutePath();
    }

    /**
     * 获取相机拍摄的照片和视频的目录
     * <pre>path: /storage/emulated/0/DCIM</pre>
     *
     * @return 照片和视频目录
     */
    public static String getExtDCIMPath() {
        return Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                .getAbsolutePath();
    }

    /**
     * 获取文档目录
     * <pre>path: /storage/emulated/0/Documents</pre>
     *
     * @return 文档目录
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getExtDocumentsPath() {
        return Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                .getAbsolutePath();
    }

    /**
     * 获取下载目录
     * <pre>path: /storage/emulated/0/Download</pre>
     *
     * @return 下载目录
     */
    public static String getExtDownloadsPath() {
        return Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .getAbsolutePath();
    }

    /**
     * 获取视频目录
     * <pre>path: /storage/emulated/0/Movies</pre>
     *
     * @return 视频目录
     */
    public static String getExtMoviesPath() {
        return Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)
                .getAbsolutePath();
    }

    /**
     * 获取音乐目录
     * <pre>path: /storage/emulated/0/Music</pre>
     *
     * @return 音乐目录
     */
    public static String getExtMusicPath() {
        return Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
                .getAbsolutePath();
    }

    /**
     * 获取提示音目录
     * <pre>path: /storage/emulated/0/Notifications</pre>
     *
     * @return 提示音目录
     */
    public static String getExtNotificationsPath() {
        return Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS)
                .getAbsolutePath();
    }

    /**
     * 获取图片目录
     * <pre>path: /storage/emulated/0/Pictures</pre>
     *
     * @return 图片目录
     */
    public static String getExtPicturesPath() {
        return Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .getAbsolutePath();
    }

    /**
     * 获取 Podcasts 目录
     * <pre>path: /storage/emulated/0/Podcasts</pre>
     *
     * @return Podcasts 目录
     */
    public static String getExtPodcastsPath() {
        return Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS)
                .getAbsolutePath();
    }

    /**
     * 获取铃声目录
     * <pre>path: /storage/emulated/0/Ringtones</pre>
     *
     * @return 下载目录
     */
    public static String getExtRingtonesPath() {
        return Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES)
                .getAbsolutePath();
    }

    //===============================外置内部存储空间，这部分不需要获取读取权限===========================================//

    /**
     * 获取此应用在外置储存中的缓存目录
     * <pre>path: /storage/emulated/0/Android/data/package/cache</pre>
     *
     * @return 此应用在外置储存中的缓存目录
     */
    public static String getAppExtCachePath() {
        return getFilePath(AppUtils.getContext().getExternalCacheDir());
    }

    /**
     * 获取此应用在外置储存中的文件目录
     * <pre>path: /storage/emulated/0/Android/data/package/files</pre>
     *
     * @return 此应用在外置储存中的文件目录
     */
    public static String getAppExtFilePath() {
        return getFilePath(AppUtils.getContext().getExternalFilesDir(null));
    }

    /**
     * 获取此应用在外置储存中的闹钟铃声目录
     * <pre>path: /storage/emulated/0/Android/data/package/files/Alarms</pre>
     *
     * @return 此应用在外置储存中的闹钟铃声目录
     */
    public static String getAppExtAlarmsPath() {
        return getFilePath(AppUtils.getContext().getExternalFilesDir(Environment.DIRECTORY_ALARMS));
    }

    /**
     * 获取此应用在外置储存中的相机目录
     * <pre>path: /storage/emulated/0/Android/data/package/files/DCIM</pre>
     *
     * @return 此应用在外置储存中的相机目录
     */
    public static String getAppExtDCIMPath() {
        return getFilePath(AppUtils.getContext().getExternalFilesDir(Environment.DIRECTORY_DCIM));
    }

    /**
     * 获取此应用在外置储存中的文档目录
     * <pre>path: /storage/emulated/0/Android/data/package/files/Documents</pre>
     *
     * @return 此应用在外置储存中的文档目录
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getAppExtDocumentsPath() {
        return getFilePath(AppUtils.getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS));
    }

    /**
     * 获取此应用在外置储存中的下载目录
     * <pre>path: /storage/emulated/0/Android/data/package/files/Download</pre>
     *
     * @return 此应用在外置储存中的下载目录
     */
    public static String getAppExtDownloadPath() {
        return getFilePath(AppUtils.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
    }

    /**
     * 获取此应用在外置储存中的视频目录
     * <pre>path: /storage/emulated/0/Android/data/package/files/Movies</pre>
     *
     * @return 此应用在外置储存中的视频目录
     */
    public static String getAppExtMoviesPath() {
        return getFilePath(AppUtils.getContext().getExternalFilesDir(Environment.DIRECTORY_MOVIES));
    }

    /**
     * 获取此应用在外置储存中的音乐目录
     * <pre>path: /storage/emulated/0/Android/data/package/files/Music</pre>
     *
     * @return 此应用在外置储存中的音乐目录
     */
    public static String getAppExtMusicPath() {
        return getFilePath(AppUtils.getContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC));
    }

    /**
     * 获取此应用在外置储存中的提示音目录
     * <pre>path: /storage/emulated/0/Android/data/package/files/Notifications</pre>
     *
     * @return 此应用在外置储存中的提示音目录
     */
    public static String getAppExtNotificationsPath() {
        return getFilePath(AppUtils.getContext().getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS));
    }

    /**
     * 获取此应用在外置储存中的图片目录
     * <pre>path: /storage/emulated/0/Android/data/package/files/Pictures</pre>
     *
     * @return 此应用在外置储存中的图片目录
     */
    public static String getAppExtPicturesPath() {
        return getFilePath(AppUtils.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES));
    }

    /**
     * 获取此应用在外置储存中的 Podcasts 目录
     * <pre>path: /storage/emulated/0/Android/data/package/files/Podcasts</pre>
     *
     * @return 此应用在外置储存中的 Podcasts 目录
     */
    public static String getAppExtPodcastsPath() {
        return getFilePath(AppUtils.getContext().getExternalFilesDir(Environment.DIRECTORY_PODCASTS));
    }

    /**
     * 获取此应用在外置储存中的铃声目录
     * <pre>path: /storage/emulated/0/Android/data/package/files/Ringtones</pre>
     *
     * @return 此应用在外置储存中的铃声目录
     */
    public static String getAppExtRingtonesPath() {
        return getFilePath(AppUtils.getContext().getExternalFilesDir(Environment.DIRECTORY_RINGTONES));
    }

    private static String getFilePath(File file) {
        return file != null ? file.getAbsolutePath() : "";
    }

    /**
     * 获取此应用的 Obb 目录
     * <pre>path: /storage/emulated/0/Android/obb/package</pre>
     * <pre>一般用来存放游戏数据包</pre>
     *
     * @return 此应用的 Obb 目录
     */
    public static String getObbPath() {
        return AppUtils.getContext().getObbDir().getAbsolutePath();
    }


}
