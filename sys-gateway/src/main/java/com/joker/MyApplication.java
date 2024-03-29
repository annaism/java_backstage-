package com.joker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * 开启服务注册发现
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MyApplication {


    public static void main(String[] args) {
        try{
            SpringApplication.run(MyApplication.class, args);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
