package com.nglar.kechang.dto;

import lombok.Data;

@Data
public class Login3rdDTO {
    String sessionKey;
    String encryptedData;
    String iv;
    //
    String openId;
    String unionId;
    //
    WeappUserInfoDTO weappUserInfo;
}
