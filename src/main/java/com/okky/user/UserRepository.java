package com.okky.user;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.okky.vo.UserInfoVO;
import com.okky.vo.UserVO;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;

	public int getCountUser() {
		return sqlSession.selectOne("user.mappers.userMapper.GetCountUser");
	}
	
	public int userInfoInsert(UserInfoVO vo) {
		sqlSession.insert("user.mappers.userMapper.UserInfoInsert", vo);
		return vo.getId();
	}

	public int userInsert(UserVO userVO) {
		return sqlSession.insert("user.mappers.userMapper.UserInsert", userVO);
	}

	public int checkUserInfoByNickname(String nickname) {
		return sqlSession.selectOne("user.mappers.userMapper.CheckUserInfoByNickname", nickname);
	}

	public int checkUserInfoByUno(String uno) {
		return sqlSession.selectOne("user.mappers.userMapper.CheckUserInfoByUno", uno);
	}
	
	public int checkUserInfoByPhone(String phone) {
		return sqlSession.selectOne("user.mappers.userMapper.CheckUserInfoByPhone", phone);
	}
	
	public int checkUserInfoByEmail(String email) {
		return sqlSession.selectOne("user.mappers.userMapper.CheckUserInfoByEmail", email);
	}
	
	public int checkUserByAccount(String account) {
		return sqlSession.selectOne("user.mappers.userMapper.CheckUserInfoByAccount", account);
	}

	public int checkUserInfo(UserInfoVO userInfoVO) {
		int result = sqlSession.selectOne("user.mappers.userMapper.CheckUserInfoAll", userInfoVO);
		return result;
	}

	public int checkUser(UserVO userVO) {
		int result = sqlSession.selectOne("user.mappers.userMapper.CheckUserAll", userVO);
		return result;
	}
}
