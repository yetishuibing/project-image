package DB;

import java.sql.*;

public class ConnectMysql {
	
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/image";
	  private static final String UNAME = "root";
	  private static final String PWD = "050196";
	 
	  private static Connection conn = null;
	 
	  static
	  {
	    try
	    {
	      // 1.加载驱动程序
	      Class.forName("com.mysql.jdbc.Driver");
	      // 2.获得数据库的连接
	      conn = DriverManager.getConnection(URL, UNAME, PWD);
	    }
	    catch (ClassNotFoundException e)
	    {
	      e.printStackTrace();
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	    }
	  }
	 
	  public static Connection getConnection()
	  {
	    return conn;
	  }
 
    /**
     * @param args
     * @throws Exception 
     */
	
    /*public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Class.forName("com.mysql.jdbc.Driver");
         
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/Image",
                "root","050196");
        Statement stmt =  conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from image");
         
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t"
                    +rs.getString(2) + "\t"
                    +rs.getString(3)+"\t" +rs.getString(4));
            }
         
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();   
        }
        if (conn != null) {
            conn.close();   
        }
    }
    
    boolean insertImage(String name,float color){
    	Statement stmt =  conn.createStatement();
        ResultSet rs = stmt.executeQuery("update image set Color=");
    	return true;
    }*/
 
}