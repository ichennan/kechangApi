package com.nglar.kechang.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;

public class WechatDecryptUtil {
    public static JSONObject getSessionJson(String appId, String secret, String code){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + appId
                + "&secret="
                + secret
                + "&js_code="
                + code
                + "&grant_type=authorization_code";
        String session = HttpUtil.get(url);
        return new JSONObject(session);
    }

    public static String getSessionKey(String appId, String secret, String code){
        JSONObject sessionJson = getSessionJson(appId, secret, code);
        return sessionJson.getStr("session_key");
    }

    public static JSONObject decrypt(String sessionKey, String iv, String encrypdata){
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(Base64.decodeBase64(iv));
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(Base64.decodeBase64(sessionKey), "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            String result = new String(cipher.doFinal(Base64.decodeBase64(encrypdata)), StandardCharsets.UTF_8);
            return new JSONObject(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new JSONObject();
    }
}
