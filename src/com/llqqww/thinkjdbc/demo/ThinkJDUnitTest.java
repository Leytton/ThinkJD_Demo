package com.llqqww.thinkjdbc.demo;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.llqqww.thinkjdbc.D;
import com.llqqww.thinkjdbc.M;

public class ThinkJDUnitTest {

	@Before
	public void setUp() throws Exception {
		D.setDbConfig("jdbc:mysql://127.0.0.1:3306/thinkjdbc?useUnicode=true&characterEncoding=UTF-8","root","root");
		D.setTablePrefix("jd_");
	}

	@Test
	public void testSelect() throws SQLException {
			//select id,name,weight from jd_user where id>3
			List<User> res = new M("user").field("id,name,weight").where("id>3").select(User.class);
			System.out.println(JSON.toJSON(res));
			
			//select sex,sum(weight) as weight,avg(age) as age,count(id) as num from jd_user where id>5 group by sex order by sex desc limit 0,10
			res = new M("user").field("sex,sum(weight) as weight,avg(age) as age,count(id) as num").where("id>?",5).group("sex").order("sex desc").page(1, 10).select(User.class);
			System.out.println(JSON.toJSON(res));
	}

	@Test
	public void testFindClassOfTLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClassOfTStringObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClassOfT() throws SQLException{
		User res = new M("user").field("id,name").where("id>?",4).order("id asc").find(User.class);
		System.out.println(JSON.toJSON(res));
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountString() {
		fail("Not yet implemented");
	}

	@Test
	public void testMax() {
		fail("Not yet implemented");
	}

	@Test
	public void testMin() {
		fail("Not yet implemented");
	}

	@Test
	public void testAvg() {
		fail("Not yet implemented");
	}

	@Test
	public void testSum(){
		try {
			long num= (long) D.M("user").where("id<2").sum("weight");
			System.out.println("sum:"+num);
			
			num= (long) D.M("user").where("id<5").sum("id");
			System.out.println("sum:"+num);
			
			num= (long) D.M("user").where("id<5").sum("time");
			System.out.println("sum:"+num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAdd(){
		try {
			long id=new M("user").field("name,weight,time","Tom",50,System.currentTimeMillis()/1000).add();
			System.out.println(id);
			assert(id>0);
			
			id=new M("user").field("",null,"Bob",50,System.currentTimeMillis()/1000).add();
			System.out.println(id);
			assert(id>0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteStringObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecute() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetField() {
		fail("Not yet implemented");
	}

}
