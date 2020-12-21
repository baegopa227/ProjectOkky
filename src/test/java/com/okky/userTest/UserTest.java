package com.okky.userTest;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.okky.vo.UserInfoVO;
import com.okky.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/kiuk-root-context.xml")
public class UserTest {

	private static final Logger log = LoggerFactory.getLogger(UserTest.class);

	@Autowired
	private SqlSession sqlSession;

	@Test
	public void test() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("account", "baegopa227");
		try {
			log.info("" + sqlSession.selectOne("user.mappers.userMapper.CheckUserAll", map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void checkUserTest() {
		String nickname = "baegopa227";
		String uno = "19920227";
		String phone = "010-3644-2568";
		String email = "baegopa22795@gmail.com";

		log.info("nickname result : "
				+ sqlSession.selectOne("user.mappers.userMapper.CheckUserInfoByNickname", nickname));
		log.info("uno result : " + sqlSession.selectOne("user.mappers.userMapper.CheckUserInfoByUno", uno));
		log.info("phone result : " + sqlSession.selectOne("user.mappers.userMapper.CheckUserInfoByPhone", phone));
		log.info("email result : " + sqlSession.selectOne("user.mappers.userMapper.CheckUserInfoByEmail", email));

		String account = "baegopa227";
		log.info("account result : " + sqlSession.selectOne("user.mappers.userMapper.CheckUserByAccount", nickname));

	}

	@Test
	@Ignore
	public void getUserInsert() {
		UserVO userVO = new UserVO();
		userVO.setId(68);
		userVO.setAccount("baegopa227");
		userVO.setPasswd("asdfqwer");
		sqlSession.insert("user.mappers.userMapper.UserInsert", userVO);
	}

	@Test
	@Ignore
	public void getUserInfoInsert() {
		UserInfoVO vo = new UserInfoVO();
		vo.setName("kiuk");
		vo.setNickname("baegopa227");
		vo.setSex(1);
		vo.setUno("19920227");
		vo.setPhone("010-3644-2568");
		vo.setEmail("baegopa22795@gmail.com");
		vo.setAddress("서울 송파구 방이동");
		try {
			sqlSession.insert("user.mappers.userMapper.UserInfoInsert", vo);
			log.info("userInfoInsert : " + vo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	@Transactional
	@Ignore
	public void getUserCount() {
		log.info("count : " + sqlSession.selectOne("user.mappers.userMapper.GetCountUser"));
	}

}
