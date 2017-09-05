package Iamge1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import Dao.Image_shape_dao;
import Dao.Image_wenli_dao;

public class Shape {
	
	
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
		    
			File fs = fa[i];
			if (fs.isDirectory()) {
	             System.out.println(fs.getName() + " [目录]");
			} else {
	    	   try{
	    	    bi = ImageIO.read(fs);     
	    	      System.out.print(fs.getName() + " ");
	    	     //   getshape(bi) ;  
	    	        
	    	        insertDBshape(i,fs.getName(),bi,path); 
	             }catch(Exception e){           	 
	            	 System.out.println(e.getMessage());
	             }  
			}
		}
	} 
	/*
	 * 调用求特征值的函数
	 */
	public static void insertDBshape(int id,String name,BufferedImage bi,String path) throws SQLException{
		double []ju=getshape(bi);
		insertDB(id,name,ju[0],ju[1],ju[2],ju[3],ju[4],ju[5],ju[6],ju[7],path);
	}
	/*
	 * 插入数据库
	 */
	public static void insertDB(int i,String name,double M1,double M2,double M3,double M4,double M5,double M6,double M7,double lixin,String path) throws SQLException{
		System.out.println("111111113");
		Image_shape_dao insertshape=new Image_shape_dao();		    	  		    	     
        insertshape.insert(i, name, M1, M2, M3, M4, M5, M6, M7,lixin,path);	
        System.out.println("111111114");
	}
	/*
	 * 获取形状特征
	 */
	public static double [] getshape(BufferedImage bi){

		int width=bi.getWidth();//图片宽度  
	    int height=bi.getHeight();//图片高度  
		double gray_matrix[][] = new double[width][height];
	    int r,g,b;//记录R、G、B的值  
	   
	  
		 for (int i = 0; i < width; i++) {
		    	for (int j = 0; j < height; j++) {
		    			int pixel=bi.getRGB(i, j); 
		    			r = (pixel & 0xff0000 ) >> 16 ; 
		    			g = (pixel & 0xff00 ) >> 8 ; 
		    			b = (pixel & 0xff ); 
		    			gray_matrix[i][j] = r * 0.3 + g * 0.59 + b * 0.11;	
		    			
		    	}
		    	
		 }
	    
	    double medianfilter[][]=getmedianFiltering(gray_matrix,width,height);	  
	    double prewitt[][]=prewitt(medianfilter,width,height);
	    double erzhi[][]=twoerzhi(prewitt,width,height);
	    double ju[]=getju(erzhi,width,height);  
	    
	    return ju;
	}
	 
    /*
     * 获取7个不变矩与离心率
     */
	public static double [] getju(double erzhi[][],int w,int h){
		
		double x, y; //重心
		double M[] = new double [8];//HU不变矩和离心率
		double m00 = 0, m01 = 0, m10 = 0,
		       m20 = 0, m02 = 0, m11 = 0;//0阶矩、1阶矩和2阶矩：普通矩
		double u20 = 0, u02 = 0, u11 = 0,
			   u30 = 0, u03 = 0, u12 = 0, u21 = 0;//2阶矩和3阶矩：中心距
		double mu20, mu02, mu11, mu30, mu03, mu12, mu21;//规范化后的中心矩
	    
		//图像的区域重心(通过0阶、1阶普通矩求得，2阶普通矩用来计算离心率)
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				m00 += erzhi[i][j];
				m10 += i * erzhi[i][j];
				m01 += j * erzhi[i][j];
				m20 += i * i * erzhi[i][j];
				m02 += j * j * erzhi[i][j];
				m11 += i * j * erzhi[i][j];
			}
		}
		x = m10 / m00;
		y = m01 / m00;
		
		//2阶和3阶矩(中心矩)
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				u20 += Math.pow(i - x, 2) * erzhi[i][j];
				u02 += Math.pow(j - y, 2) * erzhi[i][j];
				u11 += (i - x) * (j - y) * erzhi[i][j];
				u30 += Math.pow(i - x, 3) * erzhi[i][j];
				u03 += Math.pow(j - y, 3) * erzhi[i][j];
				u12 += (i - x) * Math.pow(j - y, 2) * erzhi[i][j];
				u21 += Math.pow(i - x, 2) * (j - y) * erzhi[i][j];			
			}
		}
	    //规范化后的中心矩: uij/pow(m00,((i+j)/2+1)
		mu20 =  (u20 / Math.pow(m00, (2 + 0) / 2.0 + 1));
		mu02 =  (u02 / Math.pow(m00, (0 + 2) / 2.0 + 1));
		mu11 =  (u11 / Math.pow(m00, (1 + 1) / 2.0 + 1));
		mu30 =  (u30 / Math.pow(m00, (3 + 0) / 2.0 + 1));
		mu03 =  (u03 / Math.pow(m00, (0 + 3) / 2.0 + 1));
		mu12 =  (u12 / Math.pow(m00, (1 + 2) / 2.0 + 1));
		mu21 =  (u21 / Math.pow(m00, (2 + 1) / 2.0 + 1));
	    //不变矩M1-M7
		M[0] = (mu20 + mu02);
		M[1] = ((mu20 - mu02) * (mu20 - mu02) + 4 * mu11 * mu11);
		M[2] = (mu30 - 3 * mu12) * (mu30 - 3 * mu12)
			 + (mu03 - 3 * mu21) * (mu03 - 3 * mu21);
		M[3] = (mu30 + mu12) * (mu30 + mu12) + (mu03 + mu21) * (mu03 + mu21);
		M[4] = (mu30 - 3 * mu12) * (mu30 + mu12) * ((mu30 + mu12)* (mu30 + mu12)
				- 3 *(mu21 + mu03) * (mu21 + mu03))  + (3 * mu21 - mu03) * (mu21 + mu03)
				* (3 * (mu30 + mu12) * (mu30 + mu12) - (mu21 + mu03) * (mu21 + mu03));
		M[5] = (mu20 - mu02) * ((mu30 + mu12) * (mu30 + mu12) - (mu21 + mu03) * (mu21 + mu03))
				+ 4 * mu11 * (mu30 + mu12) * (mu21 + mu03);
		M[6] = (3 * mu21 + mu03) * (mu30 * mu12) * ((mu30 + mu12) * (mu30 + mu12) - 3 * (mu21 + mu03)
				* (mu21 + mu03))+ (mu30 - 3 * mu12) * (mu21 + mu30) * (3 * (mu30 + mu12) * (mu30 + mu12)
				- (mu21 + mu03) * (mu21 + mu03));
		//离心率e
		M[7] = ((m20 - m02) * (m20 - 02) + 4 * m11 * m11) / ((m20 + m02) * (m20 + 02));
		
		return M;
	     
	}
	/*
	 * Sobel算子锐化
	 */
	public static double[][] prewitt(double medianfilter[][],int w,int h){
		double[][] result=new double[w][h];
		for(int j=1;j<h-1;j++){
			for(int i=1;i<w-1;i++){
				double sx=2*medianfilter[i+1][j]+medianfilter[i+1][j-1]+medianfilter[i+1][j+1]
						-2*medianfilter[i-1][j]-medianfilter[i-1][j-1]-medianfilter[i-1][j+1];
				
				double sy=2*medianfilter[i][j+1]+medianfilter[i-1][j+1]+medianfilter[i+1][j+1]
						-2*medianfilter[i][j-1]-medianfilter[i-1][j-1]-medianfilter[i+1][j-1];
				
				double s=Math.abs(sx)+Math.abs(sy);
				
				if(s>255){
					s=255;
				}
				if(s<255){
					s=0;
				}
				
				result[i][j]=s;
				
			}
		}
		return result;
		
	}
	/*
	 * 迭代阈值法二值化
	 */
	public static double [] []twoerzhi(double prewitt[][],int w,int h){
		
		
		double result[][]=new double[w][h];		
		double Gmax = prewitt[0][0], Gmin = prewitt[0][0];
		for (int i = 0; i < w ; i++) {
		      for(int j=0;j<h;j++){        	 
		          if (prewitt[i][j] > Gmax) {
		                Gmax = prewitt[i][j];
		          }
		          if (prewitt[i][j] < Gmin) {
		                Gmin = prewitt[i][j];
		           }
		       }
		 }

	
		 double t1 = (Gmax + Gmin) / 2;//初始阈值
		
		 double t2=0;
		 boolean flage=true;
		 do{
		    double G1[]=new double[w*h];
			double G2[]=new double[w*h];
			int i1=0;
			int j1=0;
			double avageG1=0;
			double avageG2=0;
		    //for(int i=0;i<w;i++){
				//for(int j=0;j<h;j++){
			for(int i=0;i<w;i++){
				for(int j=0;j<h;j++){
					if(prewitt[i][j]<t1){
						G1[i1]=prewitt[i][j];
						i1++;				
					}
					if(prewitt[i][j]>t1){
						G2[j1]=prewitt[i][j];
						j1++;
					}		
				}
			} 
		    for(int m=0;m<i1-1;m++){
				avageG1+=G1[m];
			}	
			avageG1=avageG1/(i1-1);	
			for(int n=0;n<j1-1;n++){
				avageG2+=G2[n];
			}
			avageG2=avageG2/(j1-1);
	        t2=(avageG1+avageG2)/2;
	        
	     
	       if(Math.abs(t1-t2)<2){
					flage=false;					
			}
	       else{
					t1=t2;
		   }
	      // System.out.println("t1:"+t1+"           t2:"+t2);		
		 } while(flage);
	     
		
		 for(int i=0;i<w;i++){
				for(int j=0;j<h;j++){
					if(prewitt[i][j]<t2){
						result[i][j]=0;	
					}
					if(prewitt[i][j]>t2){
						result[i][j]=255;
					}
					
				}
			}
		
		 return result;
		
	}
	
	/*
	 * 中值滤波平滑
	 */
	  static double [][] getmedianFiltering(double gray[][],int w,int h){
		double temp[]=new double[9];
		double result[][]=new double[w][h];
		for(int j=0;j<h;j++){
			for(int i=0;i<w;i++){
				   if(i!=0 && i!=w-1 && j!=0 && j!=h-1) {  
				        temp[0]=gray[i-1][j-1];
				        temp[1]=gray[i][j-1];
				        temp[2]=gray[i+1][j-1];
				        temp[3]=gray[i-1][j];
				        temp[4]=gray[i][j];
				        temp[5]=gray[i+1][j];
				        temp[6]=gray[i-1][j+1];
				        temp[7]=gray[i][j+1];
				        temp[8]=gray[i+1][j+1];
				        Arrays.sort(temp);
				        
				        result[i][j]=temp[4];
				        
				   }
				   else{
					   result[i][j]=gray[i][j];
				   }
				
			}
		}
		return result;
	}

}
