package myTools;



import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  

import com.mysql.jdbc.PreparedStatement;
  
public class JDBCTest {  
	private Statement stat = null;
	private PreparedStatement psStatement = null;
	private ResultSet rsq   = null;
	private String tablename = null;
	private Connection conn  = null;
	private Connection connection  = null;
	String sql;
	
	public JDBCTest(String tablename){
		this.tablename = tablename;
	}  
 
	/* 查询数据库，输出符合要求的记录的情况*/  
	
	public String  queryBySql(String sql,Object[] paramas){
//		connection();
		String code = null;
		try {
			java.sql.PreparedStatement prepareStatement = conn.prepareStatement(sql);
			if(paramas!=null&&paramas.length>0){
				for(int i=0;i<paramas.length;i++){
					prepareStatement.setObject(i+1, paramas[i]);
				}
			}
			while (rsq.next()) { 
				code = rsq.getString("regist_code");
				break;
				}
				rsq.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return code;
	}
/*	
     查询数据库，输出符合要求的记录的情况  
	public String  selectPhoneVeryfyCode(String phonenumber){
		conn();
		String code = null;
		try {
			rsq =stat.executeQuery("select regist_code from "+tablename+" where receive_code = '"+phonenumber+"'");
			while (rsq.next()) { 
				System.out.println("456456456456456456456");
				code = rsq.getString("regist_code");
				break;
				}
				rsq.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return code;
	}
	
*/
	public void connection(String database,String username,String passwd){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//String database = "jdbc:mysql://172.16.12.51:3306/customer"
			//String username = "writeuser"
			//String passwd = "parkland100"
			conn = DriverManager.getConnection("jdbc:mysql://172.16.12.51:3306/"+database, username, passwd);
			stat = conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String  selectSettleBill(){
//		connection("settle", "writeuser", "parkland100");
		String code = null;
		sql = "select send_body from "+tablename+" where to_phone = 18610066360 ORDER BY create_time desc limit 1";
		try {
			rsq =stat.executeQuery(sql);
			rsq.last();
			int rsqi = rsq.getRow();
			System.out.println(rsqi);
			for (int i=1;i<=rsqi;i++){
//			absolute	将光标移动到此 ResultSet 对象的给定行编号
			rsq.absolute(i);
			System.out.println(rsq.getString(1));
			System.out.println(rsq.getString(1).subSequence(0,6));
			}
/*		}
			while (rsq.next()) { 
				for (int i=1;i<=rsqi;i++){
				System.out.println(rsq.getString(i));
			}
				break;
			}*/
			rsq.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			close();
		}
		return code;
	}
	public String   exesql(String sql){
		String result = null;
		try {
			rsq =stat.executeQuery(sql);
			rsq.last();
			result = rsq.getString(1);
			rsq.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			close();
		}
		return result;
	}
	
	private void close() {
		if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		String sql = "select send_body from sms_queue where to_phone = 18610066360 ORDER BY create_time desc limit 1";
		JDBCTest test = new JDBCTest("sms_queue");
		test.connection("smp", "writeuser", "parkland100");
		String ss = test.exesql(sql);
		System.out.println(ss.subSequence(0, 6));
	}
	
}  