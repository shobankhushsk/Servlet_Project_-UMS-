package BeanClasses;
public class ProgramsBean{

	private int deptId;
	private int progId;
	private String progName;
	private String progDuration;
	private String remarks;


 public void setDeptId(int deptId){
 	this.deptId=deptId;
 }

 public void setProgId(int progId){
 	this.progId=progId;
 } 


public void setProgName(String progName){
 this.progName=progName;
}

public void setProgDuration(String progDuration){
	this.progDuration=progDuration;
} 

public void setRemarks(String remarks){
	this.remarks=remarks;
}


public int  getDeptId(){
	return deptId;
}

public int  getProgId(){
	return progId;
}

public String getProgName(){
	return progName;
}

public String  getProgDuration(){
 return progDuration;
}

public String getRemarks(){
  return remarks;
}

public String toString(){
	return progName;
}

}
