package com.nglar.kechang.common;

import java.util.HashMap;
import java.util.Map;

public class KechangConstant {
    public static final String THIRD_PARTY_NAME_OPENID = "OPENID";
    public static final String THIRD_PARTY_NAME_UNIONID = "UNIONID";

    public static final Map<Integer, String> VALIDATE_MAP = new HashMap<>();

    public static final Integer VALIDATE_VALID = 0;

    static {
        VALIDATE_MAP.put(VALIDATE_VALID, "Valid");
        VALIDATE_MAP.put(1, "Invalid Bill Number");
        VALIDATE_MAP.put(2, "Amount Less Than Minimum");
        VALIDATE_MAP.put(3, "Amount Exceeds Maximum");
        VALIDATE_MAP.put(4, "Invalid Bill Type");
        VALIDATE_MAP.put(5, "Invalid Currency");
    }

}
