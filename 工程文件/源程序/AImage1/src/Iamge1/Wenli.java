package Iamge1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Dao.Image_color_dao;
import Dao.Image_wenli_dao;


public class Wenli {
	
	private static double co_matrix_0[][] = new double[8][8];
	private static double co_matrix_45[][] = new double[8][8];
	private static double co_matrix_90[][] = new double[8][8];
	private static double co_matrix_135[][] = new double[8][8];
	
	
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
	    	    
	    	    
	    	    insertDBwenli(i,fs.getName(),bi,path);    			           
	             }catch(Exception e){           	 
	            	 System.out.println(e.getMessage());
	             }
	    	   
	       }
	   }
	} 
	/*
	 * 图片灰度降为8级
	 */
	public static int gray(int r,int g,int b) {	
		double G = 0;
		int gray = 0;
		G =r * 0.3 + g * 0.59 + b * 0.11;
		if (G >= 0 && G < 32) {
			gray = 0;
		} else if (G >= 32 && G < 64) {
			gray = 1;
		} else if (G >= 64 && G < 96) {
			gray = 2;
		} else if (G >= 96 && G < 128) {
			gray = 3;
		} else if (G >= 128 && G < 160) {
			gray = 4;
		} else if (G >= 160 && G < 192) {
			gray = 5;
		} else if (G >= 192 && G < 224) {
			gray = 6;
		} else if (G >= 224 && G < 256) {
			gray = 7;
		}
		return gray;
	}
	/*
	 * 调用函数求纹理特征
	 */
	public static void insertDBwenli(int id,String name,BufferedImage bi,String path) throws SQLException{
		double []wenli=getwenli(bi);
		insertDB(id,name,wenli[0],wenli[1],wenli[2],wenli[3],wenli[4],wenli[5],wenli[6],wenli[7],path);
	}
	/*
	 * 图片的纹理特征
	 */
	public static double[] getwenli(BufferedImage bi) throws SQLException{
		
		int width=bi.getWidth();//图片宽度  
	    int height=bi.getHeight();//图片高度  
		int gray_matrix[][] = new int[width][height];
		int pix[]= new int [width*height];//像素个数  
	       int r,g,b;//记录R、G、B的值  
	       pix = bi.getRGB(0, 0, width, height, pix, 0, width);//将图片的像素值存到数组里  
	       int count=0;
	       for (int i = 0; i < width; i++) {
		    	for (int j = 0; j < height; j++) {
		    			int pixel=bi.getRGB(i, j); 
		    			r = (pixel & 0xff0000 ) >> 16 ; 
		    			g = (pixel & 0xff00 ) >> 8 ; 
		    			b = (pixel & 0xff ); 
		    			gray_matrix[i][j] = gray(r,g,b);	
			        	count++;
		    	}
	       }
	     /*
	      * 4个共生矩阵
	      */
		 for(int m=0;m<8;m++){
			   for(int n=0;n<8;n++){
				 
				   for(int i=0;i<width;i++){
					   for(int j=0;j<height;j++){
						
						   if(j+1<height&&gray_matrix[i][j]==m&&gray_matrix[i][j+1]==n){
							   co_matrix_0[m][n]=co_matrix_0[m][n]+1;
							   //co_matrix_0[n][m]=co_matrix_0[m][n];
						   }
						   if(i>1&&j+1<height&&gray_matrix[i][j]==m&&gray_matrix[i-1][j+1]==n){
							   co_matrix_45[m][n]=co_matrix_45[m][n]+1;
							  // co_matrix_45[n][m]=co_matrix_45[m][n];
						   }
						   if(i+1<width&&gray_matrix[i][j]==m&&gray_matrix[i+1][j]==n){
							   co_matrix_90[m][n]=co_matrix_90[m][n]+1;
							   //co_matrix_90[n][m]=co_matrix_90[m][n];
						   }
						   if(i+1<width&&j+1<height&&gray_matrix[i][j]==m&&gray_matrix[i+1][j+1]==n){
							   co_matrix_135[m][n]=co_matrix_135[m][n]+1;
							  // co_matrix_135[n][m]=co_matrix_135[m][n];
						   }
						
					   }
		   
				   }
		   
				   if(m==n){
					   co_matrix_0[m][n]=co_matrix_0[m][n]*2;
					   co_matrix_45[m][n]=co_matrix_45[m][n]*2;
					   co_matrix_90[m][n]=co_matrix_90[m][n]*2;
					   co_matrix_135[m][n]=co_matrix_135[m][n]*2;
			  
				   }
			   }
		 } 
		
		 
		
		  double sum1 = 0;
		  double sum2 = 0;
		  double sum3 = 0;
		  double sum4 = 0;
		  
		  for(int  i = 0;i < 8;i++){
			
				for( int  k = 0;k < 8;k++) {		
					sum1 = sum1 + co_matrix_0[i][k];				    
				 	sum2 = sum2 + co_matrix_45[i][k];								 	  
				 	sum3 = sum3 + co_matrix_90[i][k];			 	  
				 	sum4 = sum4 + co_matrix_135[i][k];		 	
			}
				
		  }
			//灰度共生矩阵归一化	
		
		  for(int p = 0;p <8;p++){			   
			  for(int q = 0;q <8;q++){		
				co_matrix_0[p][q]= co_matrix_0[p][q]/sum1;
				co_matrix_45[p][q]= co_matrix_45[p][q]/sum2;
				co_matrix_90[p][q]= co_matrix_90[p][q]/sum3;
				co_matrix_135[p][q]= co_matrix_135[p][q]/sum4;
		  }
		 }
		  
		 
		//相关性计算中使用
		double ux[]=new double[]{0,0,0,0};
		double uy[]=new double[]{0,0,0,0};
		double deltaX1[]=new double[]{0,0,0,0};
		double deltaY1[]=new double[]{0,0,0,0};
	
		double E1=0,E2=0,E3=0,E4=0;//能量
		double D1=0,D2=0,D3=0,D4=0;//熵
		double I1=0,I2=0,I3=0,I4=0;//惯性矩	
		
		double C[]=new double[]{0,0,0,0};
			for(int i=0;i<8;i++)
			{
				for(int k=0;k<8;k++)
				{
					E1=E1+Math.pow( co_matrix_0[i][k], 2);
					E2=E2+Math.pow( co_matrix_45[i][k], 2);
					E3=E3+Math.pow( co_matrix_90[i][k], 2);
					E4=E4+Math.pow( co_matrix_135[i][k], 2);
					if(co_matrix_0[i][k]==0){
						D1=D1+0;
					}
					else{
						D1=D1+(- co_matrix_0[i][k])*Math.log(co_matrix_0[i][k]);
					}
					if(co_matrix_45[i][k]==0){
						D2=D2+0;
					}
					else{
						D2=D2+(- co_matrix_45[i][k])*Math.log(co_matrix_45[i][k]);
						
					}
					if(co_matrix_90[i][k]==0){
						D3=D3+0;
					}
					else{
						D3=D3+(- co_matrix_90[i][k])*Math.log(co_matrix_90[i][k]);
					}
					if(co_matrix_135[i][k]==0){
						D4=D4+0;
					}
					else{
						D4=D4+(- co_matrix_135[i][k])*Math.log(co_matrix_135[i][k]);
					}
			
					I1=I1+Math.pow((i-k), 2)* co_matrix_0[i][k];
					I2=I2+Math.pow((i-k), 2)* co_matrix_45[i][k];
					I3=I3+Math.pow((i-k), 2)* co_matrix_90[i][k];
					I4=I4+Math.pow((i-k), 2)* co_matrix_135[i][k];
					
				
					
					ux[0] = i *co_matrix_0[i][k] + ux[0];			//相关性中的ux0
					uy[0] = k * co_matrix_0[i][k] + uy[0];			//相关性中的uy0		
					ux[1] = i *co_matrix_45[i][k] + ux[1];			//相关性中的ux1
					uy[1] = k * co_matrix_45[i][k] + uy[1];			//相关性中的uy1		
					ux[2] = i *co_matrix_90[i][k] + ux[2];			//相关性中的ux2
					uy[2] = k * co_matrix_90[i][k] + uy[2];			//相关性中的uy2		
					ux[3] = i *co_matrix_135[i][k] + ux[3];			//相关性中的ux3
					uy[3] = k * co_matrix_135[i][k] + uy[3];		//相关性中的uy3		
					
				
				}
					
		}
			
		for(int i=0;i<8;i++){
			for(int k=0;k<8;k++){
				deltaX1[0]+=Math.pow((i-ux[0]), 2)*co_matrix_0[i][k];
				deltaY1[0]+=Math.pow((k-uy[0]), 2)*co_matrix_0[i][k];
				deltaX1[1]+=Math.pow((i-ux[1]), 2)*co_matrix_45[i][k];
				deltaY1[1]+=Math.pow((k-uy[1]), 2)*co_matrix_45[i][k];
				deltaX1[2]+=Math.pow((i-ux[2]), 2)*co_matrix_90[i][k];
				deltaY1[2]+=Math.pow((k-uy[2]), 2)*co_matrix_90[i][k];
				deltaX1[3]+=Math.pow((i-ux[3]), 2)*co_matrix_135[i][k];
				deltaY1[3]+=Math.pow((k-uy[3]), 2)*co_matrix_135[i][k];
				C[0]+=i*k*co_matrix_0[i][k];
				C[1]+=i*k*co_matrix_45[i][k];
				C[2]+=i*k*co_matrix_90[i][k];
				C[3]+=i*k*co_matrix_135[i][k];
			}
		}
        if(deltaX1[0]==0||deltaY1[0]==0){
        	C[0]=(C[0]-ux[0]*uy[0]);
        }
        else{
        	C[0]=(C[0]-ux[0]*uy[0])/deltaX1[0]/deltaY1[0];
        }
        if(deltaX1[1]==0||deltaY1[1]==0){
        	C[1]=(C[1]-ux[1]*uy[1]);
        }
        else{
        	C[1]=(C[1]-ux[1]*uy[1])/deltaX1[1]/deltaY1[1];
        }
        if(deltaX1[2]==0||deltaY1[2]==0){
        	C[2]=(C[2]-ux[2]*uy[2]);
        }
        else{
        	C[2]=(C[2]-ux[2]*uy[2])/deltaX1[2]/deltaY1[2];
        }
        if(deltaX1[0]==0||deltaY1[0]==0){
        	C[3]=(C[3]-ux[3]*uy[3]);
        }
        else{
        	C[3]=(C[3]-ux[3]*uy[3])/deltaX1[3]/deltaY1[3];
        }
	
        /*
         * 求均值与标准差
         */
		double avageE=0;
		double deviE=0;
		double avageD=0;
		double deviD=0;
		double avageI=0;
		double deviI=0;
		double avageC=0;
		double deviC=0;
		avageE=(E1+E2+E3+E4)/4;
		deviE=(Math.sqrt(Math.pow((E1-avageE), 2))
				+Math.sqrt(Math.pow((E1-avageE), 2))
				+Math.sqrt(Math.pow((E1-avageE), 2))
				+Math.sqrt(Math.pow((E1-avageE), 2)))/4;
		avageD=(D1+D2+D3+D4)/4;
		deviD=(Math.sqrt(Math.pow((D1-avageD), 2))
				+Math.sqrt(Math.pow((D1-avageD), 2))
				+Math.sqrt(Math.pow((D1-avageD), 2))
				+Math.sqrt(Math.pow((D1-avageD), 2)))/4;
	
		avageI=(I1+I2+I3+I4)/4;
		deviI=(Math.sqrt(Math.pow((I1-avageI), 2))
				+Math.sqrt(Math.pow((I1-avageI), 2))
				+Math.sqrt(Math.pow((I1-avageI), 2))
				+Math.sqrt(Math.pow((I1-avageI), 2)))/4;
		for(int n=0;n<4;n++){
			avageC=avageI+C[n];
		}
		avageC=avageC/4;
		 
		deviC=(Math.sqrt(Math.pow((C[0]-avageC), 2))
				+Math.sqrt(Math.pow((C[1]-avageC), 2))
				+Math.sqrt(Math.pow((C[2]-avageC), 2))
				+Math.sqrt(Math.pow((C[3]-avageC), 2)))/4;
	
		
		
		
		double result[]=new double[8];
		result[0]=avageE;
		result[1]=deviE;
		result[2]=avageD;
		result[3]=deviD;
		result[4]=avageI;
		result[5]=deviI;
		result[6]=avageC;
		result[7]=deviC;
		return result;
			
	}
    /*
     * 插入数据库
     */
	public static void insertDB(int i,String name,double E,double deviE,double D,double deviD,double I,double deviI,double C,double deviC,String path) throws SQLException{
		System.out.println("111111113");
		Image_wenli_dao insertwenli=new Image_wenli_dao();		    	  		    	     
        insertwenli.insert(i, name, E, deviE, D, deviD, I, deviI, C, deviC, path);	
        System.out.println("111111114");
	}
		 	
}
