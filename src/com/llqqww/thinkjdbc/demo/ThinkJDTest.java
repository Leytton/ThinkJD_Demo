package com.llqqww.thinkjdbc.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.llqqww.thinkjdbc.D;
import com.llqqww.thinkjdbc.M;

public class ThinkJDTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
//		HikariConfig config = new HikariConfig("/hikari_debug.properties");
//		HikariDataSource dataSource = new HikariDataSource(config);
//		D.setDataSource(dataSource);
		D.setDbConfig("jdbc:mysql://127.0.0.1:3306/thinkjdbc?useUnicode=true&characterEncoding=UTF-8","root","root");
		D.setTablePrefix("jd_");
		D.getVersion();
//		long id=new M("user").field("name,weight,time","Tom",50,System.currentTimeMillis()/1000).add();
//		System.out.println(id);
//		id=new M("user").field("",null,"Bob",50,System.currentTimeMillis()/1000).add();
//		System.out.println(id);
		long num= (long) D.M("user").where("id<5").sum("name");
		System.out.println("sum:"+num);
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
			long id=new M("machine_event").fetchSql(false).field("machine_id,type,event,time",1111,5,"摄像头怎么了",System.currentTimeMillis()/1000).add();
			System.out.println(id);
			id=new M("machine_event").fetchSql(false).field("",null,1111,5,"摄像头怎么了",System.currentTimeMillis()/1000).add();
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
			long num=new M("machine_event").fetchSql(false).field("machine_id,type,event",1112,0,"机器上线").where("id=?",13434).save();
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
			User res1 = new M("user").field("id,name").find(User.class);
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
