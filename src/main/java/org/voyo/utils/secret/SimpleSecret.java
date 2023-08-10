package org.voyo.utils.secret;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class SimpleSecret {
    private final String appId;
    private final String appSecret;

    private long distanceTimeMs=1000L*30;

    public SimpleSecret(String appId,String appSecret){
        this.appSecret=appSecret;
        this.appId=appId;
    }
    public SimpleSecret(String appId,String appSecret,long distanceTimeMs){
        this.appSecret=appSecret;
        this.appId=appId;
        this.distanceTimeMs=distanceTimeMs;
    }

    //获得加密token
    public String encryptToken(SimpleSecretDto simpleSecretDto){
        StringBuilder builder=new StringBuilder();
        builder.append(simpleSecretDto.getAppid()+"_");
        builder.append(simpleSecretDto.getTimestamp()+"_");
        builder.append(simpleSecretDto.getNonStr()+"_");
        builder.append(appSecret);
        return DigestUtils.md5DigestAsHex(builder.toString().getBytes(StandardCharsets.UTF_8));
    }
    //校验token是否一致
    public boolean validToken(SimpleSecretDto simpleSecretDto,String token){
        long timestamp=simpleSecretDto.getTimestamp();
        if(timestamp+distanceTimeMs < new Date().getTime()) return false;
        return token.equals(encryptToken(simpleSecretDto));
    }
}
