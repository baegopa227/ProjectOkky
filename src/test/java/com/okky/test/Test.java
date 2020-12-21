package com.okky.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.okky.okkyEnum.EType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/kiuk-root-context.xml")
public class Test {
	
	Logger log = LoggerFactory.getLogger(Test.class);
	
	@org.junit.Test
	public void test() {
		System.out.println(EType.applicationError.toString().getClass().getName());
	}

	private void getNametest() {
		// TODO Auto-generated method stub
		
	}
}
