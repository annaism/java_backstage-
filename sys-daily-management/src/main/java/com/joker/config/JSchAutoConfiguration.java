package com.joker.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.joker.entity.JschProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/06/0:27
 * @Description:
 */

@Configuration
@Data
@Slf4j
public class JSchAutoConfiguration implements InitializingBean, DisposableBean {
    @Resource
    private JschProperties jschProperties;

    private Session session;

    /**
     * 初始化配置链接
     *
     * @throws Exception 异常
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        JSch jSch = new JSch();
        session = jSch.getSession(jschProperties.getUsername(),jschProperties.getHost(),jschProperties.getPort());
        if(session==null){
            throw new Exception("session is null");
        }
        session.setPassword(jschProperties.getPassword());
        session.setConfig("StrictHostKeyChecking",jschProperties.getStrictHostKeyChecking());
        session.connect();
        // 打印SSH服务器版本
        log.info("Jsch_AutoConfiguration:::{}",session.getServerVersion());
    }

    /**
     * 销毁链接
     *
     * @throws Exception 销毁链接异常
     */
    @Override
    public void destroy() throws Exception {
        if (session!=null){
            session.disconnect();
        }
        log.info("Jsch_AutoConfiguration::: destroy connection");
    }

}
