package com.nglar.kechang.service.impl;

import cn.hutool.json.JSONObject;
import com.nglar.kechang.common.KechangConstant;
import com.nglar.kechang.dto.Login3rdDTO;
import com.nglar.kechang.entity.VipInfo;
import com.nglar.kechang.entity.VipThirdParty;
import com.nglar.kechang.entity.WeappUserInfo;
import com.nglar.kechang.repository.VipInfoRepository;
import com.nglar.kechang.repository.VipThirdPartyRepository;
import com.nglar.kechang.repository.WeappUserInfoRepository;
import com.nglar.kechang.service.VipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VipServiceImpl implements VipService {
    @Autowired
    VipInfoRepository vipInfoRepository;
    @Autowired
    VipThirdPartyRepository vipThirdPartyRepository;
    @Autowired
    WeappUserInfoRepository weappUserInfoRepository;

    @Override
    public VipInfo getByOpenId(String openId) {
        VipThirdParty vipThirdParty = vipThirdPartyRepository.getByThirdPartyNameAndThirdPartyId("OPENID", openId);
        if(vipThirdParty == null){
            return null;
        }
        Integer vipId = vipThirdParty.getVipId();
        VipInfo vipInfo = vipInfoRepository.getOne(vipId);
        return vipInfo;
    }

    @Override
    public VipInfo getByUnionId(String unionId) {
        VipThirdParty vipThirdParty = vipThirdPartyRepository.getByThirdPartyNameAndThirdPartyId("UNIONID", unionId);
        if(vipThirdParty == null){
            return null;
        }
        Integer vipId = vipThirdParty.getVipId();
        VipInfo vipInfo = vipInfoRepository.getOne(vipId);
        return vipInfo;
    }

    @Override
    public VipInfo getByPhone(String phone, Login3rdDTO login3rdDTO) {
        VipInfo vipInfo = vipInfoRepository.getByPhone(phone);
        if(vipInfo == null){
            vipInfo = new VipInfo();
            vipInfo.setPhone(phone);
            vipInfo.setUsername(phone);
            vipInfo.setNickname(phone);
            vipInfoRepository.save(vipInfo);
            Integer vipId = vipInfo.getId();
            log.warn("vipId: " + vipId);
            //
            VipThirdParty vipThirdPartyOpenId = new VipThirdParty();
            vipThirdPartyOpenId.setThirdPartyName(KechangConstant.THIRD_PARTY_NAME_OPENID);
            vipThirdPartyOpenId.setThirdPartyId(login3rdDTO.getOpenId());
            vipThirdPartyOpenId.setVipId(vipId);
            vipThirdPartyRepository.save(vipThirdPartyOpenId);
            //
            VipThirdParty vipThirdPartyUnionId = new VipThirdParty();
            vipThirdPartyUnionId.setThirdPartyName(KechangConstant.THIRD_PARTY_NAME_UNIONID);
            vipThirdPartyUnionId.setThirdPartyId(login3rdDTO.getUnionId());
            vipThirdPartyUnionId.setVipId(vipId);
            vipThirdPartyRepository.save(vipThirdPartyUnionId);
            //
            WeappUserInfo weappUserInfo = new WeappUserInfo();
            if(weappUserInfo != null){
                BeanUtils.copyProperties(login3rdDTO.getWeappUserInfo(), weappUserInfo);
            }
            weappUserInfo.setVipId(vipId);
            weappUserInfoRepository.save(weappUserInfo);
            //
        }
        return vipInfo;
    }

    @Override
    public JSONObject getJsonById(Integer id) {
        JSONObject rs = new JSONObject();
        VipInfo vipInfo = vipInfoRepository.getOne(id);
        if(vipInfo == null){
            return rs;
        }
        rs = vipInfo.toJson();
        List<VipThirdParty> vipThirdPartyList = vipThirdPartyRepository.findByVipId(id);
        for(VipThirdParty vipThirdParty : vipThirdPartyList){
            if(KechangConstant.THIRD_PARTY_NAME_OPENID.equals(vipThirdParty.getThirdPartyName())){
                rs.putOpt("openId", vipThirdParty.getThirdPartyId());
            }
            if(KechangConstant.THIRD_PARTY_NAME_UNIONID.equals(vipThirdParty.getThirdPartyName())){
                rs.putOpt("unionId", vipThirdParty.getThirdPartyId());
            }
        }
        WeappUserInfo weappUserInfo = weappUserInfoRepository.getByVipId(id);
        if(weappUserInfo != null){
            rs.putOpt("weappUserInfo", weappUserInfo.toJson());
        }
        return rs;
    }
}
