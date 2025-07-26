package BeanClasses;

public class FacultyBean{
	
 private int facId;
 private String facName;
 private String remarks;

 public void setFacId(int facId){
 	this.facId=facId;
 }
 
 public void setFacName(String facName){
 	this.facName=facName;
 }

public void setRemarks(String remarks){
    this.remarks=remarks;
}


 public int getFacId(){
 	return facId;
 }

 public String getFacName(){
 	return facName;
 }

 public String getRemarks(){
 	return remarks;
 }

 public String toString(){
 	return facName;
 } 

}