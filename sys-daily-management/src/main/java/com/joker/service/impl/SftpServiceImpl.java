package com.joker.service.impl;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.joker.config.JSchAutoConfiguration;
import com.joker.entity.JschProperties;
import com.joker.service.ISftpService;
import com.joker.utils.UUidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/06/0:28
 * @Description:
 */
@Service
@Slf4j
public class SftpServiceImpl implements ISftpService {

    @Resource
    JSchAutoConfiguration jSchAutoConfiguration;
    @Resource
    JschProperties jschProperties ;

    /**
     * 上传文件
     *
     * @param inputStream 输入流
     * @return 路径
     */
    @Override
    public String put(InputStream inputStream,String fileName) {

        ChannelSftp sftp = null;
        String uuid = UUidUtils.generateuuid();
        String[] split = fileName.split("\\.");
        String filename = uuid + "." + split[1];
        try {


            sftp = this.getChannelSftp();
            // 上传的图片路径
            String path = jschProperties.getPath() + "/" + filename;

            log.info("{{}}", path);
            // 上传文件
            sftp.put(inputStream, path);
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            sftp.quit();
            sftp.exit();
        }
        // 返回图片的浏览路径
        return jschProperties.getUrl() + filename;
    }



    /**
     * 删除文件
     *
     * @param fileNames 文件路径
     */
    @Override
    public boolean delete(String[] fileNames) {

        boolean key = true;
        ChannelSftp sftp = null;
        try {
            sftp = this.getChannelSftp();
            for (String filename:fileNames) {
                String res = filename.substring( filename.lastIndexOf('/') + 1, filename.length());
                log.info("{{}}",res);
                log.info("{{}}",jschProperties.getPath() + "/" + res);
                sftp.rm(jschProperties.getPath() + "/" + res);
            }
        } catch (JSchException e) {
            e.printStackTrace();
            key = false;
        } catch (SftpException e) {
            e.printStackTrace();
            key = false;
        }
        finally {
            sftp.quit();
            sftp.exit();
        }

        return key;


    }

    /**
     * 下载文件
     *
     * @param filePath 文件路径
     * @param name     文件名称
     */
    @Override
    public InputStream get(String filePath, String name) {
        InputStream inputStream = null;
        try {
            ChannelSftp sftp = this.getChannelSftp();
            inputStream = sftp.get(jschProperties.getPath() + "/" + filePath + "/" + name);
            sftp.quit();
            sftp.exit();
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    /**
     * 获取安全文件传输协议类
     *
     * @return 安全文件传输协议类
     * @throws JSchException JSch链接异常
     */
    private ChannelSftp getChannelSftp() throws JSchException {
        Session session = jSchAutoConfiguration.getSession();
        // 安全文件传输协议
        ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
        sftp.connect(100000);
        return sftp;
    }

}
