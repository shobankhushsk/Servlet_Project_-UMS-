package BeanClasses;
public class StudentsBean{

    private int  batchId;
    private int  studId;
	private String studName;
	private String fName;
	private String surName;
	private String rollNo;
	private String remarks;
	private String cNic;
	private String  phoneNo;
	private String email;
	private String dateOfBirth;



	public void setBatchId(int batchId){
		this.batchId=batchId;
	}

	public void setStudId(int studId){
		this.studId=studId;
	}

	public void setStudName(String studName){
		this.studName=studName;
	}

	public void setFname(String fName){
		this.fName=fName;
	}

	public void setSurName(String surName){
     this.surName=surName;
	}

   public void setRollNo(String rollNo){
   	this.rollNo=rollNo;
   } 	 
  
   public void setRemarks(String remarks){
  	this.remarks=remarks;
  }

   public void setCnic(String cNic){
  	this.cNic=cNic;
  }

  public void setPhoneNo(String phoneNo){
  	this.phoneNo=phoneNo;
  }

  public void setEmail(String email){
	this.email=email;
 }
	
  public void setDataOfBirth(String dateOfBirth){
	this.dateOfBirth=dateOfBirth;
 }



  public int  getBatchId(){
	return batchId;
 }

  public int getStudId(){
		return studId;
	}

  public String getStudName(){
        return studName;
  }


public String getFname(){
	return fName;
}

public String getSurName(){
     return surName;
}

  public String getRollNo(){
   	 return rollNo;
  } 	 
  
  public String getRemarks(){
  	return remarks;
  }

  public String getCnic(){
  	return cNic;
  }

  public String  getPhoneNo(){
  	return phoneNo;
  }

 public String getEmail(){
	return email;
 }
	
public String getDetaOfBirth(){
	return dateOfBirth;
}


public String toString(){
	return rollNo;
}




}