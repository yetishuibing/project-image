package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.*;
import DB.ConnectMysql;

import com.mysql.jdbc.Connection;

public class Image_wenli_dao {
	
	public void insert(int i,String name,double E,double deviE,double D,double deviD,double I,double deviI,double C,double deviC,String path) throws SQLException{
	 	Connection conn = (Connection) ConnectMysql.getConnection();
	 	 String sql="insert into wenli (ID,Name,E,DeviE,D,DeviD,I,DeviI,C,DeviC,Path) values (?,?,?,?,?,?,?,?,?,?,?)";  
	        try {  
	            PreparedStatement preStmt=conn.prepareStatement(sql);  
	            preStmt.setInt(1, i);  
	            preStmt.setString(2, name);  
	            preStmt.setDouble(3, E); 
	            preStmt.setDouble(4, deviE); 
	            preStmt.setDouble(5, D); 
	            preStmt.setDouble(6, deviD); 
	            preStmt.setDouble(7, I); 
	            preStmt.setDouble(8, deviI); 
	            preStmt.setDouble(9, C); 
	            preStmt.setDouble(10, deviC); 
	            preStmt.setString(11, path); 
	              
	            preStmt.executeUpdate();  
	              
	        } catch (SQLException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            
	            System.out.println(e.getMessage());
	        }  
		 
	}
	public List<Image_wenli> selectwenli() throws SQLException{
		
		 List<Image_wenli> ImagewenliList = new ArrayList<Image_wenli>();
		 Image_wenli imagewenli=null;
		 Connection conn = (Connection) ConnectMysql.getConnection();
		    StringBuilder sb = new StringBuilder();
		    String sql = "select * from wenli ";   
		    PreparedStatement ptmt = conn.prepareStatement(sql);
		    ResultSet rs = ptmt.executeQuery();
		    
		    while (rs.next())
		    {
		    	imagewenli=new Image_wenli();
		    	imagewenli.setId(rs.getInt("ID"));
		    	imagewenli.setName(rs.getString("Name"));
		    	
		    	imagewenli.setE(rs.getDouble("E"));
		    	imagewenli.setDeviE(rs.getDouble("DeviE"));
		    	
		    	imagewenli.setD(rs.getDouble("D"));
		    	imagewenli.setDeviD(rs.getDouble("DeviD"));
		    	
		    	imagewenli.setI(rs.getDouble("I"));
		    	imagewenli.setDeviI(rs.getDouble("DeviI"));
		    	
		    	imagewenli.setC(rs.getDouble("C"));
		    	imagewenli.setDeviC(rs.getDouble("DeviC"));
		    	
		    	
		    	imagewenli.setPath(rs.getString("Path"));
		    	
		    	ImagewenliList.add(imagewenli);
		    }
		   return ImagewenliList;
		  
	}


	

}
