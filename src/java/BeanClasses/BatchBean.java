package BeanClasses;
public class  BatchBean{

	private int progId;
        private int  batchId;
	private String shift;
	private String batchYear;
	private String groupDesc;
	private String remarks;
	


	public void setProgId(int progId){
		this.progId=progId;
	}

	public void setBatchId(int batchId){
		this.batchId=batchId;
	}
	
	public void setShift(String shift){
		this.shift=shift;
	}

	public void setBatchYear(String batchYear){
		this.batchYear=batchYear;
	}

	public void setGroupDesc(String groupDesc){
		this.groupDesc=groupDesc;
	}

   public void setRemarks(String remarks){
   	this.remarks=remarks;
   }


 public int  getProgId(){
 	return progId;
 }

 public int  getBatchId(){
 	return batchId;
 }

 public String getShift(){
 	return shift;
 } 

 public String  getBatchYear(){
 	return batchYear;
 }

 public String getGroupDesc(){
 	return groupDesc;
 }


 public String getRemarks(){
 	return remarks; 
 }

public String toString(){
	return ""+batchYear;
}


}