package com.nglar.kechang.service;

import cn.hutool.json.JSONObject;
import com.nglar.kechang.dto.Login3rdDTO;
import com.nglar.kechang.entity.VipInfo;

public interface VipService {
    VipInfo getByOpenId(String openId);
    VipInfo getByUnionId(String unionId);
    VipInfo getByPhone(String phone, Login3rdDTO login3rdDTO);
    JSONObject getJsonById(Integer id);
}
