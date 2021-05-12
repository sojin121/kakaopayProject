package com.kakaopay.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakaopay.project.RestException;
import com.kakaopay.project.mapper.KeyNumrngMapper;
import com.kakaopay.project.model.KeyNumrngDVO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/key/")
public class KeyController {

	@Autowired
	private KeyNumrngMapper keyNumrngMapper;
	
	@ApiOperation(value = "Key 정보 등록", notes = "업무에 사용할 용도의 key를 등록")
	@PostMapping(value = "/register")
    public KeyNumrngDVO registerKeyInfo(@RequestBody KeyNumrngDVO searchVo){
        System.out.println("input : " + searchVo.getKeyName());
        if(searchVo.getKeyName() == null || searchVo.getKeyName().isEmpty()) {
        	throw new RestException(HttpStatus.NOT_FOUND, "key name is empty");
        } else if(searchVo.getKeyTypCd() == null || searchVo.getKeyTypCd().isEmpty()){
        	throw new RestException(HttpStatus.NOT_FOUND, "keyTypCd is empty");
        } else if(!searchVo.getKeyTypCd().equals("01") && !searchVo.getKeyTypCd().equals("02")) {
        	throw new RestException(HttpStatus.NOT_FOUND, "keyTypCd must be 01(숫자) or 02(문자)");
        }
        
        KeyNumrngDVO outDVO = keyNumrngMapper.getKeyInfobyKeyName(searchVo);
        
        if(outDVO != null) {
        	throw new RestException(HttpStatus.NOT_FOUND, "이미 존재하는 Key 입니다. (" + outDVO.getExnt() + ")");
        } else {
			int cnt = keyNumrngMapper.registerKeyInfo(searchVo);
			System.out.println("insert cnt :" + cnt);
        }
        
		return searchVo;
    }
	
	@ApiOperation(value = "새로운 KEY 발급", notes = "key 유형별로 발급")
	@GetMapping(value = "/{keyName}")
	public String getNextKeyNumrng(@PathVariable("keyName") String keyName) {
		
		if(keyName == null || keyName.isEmpty()) {
        	throw new RestException(HttpStatus.NOT_FOUND, "key name is empty");
        }
		
		KeyNumrngDVO inDVO = new KeyNumrngDVO();
		inDVO.setKeyName(keyName);
		
		String result = null;
		KeyNumrngDVO outDVO = keyNumrngMapper.getKeyInfobyKeyName(inDVO);
        
        if(outDVO == null) {
        	throw new RestException(HttpStatus.NOT_FOUND, keyName + " must be register KEY INFO first!!");
        } else {
        	// 숫자형 key
        	if(outDVO.getKeyTypCd().equals("01")) {
        		keyNumrngMapper.getNextNumKey(outDVO);
        		result = Integer.toString(keyNumrngMapper.getLastInsertKey());
        	}
        	// 문자형 key
        	else if(outDVO.getKeyTypCd().equals("02")) {
        		String randomStrKey = keyNumrngMapper.getRandomStringKey();

        		outDVO.setStrKey(keyNumrngMapper.getRandomStringKey());
        		keyNumrngMapper.getNextStringKey(outDVO);
        		result = randomStrKey;
        	}
        }
		return result;
	}
}
