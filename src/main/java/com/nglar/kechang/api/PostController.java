package com.nglar.kechang.api;
import cn.hutool.json.JSONObject;
import com.nglar.kechang.simple.SimpleController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author chennan
 * @date 2020/09/09 09:09
 */

@RestController
@RequestMapping(value = "/post")
@Slf4j
public class PostController extends SimpleController {

	@RequestMapping(value = "/findAll", method= RequestMethod.POST)
	public String findAll(){
		log.info("PostController.findAll");
		JSONObject rs = new JSONObject();
		rs.putOpt("testKey", "testValue");
		return rs.toString();
	}
}

