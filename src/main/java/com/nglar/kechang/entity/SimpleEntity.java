package com.nglar.kechang.entity;


import cn.hutool.json.JSONObject;
import lombok.Data;

import java.util.Date;

@Data
public class SimpleEntity {
    Integer id;
    Date createTime;
    Date updateTime;
    String createUser;
    String updateUser;
    String remark;

    public JSONObject toJson(){
        JSONObject toJson = new JSONObject(this);
        return toJson;
    }


}
