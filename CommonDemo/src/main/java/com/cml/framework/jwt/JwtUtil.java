package com.cml.framework.jwt;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;

import java.security.Key;
import java.util.Base64;

public class JwtUtil {
    public static String randomKey(int len) {
        byte[] bytes = ByteUtil.randomBytes(len);
        String encoded = Base64.getEncoder().encodeToString(bytes);
        return encoded;
    }

    public static String encrypt(String keyStr, String payload) throws JoseException {
        byte[] keyByte = Base64.getDecoder().decode(keyStr);
        Key key = new AesKey(keyByte);
        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setPayload(payload);
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A256KW);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
        jwe.setKey(key);
        return jwe.getCompactSerialization();
    }

    public static String decrypt(String keyStr, String jwtStr) throws JoseException {
        byte[] keyByte = Base64.getDecoder().decode(keyStr);
        Key key = new AesKey(keyByte);
        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setAlgorithmConstraints(new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.WHITELIST,
                KeyManagementAlgorithmIdentifiers.A256KW));
        jwe.setContentEncryptionAlgorithmConstraints(new AlgorithmConstraints(AlgorithmConstraints.ConstraintType.WHITELIST,
                ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256));
        jwe.setKey(key);
        jwe.setCompactSerialization(jwtStr);
        return jwe.getPayload();
    }
}
