package com.joker.utils;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/05/20:49
 * @Description:
 */
public class UUidUtils {
    public static String generateuuid(){
        return UUID.randomUUID().toString().replace("-","").substring(0,15);
    }
}
