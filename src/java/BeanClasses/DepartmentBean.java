package BeanClasses;
public class DepartmentBean{
	
private int facId;
private int deptId;
private String deptName;
private String remarks;

 public  void setFacId(int facId){
 	this.facId=facId;
 }

public  void setDeptId(int deptId){
 	this.deptId=deptId;
 }

public  void setDeptName(String deptName){
 	this.deptName=deptName;
 }

public void setRemarks(String remarks){
    this.remarks=remarks;
}


public int getFacId(){
	return facId;
}

public int getDeptId(){
	return deptId;
}

public String getDeptName(){
	return deptName;
}

public String getRemarks(){
	return remarks;
}

public String toString(){
	return deptName;
}
}