package com.llqqww.thinkjdbc.demo;

import static org.junit.Assert.fail;

import java.sql.Connection;
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
			//System.out.println(JSON.toJSON(res));
			
			//select sex,sum(weight) as weight,avg(age) as age,count(id) as num from jd_user where id>5 group by sex order by sex desc limit 0,10
			res = new M("user").field("sex,sum(weight) as weight,avg(age) as age,count(id) as num").where("id>?",5).group("sex").order("sex desc").page(1, 10).select(User.class);
			//System.out.println(JSON.toJSON(res));
			
			//select jd_user.id,name,weight,sum(gold) as num from jd_user left join jd_gold on user_id=jd_user.id where jd_user.id>3 group by jd_user.id
			//res = new M("user").prefix("jd_").fetchSql(true).field("jd_user.id,name,weight,sum(gold) as num").join("left join jd_gold on user_id=jd_user.id").where("jd_user.id>3").group("jd_user.id").select(User.class);
			res = new M("user").field("jd_user.id,name,weight,sum(gold) as num").join("left join jd_gold on user_id=jd_user.id").where("jd_user.id>3").group("jd_user.id").select(User.class);
			//System.out.println(JSON.toJSON(res));
			
			res = new M("user").field("id,name").fetchSql(false).where("id=4").union("select id,name from jd_user where id<3",true)
					.union("select id,name from jd_user where id=3",false).select(User.class);
			System.out.println(JSON.toJSON(res));
	}

	@Test
	public void testFindClassOfTLong() throws SQLException {
		User user=D.M("user").fetchSql(true).find(User.class,3);
		System.out.println(JSON.toJSON(user));
	}

	@Test
	public void testFindClassOfTStringObject() throws SQLException {
		User user=D.M("user").fetchSql(false).find(User.class,"name","Bob");
		System.out.println(JSON.toJSON(user));
	}

	@Test
	public void testFindClassOfT() throws SQLException{
		User res = new M("user").fetchSql(true).field("id,name").where("id>?",4).order("id asc").find(User.class);
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
	
	@Test
	public void testStartTrans(){
		Connection conn=null;
		try {
			conn = D.M().startTrans();
			long id=new M("gold").trans(conn).field("user_id,gold,type,time",3,5,0,System.currentTimeMillis()/1000).add();
			System.out.println(id);
			if(id>0) {
				throw new SQLException("Transaction Rollback Test");
			}
			id=new M("gold").trans(conn).field("user_id,gold,type,time",3,5,0,System.currentTimeMillis()/1000).add();
			System.out.println(id);
			
			D.M().commit(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				D.M().rollback(conn);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
}
