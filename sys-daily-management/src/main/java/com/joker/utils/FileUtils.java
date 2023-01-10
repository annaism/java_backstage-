package com.joker.utils;

import com.joker.exception.HeadPicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/06/10:57
 * @Description:
 */
@Slf4j
public class FileUtils {
    /**
     * 文件后缀 支持的类型
     */
    private static final String[] FILE_SUFFIX_SUPPORT = {".jpg", ".jpeg", ".png",".JPG",".JPEG",".PNG"};

    /**
     * 文件大小 10MB
     */
    private static final long FILE_SIZE = 10 * 1024 * 1024;

    /**
     * 上传文件校验大小、名字、后缀
     * @param multipartFile multipartFile
     */
    public static void uploadVerify(MultipartFile multipartFile) {
        // 校验文件是否为空
        if (multipartFile == null) {
            throw new HeadPicException(413,"请选择图片!");
        }

        // 校验文件大小
        long size = multipartFile.getSize();
        if(size > FILE_SIZE){
            throw new HeadPicException(413,"文件大小不能超过10MB!");
        }

        // 校验文件名字
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null) {
            throw new HeadPicException(413,"文件名字不能为空!");
        }

        // 校验文件后缀
        if (!originalFilename.contains(".")) {
            throw new HeadPicException(413,"文件不能没有后缀!");
        }

        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
        boolean flag = true;
        for (String s : FILE_SUFFIX_SUPPORT) {
            if (s.equals(suffix.toLowerCase(Locale.ROOT))) {
                flag = false;
                break;
            }
        }
        if(flag){
            throw new HeadPicException(413,"文件格式仅限于"+ Arrays.toString(FILE_SUFFIX_SUPPORT) +"!");
        }
    }

}
