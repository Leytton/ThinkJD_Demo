package com.llqqww.thinkjdbc.demo;

import com.llqqww.thinkjdbc.Column;

public class Gold {
//	@Column(isKey=true)
//	Integer id;
	Long user_id;
	Integer gold;
	@Column(name="type")
	Integer gold_type;
	Long time;
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Integer getGold() {
		return gold;
	}
	public void setGold(Integer gold) {
		this.gold = gold;
	}
	public Integer getGold_type() {
		return gold_type;
	}
	public void setGold_type(Integer gold_type) {
		this.gold_type = gold_type;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
	
}
