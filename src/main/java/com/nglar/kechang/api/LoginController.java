package com.nglar.kechang.api;

import cn.hutool.json.JSONObject;
import com.nglar.kechang.config.WechatConfig;
import com.nglar.kechang.dto.Login2ndDTO;
import com.nglar.kechang.dto.Login3rdDTO;
import com.nglar.kechang.dto.Login1stDTO;
import com.nglar.kechang.entity.VipInfo;
import com.nglar.kechang.service.VipService;
import com.nglar.kechang.simple.SimpleController;
import com.nglar.kechang.util.WechatDecryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chennan
 * @date 2020/09/09 09:09
 */

@RestController
@RequestMapping(value = "/login")
@Slf4j
public class LoginController extends SimpleController {
	@Autowired
	VipService vipService;
	@Autowired
	WechatConfig wechatConfig;

	@RequestMapping(value = "/fromWeapp1st", method= RequestMethod.POST)
	public String fromWeapp1st(@RequestBody Login1stDTO login1stDTO){
		log.info("LoginController.fromWeapp1st: " + new JSONObject(login1stDTO).toString());
		JSONObject rs = new JSONObject();
		rs.putOpt("isOk", true);
		String code = login1stDTO.getCode();
		JSONObject json = WechatDecryptUtil.getSessionJson(wechatConfig.getWeappAppId(), wechatConfig.getWeappAppSecret(), code);
		log.warn(json.toString());
		String sessionKey = json.getStr("session_key");
		String openId = json.getStr("openid");
		rs.putOpt("sessionKey", sessionKey);
		rs.putOpt("openId", openId);
		//
		VipInfo vipInfo = vipService.getByOpenId(openId);
		if(vipInfo == null){
			rs.putOpt("isVip", false);
			return rs.toString();
		}
		Integer vipId = vipInfo.getId();
		rs.putOpt("isVip", true);
		rs.putOpt("vipId", vipId);
		rs.putAll(vipService.getJsonById(vipId));
		return rs.toString();
	}

	@RequestMapping(value = "/fromWeapp2nd", method= RequestMethod.POST)
	public String fromWeapp2nd(@RequestBody Login2ndDTO login2ndDTO){
		log.info("LoginController.fromWeapp2nd: " + new JSONObject(login2ndDTO).toString());
		JSONObject rs = new JSONObject();
		rs.putOpt("isOk", true);
		String sessionKey = login2ndDTO.getSessionKey();
		JSONObject json = WechatDecryptUtil.decrypt(sessionKey, login2ndDTO.getIv(), login2ndDTO.getEncryptedData());
		log.warn(json.toString());
		String unionId = json.getStr("openId");
		rs.putOpt("unionId", unionId);
		rs.putOpt("sessionKey", sessionKey);
		//
		VipInfo vipInfo = vipService.getByUnionId(unionId);
		if(vipInfo == null){
			rs.putOpt("isVip", false);
			return rs.toString();
		}
		Integer vipId = vipInfo.getId();
		rs.putOpt("isVip", true);
		rs.putOpt("vipId", vipId);
		rs.putAll(vipService.getJsonById(vipId));
		return rs.toString();
	}

	@RequestMapping(value = "/fromWeapp3rd", method= RequestMethod.POST)
	public String fromWeapp3rd(@RequestBody Login3rdDTO login3rdDTO){
		log.info("LoginController.fromWeapp3rd: " + new JSONObject(login3rdDTO).toString());
		JSONObject rs = new JSONObject();
		rs.putOpt("isOk", true);
		String sessionKey = login3rdDTO.getSessionKey();
		JSONObject json = WechatDecryptUtil.decrypt(login3rdDTO.getSessionKey(), login3rdDTO.getIv(), login3rdDTO.getEncryptedData());
		log.warn(json.toString());
		rs.putOpt("sessionKey", sessionKey);
		String phone = json.getStr("phoneNumber");
		//
		VipInfo vipInfo = vipService.getByPhone(phone, login3rdDTO);
		Integer vipId = vipInfo.getId();
		rs.putOpt("isVip", true);
		rs.putOpt("vipId", vipId);
		rs.putAll(vipService.getJsonById(vipId));
		return rs.toString();
	}
}

