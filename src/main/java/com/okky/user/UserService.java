package com.okky.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okky.vo.UserInfoVO;
import com.okky.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public int getCountUser() {
		return userRepository.getCountUser();
	}

	public int userInfoInsert(UserInfoVO userInfoVO) {
		return userRepository.userInfoInsert(userInfoVO);
	}

	public int userInsert(UserVO userVO) {
		return userRepository.userInsert(userVO);
	}

	public int checkUserAll(UserInfoVO userInfoVO, UserVO userVO) {
		
		return userRepository.checkUserInfo(userInfoVO) + 
				userRepository.checkUser(userVO);
	}
	
}
