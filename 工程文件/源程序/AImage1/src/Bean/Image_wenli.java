package Bean;

import java.sql.ResultSet;
import java.sql.SQLException;



public class Image_wenli{
	private int id;
	private String name;
	private Double E,DeviE,D,DeviD,I,DeviI,C,DeviC;
	private String path;
	
	public int getId()
	  {
	    return id;
	  }
	 
	  public void setId(int id)
	  {
	    this.id = id;
	  }
	 
	  public String getName()
	  {
	    return name;
	  }
	 
	  public void setName(String name)
	  {
	    this.name = name;
	  }
	  
	  public void setE(double E){
			 this.E=E;
		 }
     public double getE(){
			 return E;
		 }
	 public void setDeviE(double DeviE){
		 this.DeviE=DeviE;
	 }
	 public double getDeviE(){
		 return DeviE;
	 }
	 public void setD(double D){
		 this.D=D;
	 }
	 public double getD(){
		 return D;
	 }
	
	 public void setDeviD(double DeviD){
		 this.DeviD=DeviD;
	 }
	 public double getDeviD(){
		 return DeviD;
	 }
	 public void setI(double I){
		 this.I=I;
	 }
	 
	 public double getI(){
		 return I;
	 }
	 public double getDeviI(){
		 return DeviI;
	 }
	 public void  setDeviI(double DeviI){
		 this.DeviI=DeviI;
	 }
	
	 public double getC(){
		 return C;
	 }
	 public void setC(double C){
		 this.C=C;
	 }
	 public double getDeviC(){
		 return DeviC;
	 }
	 public void setDeviC(double DeviC ){
		 this.DeviC=DeviC;
	 }
	
	  public String getPath()
	  {
	    return path;
	  }
	  public void setPath(String path){
		  this.path=path;
	  }
}

