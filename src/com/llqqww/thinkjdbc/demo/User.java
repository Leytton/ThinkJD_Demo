package com.llqqww.thinkjdbc.demo;

import com.llqqww.thinkjdbc.Column;

//@Table(name="user")默认类名为表名,可注解重定义
public class User {
	//@Column(isKey=true)默认id为主键、isAutoInc=true自增,可注解重定义
	private Long id;
	private Integer age;
	//@Column(name="user_name")字段名可注解重定义
	private String name;
	private Float weight;
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
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
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
