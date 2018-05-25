package com.llqqww.thinkjdbc.demo;

import com.llqqww.thinkjdbc.Column;

//@Table(name="user")Ĭ������Ϊ����,��ע���ض���
public class User {
	//@Column(isKey=true)Ĭ��idΪ������isAutoInc=true����,��ע���ض���
	private Long id;
	private Integer age;
	//@Column(name="user_name")�ֶ�����ע���ض���
	private String name;
	@Column(name="weight")
	private Float remap_weight;
	private Boolean sex;
	
	@Column(isColumn=false)
	private Integer num;
	
	private Long time;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getRemap_weight() {
		return remap_weight;
	}
	public void setRemap_weight(Float remap_weight) {
		this.remap_weight = remap_weight;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
}
