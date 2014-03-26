package myTools;


//就只有4个步骤：1.加载驱动;2.链接数据库;3.执行命令；4.关闭数据库;  
import java.sql.*;  
class SqlConn{  
  /*要是更换数据库，就直接更换这些语句就行了，main函数的那些都不用动的，主要是驱动(DBDRIVER)和链接方式(DBURL)*/  
  private static String DBDRIVER   = "com.mysql.jdbc.Driver";
  //这个是与下载jdbc-mysql里面的那个driver.class文件是对应的，你  
                                                              //可以解压找下，会发觉驱动就是那个鬼东西的。。  
  private static String DBURL      = "jdbc:mysql://172.16.12.51:3306/settle";
  /* 
                                      jdbc:mysql://localhost:3306:test这句里面分如下解析： 
                                      jdbc:mysql://   是指JDBC连接方式； 
                                      localhost:      是指你的本机地址； 
                                      3306            SQL数据库的端口号； 
                                      study           就是你要连接的数据库的地址。 
                                                      你可以试下不要这个'study'，或者胡乱接一个不存在的数据库， 
                                                      然后还可以执行下面语句来实现连接数据库（a） 
                                      */  
  
  private static String DBUSER     = "writeuser";  
  private static String DBPASSWORD = "parkland100";  
  public static void main(String[] args)throws Exception   
  {  
      Class.forName(DBDRIVER).newInstance();//1.加载驱动  
      Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);//2.获得链接  
      Statement statement = conn.createStatement();//3.执行命令  
      //statement.executeUpdate("use study");//(a)要是没有上面的那个数据库，就要使用这个函数来连接数据库  
      ResultSet result = statement.executeQuery("SELECT * from settle_bill where id = '100-20140303-708-001731'");//结果收集，迭代  
      /*   while(result.next()){  
          printf(result.getObject(1)+" ");  
          printf(result.getObject(2)+" ");  
          printf(result.getObject(3)+" ");  
          printf(result.getObject(4)+"\n");  
      }*/
    
      printf(result.getRow());
      conn.close();  
  }  
  public static void printf(Object obj){  
      System.out.print(obj);  
  }  
  public static void printfln(Object obj){  
      System.out.println(obj);  
  }  

}  