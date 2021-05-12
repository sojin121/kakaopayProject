package com.kakaopay.project;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kakaopay.project.mapper.TestMapper;
import com.kakaopay.project.model.TestDVO;

@SpringBootTest
class MapperTests {

	@MockBean
    TimeMapper timeMapper;

    @Test
    public void test() {
        System.out.println("-------------------------");
        System.out.println("Time: " + timeMapper.getTime());
    }
    
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    
    @Autowired
	private TestMapper testMapper;
    
    @Test
    public void connection_test(){
    	try(SqlSession sqlSession = sqlSessionFactory.openSession()){
    		//String sysdate = sqlSession.selectOne("com.kakaopay.project.mapper.CommentMapper.selectTest");
    		
    		String sysdate = sqlSession.selectOne("com.kakaopay.project.mapper.TestMapper.selectTestList");
    		
    		String result = testMapper.selectTest();
    		
    		//sqlSession.insert("com.kakaopay.project.mapper.CommentMapper.insertComment");
            System.out.println(" testing ::"+sysdate);
            System.out.println(" result ::"+result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void bootTest() {
    
    	String result = testMapper.selectTest();
    	System.out.println("result : " + result);
    	
    	TestDVO param = new TestDVO();
    	List<TestDVO> testDVO = testMapper.selectTestList(param);
    	System.out.println("TESTDVO:" + testDVO.get(0).getContent() +" , " + testDVO.get(0).getWriter());
    	
    	int number = 20;
    		
    	try {
    		for (int i = 1; i <= number; i++) {
    			TestDVO params = new TestDVO();
    			params.setBoardIdx((long) 6529); // 댓글을 추가할 게시글 번호
    			params.setContent(i + "번 댓글을 추가합니다!");
    			params.setWriter(i + "번 회원");
    			int ins = testMapper.insertTest(params);
    			System.out.println("insert cnt :" + ins);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    }
    
    @Test
    public void serviceTest() {
    
    	String result = testMapper.selectTest();
    	System.out.println("result : " + result);
    	
    	TestDVO param = new TestDVO();
    	param.setBoardIdx((long)1230);
    	List<TestDVO> testDVO = testMapper.selectTestList(param);
    	System.out.println("TESTDVO:" + testDVO.get(0).getContent() +" , " + testDVO.get(0).getWriter());
		
    }
}

interface TimeMapper {
    @Select("SELECT now()")
    public String getTime();

    public String getTimeXML();
}