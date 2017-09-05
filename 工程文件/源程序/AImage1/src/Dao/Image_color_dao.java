package Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;



import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import Bean.*;
import DB.*;

import java.sql.*;

public class Image_color_dao {
	
	
	
	public void updatecolor(String name,String color) throws SQLException{
		 	Connection conn = (Connection) ConnectMysql.getConnection();
		    String sql = "update imagecolor set Color=? where Name=?";   
		    PreparedStatement ptmt = conn.prepareStatement(sql);
		    ptmt.setString(1, color);
		    ptmt.setString(2, name);	   
		    ptmt.execute();		    
			 
	}
	
	public void insert(int id,String name,String tezhengpath,String path){
		  Connection conn = (Connection) ConnectMysql.getConnection();
	 	   String sql="insert into imagecolor (ID,Name,Color,Path) values (?,?,?,?)";  
	        try {  
	            PreparedStatement preStmt=conn.prepareStatement(sql);  
	            preStmt.setInt(1, id);  
	            preStmt.setString(2, name);  
	            preStmt.setString(3, tezhengpath);  
	            preStmt.setString(4, path); 
	              
	            preStmt.executeUpdate();  
	              
	        } catch (SQLException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	            
	            System.out.println(e.getMessage());
	        }  
		 
	}
	
	
	public List<Image_color> selectColor() throws SQLException{
		
		 List<Image_color> ImagecolorList = new ArrayList<Image_color>();
		 Image_color imagecolor=null;
		 Connection conn = (Connection) ConnectMysql.getConnection();
		    StringBuilder sb = new StringBuilder();
		    String sql = "select * from imagecolor ";   
		    PreparedStatement ptmt = conn.prepareStatement(sql);
		    ResultSet rs = ptmt.executeQuery();
		    
		    while (rs.next())
		    {
		    	imagecolor=new Image_color();
		    	imagecolor.setId(rs.getInt("ID"));
		    	imagecolor.setName(rs.getString("Name"));
		    	imagecolor.setColor(rs.getString("Color"));
		    	imagecolor.setPath(rs.getString("Path"));
		    	
		    	ImagecolorList.add(imagecolor);
		    }
		   return ImagecolorList;
		  
	}

	
}
