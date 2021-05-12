package com.kakaopay.project.model;

import java.time.LocalDateTime;

public class KeyNumrngDVO {

	private String keyName;
	private String keyTypCd;
	private String bizClssCd;
	private String exnt;
	private int numKey;
	private String strKey;
	private LocalDateTime chgDttm;
    private LocalDateTime regDttm;
    
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getKeyTypCd() {
		return keyTypCd;
	}
	public void setKeyTypCd(String keyTypCd) {
		this.keyTypCd = keyTypCd;
	}
	public String getBizClssCd() {
		return bizClssCd;
	}
	public void setBizClssCd(String bizTypCd) {
		this.bizClssCd = bizTypCd;
	}
	public String getExnt() {
		return exnt;
	}
	public void setExnt(String exnt) {
		this.exnt = exnt;
	}
	public int getNumKey() {
		return numKey;
	}
	public void setNumKey(int numKey) {
		this.numKey = numKey;
	}
	public String getStrKey() {
		return strKey;
	}
	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}
	public LocalDateTime getChgDttm() {
		return chgDttm;
	}
	public void setChgDttm(LocalDateTime chgDttm) {
		this.chgDttm = chgDttm;
	}
	public LocalDateTime getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(LocalDateTime regDttm) {
		this.regDttm = regDttm;
	}
}
