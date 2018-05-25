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
//		D.setCheckField(false);
	}

	@Test
	public void testSelect() throws SQLException {
			
			//select id,name,weight from jd_user where id>3
			List<User> res = new M(User.class).fetchSql(true).select();
			//System.out.println(JSON.toJSON(res));
			
			res = new M(User.class).fetchSql(false).field("id,name,weight").select("name","Tom");
			System.out.println(JSON.toJSON(res));
			
			//select sex,sum(weight) as weight,avg(age) as age,count(id) as num from jd_user where id>5 group by sex order by sex desc limit 0,10
			res = new M(User.class).fetchSql(false).field("sex,sum(weight) as weight,avg(age) as age,count(id) as num").where("id>?",5).group("sex").order("sex desc").page(1, 10).select();
			System.out.println(JSON.toJSON(res));
			
			//select jd_user.id,name,weight,sum(gold) as num from jd_user left join jd_gold on user_id=jd_user.id where jd_user.id>3 group by jd_user.id
			//res = new M("user").prefix("jd_").fetchSql(true).field("jd_user.id,name,weight,sum(gold) as num").join("left join jd_gold on user_id=jd_user.id").where("jd_user.id>3").group("jd_user.id").select(User.class);
			res = new M(User.class).fetchSql(false).field("jd_user.id,name,weight,sum(gold) as num").join("left join jd_gold on user_id=jd_user.id").where("jd_user.id>3").group("jd_user.id").select();
			System.out.println(JSON.toJSON(res));
			
			res = new M(User.class).fetchSql(false).field("id,name").where("id=4").union("select id,name from jd_user where id<3",true)
					.union("select id,name from jd_user where id=3",false).select();
			System.out.println(JSON.toJSON(res));
	}

	@Test
	public void testFindClassOfObject(){
		try {
			User user = D.M(User.class).fetchSql(false).find(10);
			System.out.println(JSON.toJSON(user));
			D.M(user).autoInc(false).fetchSql(false).field("name").where("name=?",user.getId()-1).save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindClassOfTStringObject() throws SQLException {
		User user=D.M(User.class).fetchSql(false).find("name","Bob");
		System.out.println(JSON.toJSON(user));
	}

	@Test
	public void testFindClass(){
		try {
			User res = new M(User.class).fetchSql(false).field("id,name,weight").where("id>?",4).order("time desc").asc("id").desc("age").find();
			System.out.println(JSON.toJSON(res));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
//			long id=new M("user").fetchSql(true).field("name,weight,time").data("Tom",50,System.currentTimeMillis()/1000).add();
//			id=new M(Gold.class).fetchSql(true).data(5,10,1,System.currentTimeMillis()/1000).add();
//			System.out.println(id);
//			assert(id>0);
			User user = new User();
			user.setId(1L);
			user.setName("Hello");
			user.setAge(10);
//			user.setWeight(30F);
			long id=new M(user).fetchSql(true).add();
//			D.M(User.class).fetchSql(true).field("name,weight").data("Tom",60).add();
			id=D.M(User.class).fetchSql(true).data("Tom",60).add();
//			System.out.println(id);
//			long id=D.M(Gold.class).data(1,2,3,null).fetchSql(true).add();
			System.out.println(id);
			assert(id>0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSave() {
		try {
			User user=new User();
			user.setId(12L);
			user.setAge(10);
			user.setName("Hello");
			user.setSex(true);
			long num=D.M(user).autoInc(false).field("name,age").where("id=?",999).fetchSql(true).save();
//			System.out.println(num);
//			long num=D.M(User.class).where("id=?",999).fetchSql(true).field("name").data("Tom").setInc("age",1).setInc("weight", 10).setDec("time", 10).save();
			System.out.println(num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteStringObject() {
		try {
			long num=D.M(User.class).fetchSql(true).delete("name","Tom");
			System.out.println(num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			long num=D.M("user").fetchSql(true).delete(11);
//			long num=D.M(Gold.class).fetchSql(true).delete(11);
			User user=new User();
			user.setId(10L);
//			long num=D.M(user).fetchSql(true).delete();
			System.out.println(num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			conn=D.getTransConnection();
			Long id=new M("gold").fetchSql(false).field("user_id,gold,type,time").data(3,5,0,System.currentTimeMillis()/1000).add();
			System.out.println(id);
			Gold gold = new Gold();
			gold.setUser_id(3L);
			gold.setGold(5);
			gold.setGold_type(0);
			id=new M(gold).fetchSql(false).add();
			System.out.println(id);
			if(id>=0) {
				throw new SQLException("Transaction Rollback Test");
			}
			id=new M(Gold.class).fetchSql(false).field("user_id,gold,type,time").data(3,5,0,System.currentTimeMillis()/1000).add();
			System.out.println(id);
			
			D.commit(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				D.rollback(conn);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
}
