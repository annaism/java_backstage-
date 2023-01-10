package com.joker.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/06/0:26
 * @Description:
 */

@Component
@Data
@ConfigurationProperties(prefix = "spring.jsch")
public class JschProperties {
    /**
     * 服务器IP
     */
    private String host;
    /**
     * 服务器端口
     */
    private int port;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 文件的访问路径
     */
    private String url;
    /**
     * 存储路径
     */
    private String path;
    /**
     * 严格的主机密钥检查，默认不开启
     */
    private String strictHostKeyChecking = "no";
}
