package com.nglar.kechang.api;

import cn.hutool.json.JSONObject;
import com.nglar.kechang.config.JwtConfig;
import com.nglar.kechang.simple.SimpleController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chennan
 * @date 2020/09/09 09:09
 */

@RestController
@RequestMapping(value = "/private")
@Slf4j
public class PrivateController extends SimpleController {
	@Autowired
	JwtConfig jwtConfig;

	@RequestMapping(value = "/checkToken", method= RequestMethod.POST)
	public String checkToken(HttpHeaders httpHeaders){
		log.info("PrivateController.checkToken()");
//		String memberId = jwtTokenUtil.getUsernameFromToken(httpHeaders.get(jwtConfig.getTokenName()));
		JSONObject rs = new JSONObject();
		rs.put("sts", "1");
		return rs.toString();
	}
}

