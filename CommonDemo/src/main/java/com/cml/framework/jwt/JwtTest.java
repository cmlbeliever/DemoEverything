package com.cml.framework.jwt;

import com.cml.framework.distribute.snowflake.SnowflakeIdWorker;
import org.jose4j.lang.JoseException;

public class JwtTest {
    public static void main(String[] args) throws JoseException, InterruptedException {
        String key = JwtUtil.randomKey(32);
        System.out.println(key);

        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(31, 13);
        String token = idWorker.nextId() + "";

        String jwtToken = JwtUtil.encrypt(key, token);
        System.out.println(jwtToken);
        Thread.sleep(3000);
        String decrypt = JwtUtil.decrypt(key, jwtToken);
        System.out.println(decrypt);
    }
}
