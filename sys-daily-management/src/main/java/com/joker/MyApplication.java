package com.joker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2022/12/01/22:01
 * @Description:
 */
@EnableDiscoveryClient  //nacos注册
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
