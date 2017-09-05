package Iamge1;


import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;











import java.awt.Panel;
import java.awt.BorderLayout;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseListener;
//import org.eclipse.swt.events.MouseEvent;
//import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.swt.browser.Browser;

import Bean.*;
import Dao.Image_color_dao;
import Dao.Image_shape_dao;
import Dao.Image_wenli_dao;

import org.eclipse.swt.widgets.Text;

public class Image1 {
	
	
    private String path_tupian;
	protected Shell shell;
	private Image image=null;
	Label show9Lable[]=new Label[9];
	Text xiangsiText[]=new Text[9];
	String imagepath[]=null;
	
	float sub[]=null;
	/*
	 * 三种匹配保存最终匹配路径
	 */
	static String imagepath1[]=null;
	static String imagepath2[]=null;
	static String imagepath3[]=null;
	/*
	 * 三种匹配保存最终差距
	 */
	static double similar1[];
	static double similar2[];
	static double similar3[];
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtNewText_0;
	private Text txtNewText_1;
	private Text txtNewText_2;
	private Text txtNewText_3;
	private Text txtNewText_4;
	private Text txtNewText_5;
	private Text txtNewText_6;
	private Text txtNewText_7;
	private Text txtNewText_8;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Image1 window = new Image1();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		//image=new Image(Display.getDefault(),path_tupian);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.CLOSE | SWT.MIN);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setMinimumSize(new Point(200, 100));
		shell.setImage(SWTResourceManager.getImage(Image1.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shell.setSize(1100, 900);
		shell.setText("\u57FA\u4E8E\u56FE\u50CF\u5185\u5BB9\u7684\u68C0\u7D22");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		label.setBounds(10, 0, 103, 25);
		label.setText("\u8BF7\u9009\u62E9\u68C0\u7D22\u56FE\u7247");
		
		final List list = new List(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		list.setBounds(10, 26, 255, 386);
		formToolkit.adapt(list, true, true);
		
		Menu menu = new Menu(shell, SWT.BAR);
		
		shell.setMenuBar(menu);

		MenuItem fileItem=new MenuItem(menu,SWT.CASCADE);
		fileItem.setText("文件&(F)");
		
		Menu fileMenu=new Menu(shell,SWT.DROP_DOWN);
		fileItem.setMenu(fileMenu);	
		{
             //"打开"项
             MenuItem openFileItem = new MenuItem(fileMenu, SWT.CASCADE);
             openFileItem.addSelectionListener(new SelectionAdapter() {
             	@Override
             	public void widgetSelected(SelectionEvent e) {
             		DirectoryDialog dd=new DirectoryDialog(shell);  
            		dd.setMessage("选择图片库");  
            		dd.setText("打开图片库");  
            		dd.setFilterPath("C://");  
            		String saveFile=dd.open();  
            		int fileNum = 0, folderNum = 0;
            		String[] item = new String[500]; 
            		if(saveFile!=null){  
            		    File directiory=new File(saveFile);  
            			File[] files = directiory.listFiles();
            			
            			int i=0;
                        for (File file2 : files) {
                        	    item[i]=file2.getAbsolutePath();      
                        	   // list.setItems(item);
                        	    list.add(item[i]);
                                System.out.println("图片:" + file2.getAbsolutePath());
                                fileNum++;
                                i=i+1;
                        }
                        System.out.println("共有文件"+fileNum);                 
                       
            		}
            		
             	}
             });
             openFileItem.setText("打开&O");                         
             
             //"退出"项
             MenuItem exitItem = new MenuItem(fileMenu, SWT.CASCADE);
             exitItem.setText("退出&E");
             
            
             exitItem.addSelectionListener(new SelectionAdapter() {
             	@Override
             	public void widgetSelected(SelectionEvent e) {
             		shell.close();
             	}
             });
             
             
          }
        
		System.out.println("^^^^^^1");
		final Label lblNewLabel = new Label(shell, SWT.BORDER | SWT.HORIZONTAL | SWT.CENTER);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(10, 418, 255, 213);
		formToolkit.adapt(lblNewLabel, true, true);
	
		System.out.println("^^^^^^2");
		
		SelectionAdapter listener = new SelectionAdapter() {
			// 按钮单击事件处理的方法
			public void widgetSelected(SelectionEvent e) {
				 int index=list.getSelectionIndex();
				 if (index >= 0) {
					 path_tupian = list.getItem(index);
					 image=new Image(Display.getDefault(),path_tupian);
					 System.out.println("*****"+path_tupian);
					 lblNewLabel.setImage(image);							  
			    
				 }
			}
			};
			list.addSelectionListener(listener);
		
	
		
		MenuItem colorItem=new MenuItem(menu,SWT.CASCADE);
		colorItem.setText("颜色特征检索&(CS)");
		
		Menu colorMenu=new Menu(shell,SWT.DROP_DOWN);
		colorItem.setMenu(colorMenu);	
		{
		   
			MenuItem zhifangItem = new MenuItem(colorMenu, SWT.CASCADE);
			zhifangItem.setText("直方图匹配检索");
			zhifangItem.addSelectionListener(new SelectionAdapter(){
				@Override
				public void widgetSelected(SelectionEvent e) {
					Color color=new Color();
					BufferedImage bi;
					
				
					try {
						bi = ImageIO.read(new File(path_tupian));
				
						double zhifangrgb[][]=color.getzhifang(bi);				
						PiPeizhifang(zhifangrgb);		
						double xiangsi1[]=similar1;
						//System.out.println(xiangsi1);
						final String s1[]=new String[9];
						for(int i=0;i<9;i++){	
							s1[i]=xiangsi1[i]+"";
						}  
						for(int i=0;i<9;i++){	
							
							image=new Image(Display.getDefault(),imagepath1[i]);				
							show9Lable[i].setImage(image);	
							xiangsiText[i].setText(s1[i]);
					 
					
					
						}
					} catch (IOException e1) {		
						e1.printStackTrace();
					} catch (SQLException e1) {
				
						e1.printStackTrace();
					} 
				}
			});   
		}	
		
		MenuItem wenliItem=new MenuItem(menu,SWT.PUSH);
		wenliItem.setText("纹理特征检索&(VS)");
		
		wenliItem.addSelectionListener(new SelectionAdapter(){
        	@Override
         	public void widgetSelected(SelectionEvent e) {
        		
			Wenli wenli=new Wenli();
			 BufferedImage bi;
			try {
				bi = ImageIO.read(new File(path_tupian));			
				double wenliimage[]=wenli.getwenli(bi);					
				PiPeiwenli(wenliimage);	
				double xiangsi[]=similar2;
				final String s[]=new String[9];
			
				for(int i=0;i<9;i++){	
					s[i]=xiangsi[i]+"";
				}     
			
				for( int i=0;i<9;i++){	
				
					 image=new Image(Display.getDefault(),imagepath2[i]);				
					 show9Lable[i].setImage(image);	
					 xiangsiText[i].setText(s[i]);
				}
				  				
				
			} catch (IOException e1) {		
				e1.printStackTrace();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			} 
        	}
        });  
			
		MenuItem shapeItem=new MenuItem(menu,SWT.PUSH);
		shapeItem.setText("形状特征检索&(SS)");
		
		shapeItem.addSelectionListener(new SelectionAdapter(){
        	@Override
         	public void widgetSelected(SelectionEvent e) {
        		
			Shape shape=new Shape();
			 BufferedImage bi;
			try {
				bi = ImageIO.read(new File(path_tupian));			
				double shapeimage[]=shape.getshape(bi);		
				
				PiPeishape(shapeimage);	
				double xiangsi3[]=similar3;
				final String s3[]=new String[9];
			
				for(int i=0;i<9;i++){	
					s3[i]=xiangsi3[i]+"";
				}     
			
				for( int i=0;i<9;i++){	
				
					 image=new Image(Display.getDefault(),imagepath3[i]);				
					 show9Lable[i].setImage(image);	
					 xiangsiText[i].setText(s3[i]);
				}		
				
			} catch (IOException e1) {		
				e1.printStackTrace();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			} 
        	}
        });  
		
		
		
		
		
		
		MenuItem helpItem=new MenuItem(menu,SWT.PUSH);
		helpItem.setText("帮助&(H)");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		label_1.setBounds(293, 0, 68, 25);
		formToolkit.adapt(label_1, true, true);
		label_1.setText("\u5339\u914D\u7ED3\u679C");
		
		Label lblNewLabel_1 = new Label(shell, SWT.BORDER | SWT.SHADOW_NONE);
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setTouchEnabled(true);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setBounds(279, 26, 223, 194);
		formToolkit.adapt(lblNewLabel_1, true, true);
	
		
		Label lblNewLabel_2 = new Label(shell, SWT.BORDER);
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(554, 26, 223, 194);
		formToolkit.adapt(lblNewLabel_2, true, true);
		
		
		Label lblNewLabel_3 = new Label(shell, SWT.BORDER);
		lblNewLabel_3.setAlignment(SWT.CENTER);
		lblNewLabel_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_3.setBounds(833, 26, 223, 194);
		formToolkit.adapt(lblNewLabel_3, true, true);
		
		
		Label lblNewLabel_4 = new Label(shell, SWT.BORDER);
		lblNewLabel_4.setAlignment(SWT.CENTER);
		lblNewLabel_4.setBounds(279, 253, 223, 194);
		formToolkit.adapt(lblNewLabel_4, true, true);
	
		
		Label lblNewLabel_5 = new Label(shell, SWT.BORDER);
		lblNewLabel_5.setAlignment(SWT.CENTER);
		lblNewLabel_5.setBounds(554, 253, 223, 194);
		formToolkit.adapt(lblNewLabel_5, true, true);
	
		
		Label lblNewLabel_6 = new Label(shell, SWT.BORDER);
		lblNewLabel_6.setAlignment(SWT.CENTER);
		lblNewLabel_6.setBounds(833, 253, 223, 194);
		formToolkit.adapt(lblNewLabel_6, true, true);
	
		
		Label lblNewLabel_7 = new Label(shell, SWT.BORDER);
		lblNewLabel_7.setAlignment(SWT.CENTER);
		lblNewLabel_7.setBounds(279, 479, 223, 194);
		formToolkit.adapt(lblNewLabel_7, true, true);
	
		
		Label lblNewLabel_8 = new Label(shell, SWT.BORDER);
		lblNewLabel_8.setAlignment(SWT.CENTER);
		lblNewLabel_8.setBounds(564, 485, 223, 194);
		formToolkit.adapt(lblNewLabel_8, true, true);
	
		
		Label lblNewLabel_9 = new Label(shell, SWT.BORDER);
		lblNewLabel_9.setAlignment(SWT.CENTER);
		lblNewLabel_9.setBounds(833, 485, 223, 194);
		formToolkit.adapt(lblNewLabel_9, true, true);
		
		
		show9Lable[0]=lblNewLabel_1 ;
		show9Lable[1]=lblNewLabel_2 ;
		show9Lable[2]=lblNewLabel_3 ;
		show9Lable[3]=lblNewLabel_4 ;
		show9Lable[4]=lblNewLabel_5 ;
		show9Lable[5]=lblNewLabel_6 ;
		show9Lable[6]=lblNewLabel_7 ;
		show9Lable[7]=lblNewLabel_8 ;
		show9Lable[8]=lblNewLabel_9 ;
		
		Label label_2 = formToolkit.createLabel(shell, "\u5DEE\u8DDD\uFF1A", SWT.NONE);
		label_2.setBounds(289, 232, 61, 17);
		
		txtNewText_0 = formToolkit.createText(shell, "", SWT.NONE);
		txtNewText_0.setBounds(356, 229, 161, 23);
		
		Label label_3 = formToolkit.createLabel(shell, "\u5DEE\u8DDD\uFF1A", SWT.NONE);
		label_3.setBounds(554, 229, 61, 17);
		
		Label label_4 = formToolkit.createLabel(shell, "\u5DEE\u8DDD\uFF1A", SWT.NONE);
		label_4.setBounds(833, 230, 61, 17);
		
		Label label_5 = formToolkit.createLabel(shell, "\u5DEE\u8DDD\uFF1A", SWT.NONE);
		label_5.setBounds(289, 456, 61, 17);
		
		Label label_6 = formToolkit.createLabel(shell, "\u5DEE\u8DDD\uFF1A", SWT.NONE);
		label_6.setBounds(554, 456, 61, 17);
		
		Label label_7 = formToolkit.createLabel(shell, "\u5DEE\u8DDD\uFF1A", SWT.NONE);
		label_7.setBounds(833, 456, 61, 17);
		
		Label label_8 = formToolkit.createLabel(shell, "\u5DEE\u8DDD\uFF1A", SWT.NONE);
		label_8.setBounds(279, 685, 61, 17);
		
		Label label_9 = formToolkit.createLabel(shell, "\u5DEE\u8DDD\uFF1A", SWT.NONE);
		label_9.setBounds(554, 685, 61, 17);
		
		Label label_10 = formToolkit.createLabel(shell, "\u5DEE\u8DDD\uFF1A", SWT.NONE);
		label_10.setBounds(833, 685, 61, 17);
		
		txtNewText_1 = formToolkit.createText(shell, "", SWT.NONE);
		txtNewText_1.setBounds(635, 229, 170, 23);
		
		txtNewText_2 = formToolkit.createText(shell, "", SWT.NONE);
		txtNewText_2.setBounds(900, 229, 154, 23);
		
		txtNewText_3 = formToolkit.createText(shell, "", SWT.NONE);
		txtNewText_3.setBounds(356, 453, 161, 23);
		
		txtNewText_4 = formToolkit.createText(shell, "", SWT.NONE);
		txtNewText_4.setBounds(635, 450, 170, 23);
		
		txtNewText_5 = formToolkit.createText(shell, "", SWT.NONE);
		txtNewText_5.setBounds(900, 453, 154, 23);
		
		txtNewText_6 = formToolkit.createText(shell, "", SWT.NONE);
		txtNewText_6.setBounds(356, 685, 161, 23);
		
		txtNewText_7 = formToolkit.createText(shell, "", SWT.NONE);
		txtNewText_7.setBounds(635, 685, 161, 23);
		
		txtNewText_8 = formToolkit.createText(shell, "", SWT.NONE);
		txtNewText_8.setBounds(900, 684, 154, 23);
		
		xiangsiText[0]=txtNewText_0;
		xiangsiText[1]=txtNewText_1;
		xiangsiText[2]=txtNewText_2;
		xiangsiText[3]=txtNewText_3;
		xiangsiText[4]=txtNewText_4;
		xiangsiText[5]=txtNewText_5;
		xiangsiText[6]=txtNewText_6;
		xiangsiText[7]=txtNewText_7;
		xiangsiText[8]=txtNewText_8;
		
	}
	
	/*
	 * 形状匹配
	 */
	public static void PiPeishape(double [] shape) throws SQLException{
		Image_shape_dao imageshapedao=new Image_shape_dao();
		ArrayList shapeimagelist=(ArrayList) imageshapedao.selectshape();
		int n=shapeimagelist.size();
	     similar3=new double[n];
		imagepath3=new String[n];
	
		for(int i=0;i<n;i++){
			
		    similar3[i]=getsimilarshape(shape,(Image_shape)shapeimagelist.get(i));
		 	String path1=((Image_shape) shapeimagelist.get(i)).getPath();
		 	String name1=((Image_shape) shapeimagelist.get(i)).getName();
		     imagepath3[i]=path1+name1;
		 
		}
		
		for (int i = 0; i < n - 1; i++) {   
			  
		      for (int j = 0; j < n - 1; j++) {   	  
		        if (similar3[j] > similar3[j + 1]) {   		  
		          double temp = similar3[j];   
		          String temp1=imagepath3[j];
		          similar3[j] =  similar3[j + 1]; 
		          imagepath3[j]=imagepath3[j+1];
		          similar3[j + 1] = temp; 
		          imagepath3[j+1]=temp1;	  
		        }   	  
		     }   
		}
		
		
	}
	/*
	 * 形状匹配计算距离
	 */
public static double getsimilarshape(double [] shape,Image_shape imageshape){
		
		double sim []=new double[8];
		double similar=(double)0.0;//相似度      
        sim[0]+=(shape[0]-imageshape.getM1())*(shape[0]-imageshape.getM1());  
        sim[1]+=(shape[1]-imageshape.getM2())*(shape[1]-imageshape.getM2());  
        sim[2]+=(shape[2]-imageshape.getM3())*(shape[2]-imageshape.getM3());  
        sim[3]+=(shape[3]-imageshape.getM4())*(shape[3]-imageshape.getM4());  
        sim[4]+=(shape[4]-imageshape.getM5())*(shape[4]-imageshape.getM5());  
        sim[5]+=(shape[5]-imageshape.getM6())*(shape[5]-imageshape.getM6());  
        sim[6]+=(shape[6]-imageshape.getM7())*(shape[6]-imageshape.getM7()); 
        sim[7]+=(shape[7]-imageshape.getLixin())*(shape[7]-imageshape.getLixin()); 
                           
       for(int i=0;i<8;i++){
    	   sim[i]=Math.sqrt(sim[i]);  
    	   similar+=sim[i];
       }
        similar=similar/8;
        return similar; 
		
	}
	
	
	/*
	 * 纹理匹配
	 */
	public static void PiPeiwenli(double [] wenli) throws SQLException{
		Image_wenli_dao imagewenlidao=new Image_wenli_dao();
	
		
		ArrayList wenliimagelist=(ArrayList) imagewenlidao.selectwenli();
		int n=wenliimagelist.size();
	     similar2=new double[n];
		imagepath2=new String[n];
	
		for(int i=0;i<n;i++){
			
		    similar2[i]=getsimilarwenli(wenli,(Image_wenli)wenliimagelist.get(i));
		 	String path1=((Image_wenli) wenliimagelist.get(i)).getPath();
		 	String name1=((Image_wenli) wenliimagelist.get(i)).getName();
		     imagepath2[i]=path1+name1;
		 
		}
		
		for (int i = 0; i < n - 1; i++) {   
			  
		      for (int j = 0; j < n - 1; j++) {   	  
		        if (similar2[j] > similar2[j + 1]) {   		  
		          double temp = similar2[j];   
		          String temp1=imagepath2[j];
		          similar2[j] =  similar2[j + 1]; 
		          imagepath2[j]=imagepath2[j+1];
		          similar2[j + 1] = temp; 
		          imagepath2[j+1]=temp1;	  
		        }   	  
		     }   
		}
		
		
	}
	/*
	 * 纹理匹配计算距离
	 */
	public static double getsimilarwenli(double [] wenli,Image_wenli imagewenli){
		
		double sim []=new double[8];
		double similar=(double)0.0;//相似度      
                sim[0]+=(wenli[0]-imagewenli.getE())*(wenli[0]-imagewenli.getE());  
                sim[1]+=(wenli[1]-imagewenli.getDeviE())*(wenli[1]-imagewenli.getDeviE());  
                sim[2]+=(wenli[2]-imagewenli.getD())*(wenli[2]-imagewenli.getD());  
                sim[3]+=(wenli[3]-imagewenli.getDeviD())*(wenli[3]-imagewenli.getDeviD());  
                sim[4]+=(wenli[4]-imagewenli.getI())*(wenli[4]-imagewenli.getI());  
                sim[5]+=(wenli[5]-imagewenli.getDeviI())*(wenli[5]-imagewenli.getDeviI());  
                sim[6]+=(wenli[6]-imagewenli.getC())*(wenli[6]-imagewenli.getC());  
                sim[7]+=(wenli[7]-imagewenli.getDeviC())*(wenli[7]-imagewenli.getDeviC());  
             
       for(int i=0;i<8;i++){
    	   sim[i]=Math.sqrt(sim[i]);  
    	   similar+=sim[i];
       }
        similar=similar/8;
        return similar; 
		
	}
	/*
	 * 直方图颜色匹配
	 */
	public static void PiPeizhifang(double zhifangrgb[][]) throws SQLException{
		
		Image_color_dao imagecolordao=new Image_color_dao();
		ArrayList zhifangimagelist=(ArrayList) imagecolordao.selectColor();
		
		int n=zhifangimagelist.size();
		 imagepath1=new String[n];
		 similar1=new double[n];
		
		double zhifangrgb2[][]=new double[3][256];
		for(int i=0;i<n;i++){
			 Color color=new Color();
			 BufferedImage bi;
			try {
			   String path=((Image_color) zhifangimagelist.get(i)).getColor();
			  // System.out.println("path:"+path);
			   FileInputStream fisr=new FileInputStream(path);
			   String encoding="GBK";//设置读取文件的编码格式            
               InputStreamReader isr=new InputStreamReader(fisr,encoding);//封装文件输入流，并设置编码方式              
               BufferedReader br=new BufferedReader(isr);
               
               String txt=null;          
               txt=br.readLine();          
               String[] line = txt.split(" ");  
            
                   //按行读取文件，每次读取一行
            	 
            	while(txt!=null){
            			for(int k=0;k<256;k++){ 
            				 for(int j=0,p=0;j<3&&p<line.length;j++,p++){
            						 zhifangrgb2[j][k]=Double.parseDouble(line[p]);
            					 	//.out.print(k+":"+zhifangrgb[j][k]+" ");
            				 }
            		        txt=br.readLine();
            		        if(txt!=null){
            		        line = txt.split(" ");              		                    		   	
            	      }
            		       // System.out.println();
            			} 
            	   }
				 similar1[i]=GetSimilarityzhifang(zhifangrgb,zhifangrgb2);
				 imagepath1[i]=((Image_color) zhifangimagelist.get(i)).getPath()+((Image_color) zhifangimagelist.get(i)).getName();
				// System.out.print("***path1:"+imagepath1[i]);
			} catch (IOException e1) {				
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		for (int i = 0; i < n - 1; i++) {   
			  
		      for (int j = 0; j < n - 1; j++) {   	  
		        if (similar1[j] > similar1[j + 1]) {   		  
		          double temp = similar1[j];   
		          String temp1=imagepath1[j];
		          similar1[j] =  similar1[j + 1]; 
		          imagepath1[j]=imagepath1[j+1];
		          similar1[j + 1] = temp; 
		          imagepath1[j+1]=temp1;	  
		        }   	  
		     }   
		}
		
	}
	
	 //直方图颜色距离  
    public static double GetSimilarityzhifang(double [][] Rhistgram,double  [][] Dhistgram)  
    {  
          double similar=(double)0.0;//相似度  
    
          for(int i=0;i<3;i++)  
          {  
              for(int j=0;j<Rhistgram[i].length;j++)  
              {  
                  similar+=(Rhistgram[i][j]-Dhistgram[i][j])*(Rhistgram[i][j]-Dhistgram[i][j]);  
              }  
          }  
          similar=Math.sqrt(similar);  
          similar=similar/3;  
          return similar; 
    }  
}

