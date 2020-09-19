package com.nglar.kechang.dto;

import lombok.Data;

@Data
public class Login2ndDTO {
    String sessionKey;
    String encryptedData;
    String iv;
}
