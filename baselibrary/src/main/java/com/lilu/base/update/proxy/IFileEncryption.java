package com.lilu.base.update.proxy;

import java.io.File;

/**
 * Description:
 * 文件加密器，用于文件有效性校验
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public interface IFileEncryption {

    /**
     * 加密文件
     * @param file
     * @return
     */
    String encryptFile(File file);

    /**
     * 检验文件是否有效（加密是否一致）
     *
     * @param encrypt 加密值
     * @param file    需要校验的文件
     * @return 文件是否有效
     */
    boolean isFileValid(String encrypt, File file);
}
