package com.nglar.kechang.entity;


import cn.hutool.json.JSONObject;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_vip_third_party")
public class VipThirdParty extends SimpleEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer vipId;
    String phone;
    String status;
    String thirdPartyName;
    String thirdPartyId;

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
