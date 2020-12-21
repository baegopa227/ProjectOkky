package com.okky.user;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.okky.common.BaseController;
import com.okky.exception.Custom400Exception;
import com.okky.exception.DataException;
import com.okky.vo.UserInfoVO;
import com.okky.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/signup")
	@ResponseBody
	public String signup(UserInfoVO userInfoVO, @RequestParam(value = "account" , required = true) String account,
			@RequestParam(value = "passwd", required = true) String passwd, Model model) {

		
		if(userInfoVO.getNickname() == null || userInfoVO.getUno() == null || userInfoVO.getUno() == null || userInfoVO.getAddress() == null || userInfoVO.getEmail() == null) {
			throw new Custom400Exception();
		}
		
		UserVO userVO = new UserVO();

		userVO.setAccount(account);
		userVO.setPasswd(passwd);
		
		int checkResult = userService.checkUserAll(userInfoVO, userVO);
		log.info("체크결과 :" +checkResult);
		if(checkResult != 0) {
			throw new Custom400Exception();
		}
		
		ObjectMapper om = new ObjectMapper();

		int resultUserInfo = userService.userInfoInsert(userInfoVO);
		log.info("userinfo insert result : " + (resultUserInfo == 1 ? "success" : "fail"));


		int result = 0;
		
		if (resultUserInfo != 0) {
			userVO.setUid(resultUserInfo);
			result = userService.userInsert(userVO);
		}else {
			throw new DataException();
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", (result == 1 ? "success" : "fail"));

		String mapStr = null;
		try {
			mapStr = om.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mapStr;

	}

}
