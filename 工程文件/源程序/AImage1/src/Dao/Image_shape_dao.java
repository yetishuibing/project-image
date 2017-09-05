package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.Image_shape;
import Bean.Image_wenli;
import DB.ConnectMysql;

import com.mysql.jdbc.Connection;

public class Image_shape_dao {
	public void insert(int i,String name,double M1,double M2,double M3,double M4,double M5,double M6,double M7,double lixin,String path) throws SQLException{
	 	Connection conn = (Connection) ConnectMysql.getConnection();
	 	 String sql="insert into shape (ID,Name,M1,M2,M3,M4,M5,M6,M7,lixin,Path) values (?,?,?,?,?,?,?,?,?,?,?)";  
	        try {  
	            PreparedStatement preStmt=conn.prepareStatement(sql);  
	            preStmt.setInt(1, i);  
	            preStmt.setString(2, name);  
	            preStmt.setDouble(3, M1); 
	            preStmt.setDouble(4, M2); 
	            preStmt.setDouble(5, M3); 
	            preStmt.setDouble(6, M4); 
	            preStmt.setDouble(7, M5); 
	            preStmt.setDouble(8, M6); 
	            preStmt.setDouble(9, M7); 
	            preStmt.setDouble(10, lixin); 
	            preStmt.setString(11, path); 
	        
	              
	            preStmt.executeUpdate();  
	              
	        } catch (SQLException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            
	            System.out.println(e.getMessage());
	        }  
		 
	}
	public List<Image_shape> selectshape() throws SQLException{
		
		 List<Image_shape> ImageshapeList = new ArrayList<Image_shape>();
		 Image_shape imageshape=null;
		 Connection conn = (Connection) ConnectMysql.getConnection();
		    StringBuilder sb = new StringBuilder();
		    String sql = "select * from shape ";   
		    PreparedStatement ptmt = conn.prepareStatement(sql);
		    ResultSet rs = ptmt.executeQuery();
		    
		    while (rs.next())
		    {
		    	imageshape=new Image_shape();
		    	imageshape.setId(rs.getInt("ID"));
		    	imageshape.setName(rs.getString("Name"));
		    	
		    	imageshape.setM1(rs.getDouble("M1"));
		    	imageshape.setM2(rs.getDouble("M2"));
		    	
		    	imageshape.setM3(rs.getDouble("M3"));
		    	imageshape.setM4(rs.getDouble("M4"));
		    	
		    	imageshape.setM5(rs.getDouble("M5"));
		    	imageshape.setM6(rs.getDouble("M6"));
		    	
		    	imageshape.setM7(rs.getDouble("M7"));
		    	
		    	imageshape.setLixin(rs.getDouble("lixin"));
		    
		    	
		    	imageshape.setPath(rs.getString("Path"));
		    	
		    	ImageshapeList.add(imageshape);
		    }
		   return ImageshapeList;
		  
	}


}
