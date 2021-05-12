package com.kakaopay.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kakaopay.project.mapper.TestMapper;
import com.kakaopay.project.model.TestDVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/")
public class SampleController {

	 @Autowired
	    private SqlSessionFactory sqlSessionFactory;
	 
	 @Autowired
		private TestMapper testMapper;
	 
	@RequestMapping(method = RequestMethod.GET, value = "sample", 
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "Test Sample", tags = "sample")
    public ResponseEntity sample(@RequestParam String param) {
        return ResponseEntity.ok(param);
    }
	
	@ApiOperation(value = "사용자 정보 조회", notes = "UserId를 이용하여 사용자 정보를 조회합니다.")
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object findUser(
            @ApiParam(value = "user id", required = true, example = "1")
            @PathVariable(value = "id", required = true) String id,
            @ApiParam(value = "User Agent Type ", required = true, example = "Mozila")
            @RequestHeader(value = "User-Agent") String userAgent,
            @ApiParam(value = "parameter1 ", required = false)
            @RequestParam(value = "param1", required = false) String param1,
            @ApiParam(value = "parameter2 ", required = false)
            @RequestParam(value = "param2", required = false) String param2){

        return true;
    }

    @ApiOperation(value = "사용자 리스트 조회", notes = "특정 조건에 맞는 사용자 리스트를 조회합니다.")
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object findUsers(
            @RequestHeader(value = "User-Agent") String userAgent,
            @ModelAttribute User user){

        return true;
    }

    @ApiOperation(value = "사용자 생성", notes = "신규 사용자를 생성합니다.")
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object CreateUser(
            @RequestHeader(value = "User-Agent") String userAgent,
            @RequestBody(required = true) User user){

        return true;
    }
    
    @GetMapping(value = "/message")
	public String testByResponseBody() {
		String message = "안녕하세요. 잠시 후에 화면에서 만나요!";
		
		
		TestDVO param = new TestDVO();
		param.setBoardIdx((long)1230);
    	List<TestDVO> testDVO = testMapper.selectTestList(param);
    	System.out.println("TESTDVO:" + testDVO.get(0).getContent() +" , " + testDVO.get(0).getWriter());
    	
		
		
		return testDVO.get(0).getContent();
	}
    
    @GetMapping(value = "/members")
	public Map<Integer, Object> testByResponseBody2() {

		Map<Integer, Object> members = new HashMap<>();

		for (int i = 1; i <= 20; i++) {
			Map<String, Object> member = new HashMap<>();
			member.put("idx", i);
			member.put("nickname", i + "길동");
			member.put("height", i + 20);
			member.put("weight", i + 30);
			members.put(i, member);
		}

		return members;
	}

}