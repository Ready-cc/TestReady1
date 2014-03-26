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
		try {
			rsq =stat.executeQuery("select regist_code from "+tablename+" where id = '100-20140303-708-001731'");
			while (rsq.next()) { 
				System.out.println("456456456456456456456");
//				code = rsq.getString("regist_code");
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
	
	private void close() {
		if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		JDBCTest test = new JDBCTest("settle_bill");
		test.connection("settle", "writeuser", "parkland100");
		System.out.println(test.selectSettleBill());
		//test.deletePhoneVeryfyCode("13522939122");
		//System.out.print(test.getLocatorCSS( "Password"));	
	}
	
}  