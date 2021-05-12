package com.kakaopay.project;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.kakaopay.project.mapper.TestMapper;

@SpringBootTest
class KakakpayApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private SqlSessionFactory sessionFactory;

	
	@Autowired
	private TestMapper testMapper;
	@Test
	void contextLoads() {
	}

	@Test
	public void testByApplicationContext() {
		try {
			System.out.println("=========================");
			System.out.println(context.getBean("sqlSessionFactory"));
			System.out.println("=========================");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBySqlSessionFactory() {
		try {
			System.out.println("=========================");
			System.out.println(sessionFactory.toString());
			System.out.println("=========================");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
    public void bootTest() {
    
		
		SqlSession sqlSession = sessionFactory.openSession();
				
		String result1 = sqlSession.selectOne("com.kakaopay.project.mapper.TestMapper.selectTestList");
		System.out.println("result1 : " + result1);
		String result = testMapper.selectTest();
    	System.out.println("result : " + result);
    }

}
