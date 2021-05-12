package com.kakaopay.project.mapper;

import java.util.List;

import com.kakaopay.project.model.TestDVO;

public interface TestMapper {

	public String selectTest();
	
	public List<TestDVO> selectTestList(TestDVO params);
	
	public int insertTest(TestDVO params);
	
}
