package com.jd.nbapp.bean;

import java.io.Serializable;

public class MyLove implements Serializable{
	private int myLoveId;
	private String  username;
	private int goodsId;
	public int getMyLoveId() {
		return myLoveId;
	}
	public void setMyLoveId(int myLoveId) {
		this.myLoveId = myLoveId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	
}
