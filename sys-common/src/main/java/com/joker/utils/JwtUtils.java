package com.joker.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.joker.vo.UserVo;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/03/16:49
 * @Description: JwtUtils的工具类
 */
@Slf4j
public class JwtUtils {

    //指定一个token过期时间（毫秒）
    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;  //7天

    /**
     * 生成token
     */
    //注意这里的sercet不是密码，而是进行三件套（salt+MD5+1024Hash）处理密码后得到的凭证
    public static String getJwtToken(UserVo user, String secret) {
        log.info("{{}}",secret);
//        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);    //使用密钥进行哈希
        // 附带user信息的token
        return JWT.create()
                .withClaim("user", JSON.toJSONString(user))//签名算法
                .sign(algorithm);
//                .withExpiresAt(date)  //过期时间

    }

    /**
     * 校验token是否正确
     */
    public static boolean verifyToken(String token, UserVo user, String secret) {
        log.info("{{}}",secret);
        try {
            //根据密钥生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("user", JSON.toJSONString(user) )
                    .build();
            //效验TOKEN（其实也就是比较两个token是否相同）
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 在token中获取到user信息
     */
    public static UserVo getUser(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Claim claim = jwt.getClaim("user");
            return JSON.parseObject(claim.asString(),UserVo.class);
        } catch (JWTDecodeException e) {
            return null;
        }
    }
//
//    /**
//     * 判断是否过期
//     */
//    public static boolean isExpire(String token){
//        DecodedJWT jwt = JWT.decode(token);
//        return jwt.getExpiresAt().getTime() < System.currentTimeMillis() ;
//    }

}
