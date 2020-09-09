package com.nglar.kechang.simple;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chennan
 * @date 2020/09/09 09:09
 */

public class SimpleController {
	@Value("${spring.application.version}")
	String applicationVersion;
	@Value("${spring.profiles.active}")
	String activeProfile;

	protected Map<String, Object> parameters;
	public SimpleController() {
	}

	@PostConstruct
	public void init() {
		this.parameters = new HashMap();
		this.parameters.put("msg", "test");
		this.parameters.put("version", applicationVersion);
		this.parameters.put("profile", activeProfile);
		this.parameters.put("pageTitle", "5Amazon ERP");
		this.parameters.put("pageName", "5Amazon");
//		this.parameters.put("title", "TestTitle");
	}

//	public final static String getUsername(){
//		String username = "";
//		try{
//			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			username = userDetails.getUsername();
//			return username;
//		}catch (Exception e){
//			throw new SimpleCommonException("用户未登录");
//		}finally {
//			System.out.println("username: " + username);
//		}
//	}
}
