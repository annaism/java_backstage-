package com.joker;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/10/11:13
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        String new_salt = uuid.substring(uuid.lastIndexOf("-") + 1, uuid.length() - 1);

        Md5Hash newpwd = new Md5Hash("0515", new_salt, 1024);

        System.out.println(new_salt);
        System.out.println(newpwd);
    }

}
