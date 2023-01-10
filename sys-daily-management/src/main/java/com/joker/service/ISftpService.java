package com.joker.service;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/06/0:29
 * @Description:
 */
public interface ISftpService {
    public String put(InputStream inputStream, String name);
    public boolean delete(String[] fileName);
    public InputStream get(String filePath, String name);
}
