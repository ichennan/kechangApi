package com.nglar.kechang.entity;


import cn.hutool.json.JSONObject;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_vip_info")
public class VipInfo extends SimpleEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String username;
    String nickname;
    String phone;
    String status;

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
