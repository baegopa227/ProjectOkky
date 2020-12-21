package com.okky.dbconnect;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DBConnect {
	
	private Logger log = LoggerFactory.getLogger(DBConnect.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	public SqlSession connect() {
		if(sqlSession == null) {
			log.info("session error");
			return null;
		}
		return sqlSession;
	}
	
	
	public boolean connectTest() {
		return sqlSession == null ? false:true;
	}
	
}
