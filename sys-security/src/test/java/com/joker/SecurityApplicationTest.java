package com.joker;

import com.joker.MyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/03/04/11:22
 * @Description:
 */

@SpringBootTest(classes = MyApplication.class)
public class SecurityApplicationTest {
    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Test
    public void testBcrypt() {
        // 加密测试
        String encode = passwordEncoder.encode("1234");
        System.out.println(encode);

        // 校验测试
        boolean matches = passwordEncoder.matches("1234", "$2a$10$ZqVB18PPA3P/MR9So/i8N.1UvVb.PblNl2sbj6pQJNDCgqiZqNQUm");
        System.out.println(matches);
    }
}
