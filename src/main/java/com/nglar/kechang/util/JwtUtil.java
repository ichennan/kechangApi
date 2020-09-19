package com.nglar.kechang.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtUtil {
    public static String sign(String username, String secret, long expireSeconds) {
        Date date = new Date(System.currentTimeMillis() + expireSeconds * 1000);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                .withClaim("as", "a")
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(token);
            System.out.println(jwt.getHeader());
            System.out.println(jwt.getPayload());
            System.out.println(jwt.getSignature());
            System.out.println(jwt.getToken());
            log.info("username: " + jwt.getClaim("username").asString());
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }




    public static void main(String[] args) {
        String zhangsan = JwtUtil.sign("zhangsan", "123", 300);
        JwtUtil.verify(zhangsan, "zhangsan", "123");
    }
}