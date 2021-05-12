package com.kakaopay.project.mapper;

import com.kakaopay.project.model.KeyNumrngDVO;

public interface KeyNumrngMapper {

	public KeyNumrngDVO getKeyInfobyKeyName(KeyNumrngDVO params);
	
	public int registerKeyInfo(KeyNumrngDVO params);
	
	public int getNextNumKey(KeyNumrngDVO params);
	
	public int getLastInsertKey();
	
	public String getRandomStringKey();
	
	public int getNextStringKey(KeyNumrngDVO params);
}
