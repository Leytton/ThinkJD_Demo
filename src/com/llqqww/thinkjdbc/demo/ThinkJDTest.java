package com.llqqww.thinkjdbc.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.llqqww.thinkjdbc.D;
import com.llqqww.thinkjdbc.M;

public class ThinkJDTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
//		HikariConfig config = new HikariConfig("/hikari_debug.properties");
//		HikariDataSource dataSource = new HikariDataSource(config);
//		D.setDataSource(dataSource);
		//数据库配置(只需调用一次)
		D.setDbConfig("jdbc:mysql://127.0.0.1:3306/thinkjdbc?useUnicode=true&characterEncoding=UTF-8","root","root");
		//设置表前缀
		D.setTablePrefix("jd_");
		
		D.setAutoClose(false);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Gold gold = new Gold();
				gold.setUser_id(1L);
				gold.setGold(5);
				gold.setGold_type(0);
				try {
					D.M(gold).add();
					D.M(gold).add();
				} catch (SQLException e) {
					D.closeConn();
					e.printStackTrace();
				}
			}
		}, "Thread_1").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Gold gold = new Gold();
				gold.setUser_id(2L);
				gold.setGold(5);
				gold.setGold_type(0);
				try {
					D.M(gold).add();
					D.M(gold).add();
				} catch (SQLException e) {
					D.closeConn();
					e.printStackTrace();
				}
			}
		}, "Thread_2").start();

/*		
		//ThinkJDBC会根据JavaBean自动获取表名、主键、字段名和数据
		//实例化JavaBean
		User user = new User();
		user.setId(5L);
		user.setAge(10);
		user.setName("Hello");
		
		//插入数据
		long id=D.M(user).fetchSql(true).save();
		System.out.println(id);
		
		//查询数据
		user=D.M(User.class).find(id);
		System.out.println(JSON.toJSON(user));//fastJson输出
//
//		//更新数据，不指定字段名默认更新JavaBean的所有非空属性
		user.setSex(false);
		long num=D.M(user).field("sex").save();
		System.out.println(num);
//
//		//删除数据
		num=D.M(user).delete();
		System.out.println(num);
//
//		//table模式
//		//插入数据
		id=D.M("user").field("name,weight").data("Tom",60).add();
//		//更新数据
		D.M("user").field("name,weight").data("Tom",100).where("id=?",id).save();
		//查询数据,必须要用class或者实例bean参数指定返回数据类型
		user=D.M(User.class).find(7);
		
//		//删除数据
		D.M("user").delete(id);
*/		
		
//		D.getVersion();
//		long id=new M("user").field("name,weight,time","Tom",50,System.currentTimeMillis()/1000).add();
//		System.out.println(id);
//		id=new M("user").field("",null,"Bob",50,System.currentTimeMillis()/1000).add();
//		System.out.println(id);
//		User user = D.M(User.class).fetchSql(false).field("id,name,age").order("id desc").find("name","Tom");
//		System.out.println(JSON.toJSON(user));
//		user=new User();
//		user.setAge(122);
//		D.M(User.class).fetchSql(false).find(10);
		
//		D.M(user).fetchSql(false).save();
//		D.M(user).fetchSql(true).add();
		
//		System.out.println(Integer.MAX_VALUE);
//		add();
//		delete();
//		save();
//		select();
//		count();
//		execute();
	}
	
	public static void readCfg() throws FileNotFoundException, IOException {
		Properties cfg = new Properties();
		cfg.load(new FileInputStream("thinkjdbc.properties"));
		Enumeration names = cfg.propertyNames();
        while(names.hasMoreElements()) {
            String strKey = (String) names.nextElement();
            String strValue = cfg.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }
	}
	
	public static void add(){
		try {
			long id=new M("machine_event").fetchSql(false).field("machine_id,type,event,time").data(1111,5,"摄像头怎么了",System.currentTimeMillis()/1000).add();
			System.out.println(id);
			id=new M("machine_event").fetchSql(false).data(null,1111,5,"摄像头怎么了",System.currentTimeMillis()/1000).add();
			System.out.println(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void delete(){
		try {
			long num=new M("machine_event").delete(13424);
			System.out.println(num);
			num=new M("machine_event").delete("time",1523681398);
			System.out.println(num);
			num=new M("machine_event").where("id>=?",13421).delete();
			System.out.println(num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void save(){
		try {
			long num=new M("machine_event").fetchSql(false).field("machine_id,type,event").data(1112,0,"机器上线").where("id=?",13434).save();
			System.out.println(num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void select(){
		try {
//			List<Machine> res = new M("machine").fetchSql(true).field("id,name,per_money").where("id=?",1068).select(Machine.class);
//			System.out.println(JSON.toJSON(res));
//			Machine res1 = new M("machine").field("id,name").page(1, 9).find(Machine.class);
//			System.out.println(JSON.toJSON(res1));
//			User res1 = new M("user").field("id,name").find(User.class);
			User res1 = new M(User.class).field("id,name").find();
			System.out.println(JSON.toJSON(res1));
//			List<Machine> res3 = new M("gold_log").fetchSql(false).field("id,gold_type,type_name,gold_num").page(1, 9).order("id desc").select(Machine.class);
//			System.out.println(JSON.toJSON(res3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void count(){
		try {
			long num= D.M("machine_event").fetchSql(true).where("id>13441").count();
			System.out.println("count:"+num);
			num= new M("machine_event").fetchSql(false).where("id>13441").count("type");
			System.out.println("count:"+num);
			num= (long) D.M("machine_event").where("id<0").max("id");
			System.out.println("max:"+num);
			num= (long) D.M("machine_event").where("id<13441").max("id");
			System.out.println("max:"+num);
			num= (long) D.M("machine_event").min("id");
			System.out.println("min:"+num);
			num= (long) D.M("machine_event").where("id>13441").min("id");
			System.out.println("min:"+num);
			num= (long) D.M("machine_event").fetchSql(false).where("id>13442").avg("id");
			System.out.println("avg:"+num);
			double avg= D.M("machine_event").fetchSql(false).where("id>13442").avg("id");
			System.out.println("avg:"+avg);
			num= (long) D.M("machine_event").where("id>13441").sum("type");
			System.out.println("sum:"+num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void execute(){
		try {
			new M().execute();
			new M().execute("update machine set user_id=3 where id=1067;");
			new M().execute("update machine set user_id=4 where id=1115;","update machine set user_id=4 where id=1068;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}