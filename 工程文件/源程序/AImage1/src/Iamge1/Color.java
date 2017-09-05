package Iamge1;


import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import Dao.Image_color_dao;
public class Color {
	static float PI=(float) 3.1415926;
	private static String tupian_name=null;
	private static float gray_avage;
	
	public static void main(String[] args) throws SQLException{
	         getFileName();
	}	
	
	/*
	 * 读取图片库
	 */
    public static void getFileName() {
    	String path = "C:/Users/ASUS/Desktop/image database_1/"; // 路径
		File f = new File(path);
		if (!f.exists()) {
		       System.out.println(path + " not exists");
		       return;
		} 
		File fa[] = f.listFiles();
		BufferedImage bi = null;
		for (int i = 0; i < fa.length; i++) {
		//for (int i = 0; i <1; i++) {
		       File fs = fa[i];
		       if (fs.isDirectory()) {
		             System.out.println(fs.getName() + " [目录]");
		       } else {
		    	   try{
		    	     bi = ImageIO.read(fs);      	     
		    	     double [][] rgbzhifang=getzhifang(bi);
		    	    
		    	     tupian_name=fs.getName();
		    	   
		    	     String tezhengname= write(tupian_name,rgbzhifang);
		    	     Image_color_dao insertimage=new Image_color_dao();
		    	     
		    	     insertimage.insert(i,tupian_name,tezhengname,path);
		             }catch(Exception e){
		            	 
		            	 System.out.println(e.getMessage());
		             }
		    	   
		       }
		   }
		} 
    /*
     * 每张图的特征值
     */
    public static double [][] getzhifang(BufferedImage img)  
    {  
       double [][] histgram=new double [3][256];  
       
       double []rgb1=new double []{0,0,0};
       int width=img.getWidth();//图片宽度  
       int height=img.getHeight();//图片高度  
       int pix[]= new int [width*height];//像素个数  
       int r,g,b;//记录R、G、B的值  
       pix = img.getRGB(0, 0, width, height, pix, 0, width);//将图片的像素值存到数组里  
       for(int i=0; i<width*height; i++)   
       {    
           r = pix[i]>>16 & 0xff; //提取R   
           g = pix[i]>>8 & 0xff;    
           b = pix[i] & 0xff;     
           histgram[0][r] ++;    
           histgram[1][g] ++;    
           histgram[2][b] ++;    
       }    
       double red =0,green=0,blue=0;  
       for(int j=0;j<256;j++){  
           red+=histgram[0][j];  
           green+=histgram[1][j];  
           blue+=histgram[2][j];  
       }  
       for(int j=0;j<256;j++)//将直方图每个像素值的总个数进行量化  
       {  
           histgram[0][j]/=red;  
           histgram[1][j]/=green;  
           histgram[2][j]/=blue;             
       }    
       
       return histgram;
    
       
    }  
    /*
     * 每张图的特征值保存在.txt文件中
     */
   public static String  write(String name,double tezheng[][]) throws IOException{
	   String prefix=name.substring(name.lastIndexOf("."));
	   int num=prefix.length();//得到后缀名长度  
       
	   String fileOtherName=name.substring(0, name.length()-num);//得到文件名。去掉了后缀  
	   String tuname="C:/Users/ASUS/Desktop/tezheng/"+fileOtherName+".txt";
	   File fout = new File(tuname);
       FileOutputStream fos = new FileOutputStream(fout);
       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
      
       for(int j=0;j<256;j++){
    	   for (int i = 0; i < 3; i++) {
    		   String f=tezheng[i][j]+"";
    		   bw.write(f+" ");
    		 
    	   }
    	   bw.newLine();
       }
       bw.close();
       return tuname;
	   
   }
 
   

} 
