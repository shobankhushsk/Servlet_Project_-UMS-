package BeanClasses;

public class PartBean{
	
    private int  batchId;
	private int  part;
	private String batchYear;
	private String remarks;
	



	public void setBatchId(int batchId){
		this.batchId=batchId;
	}
	
	public void setPart(int part){
		this.part=part;
	}

	public void setBatchYear(String batchYear){
		this.batchYear=batchYear;
	}
	
   public void setRemarks(String remarks){
   	this.remarks=remarks;
   }

	 public int  getBatchId(){
	 	return batchId;
	 }

	 public int  getPart(){
	 	return part;
	 } 

	 public String  getBatchYear(){
	 	return batchYear;
	 }

	 public String getRemarks(){
	 	return remarks; 
	 }

	public String toString(){
		return ""+part;
	}






}