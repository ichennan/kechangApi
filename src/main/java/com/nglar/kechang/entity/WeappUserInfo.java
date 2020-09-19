package com.nglar.kechang.entity;


import cn.hutool.json.JSONObject;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_weapp_user_info")
public class WeappUserInfo extends SimpleEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer vipId;
    String nickName;
    String gender;
    String language;
    String city;
    String province;
    String country;
    String avatarUrl;

    @Override
    public JSONObject toJson(){
        JSONObject toJson = new JSONObject(this);
        return toJson;
    }

    @Override
    public String toString(){
        return toJson().toString();
    }
}
