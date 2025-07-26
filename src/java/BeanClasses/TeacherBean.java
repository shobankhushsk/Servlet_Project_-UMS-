package BeanClasses;
public class TeacherBean{
	private int  deptId;
    private int  teacherId;
	private String teacherName;
	private String fName;
	private String surName;
        private String dateOfBirth;
        private String  nic;
	private String email;
	private String   contactNo;
	private String  dateOfAppointment;
	private String  designationAtTheOfAppointment;
	private String  currentDesignation;
	private String remarks;



	public void setDeptId(int deptId){
		this.deptId=deptId;
	}

	public void setTeacherId(int teacherId){
		this.teacherId=teacherId;
	}

	public void setTeacherName(String teacherName){
		this.teacherName=teacherName;
	}

	public void setFname(String fName){
		this.fName=fName;
	}

	public void setSurName(String surName){
     this.surName=surName;
	}

  public void setDataOfBirth(String dateOfBirth){
	this.dateOfBirth=dateOfBirth;
 }

  public void setNic(String  nic){
  	this.nic=nic;
  }

  public void setEmail(String email){
	this.email=email;
 }
   
  public void setContactNo(String  contactNo){
  	this.contactNo=contactNo;
  }

  public void setDateOfAppointment(String dateOfAppointment){
  	this.dateOfAppointment=dateOfAppointment;
  }

  public void setDesignationAppointment(String designationAtTheOfAppointment){
  	this.designationAtTheOfAppointment=designationAtTheOfAppointment;
  }

  public void setCurrentDesignation(String currentDesignation){
  	this.currentDesignation=currentDesignation;
  }

   public void setRemarks(String remarks){
  	this.remarks=remarks;
  }

  

  	
public int  getDeptId(){
		return deptId;
	}

	public int  getTeacherId(){
		return teacherId;
	}

	public String getTeacherName(){
		return teacherName;
	}

	public String getFname(){
		return fName;
	}

	public String getSurName(){
     return surName;
	}

  public String getDataOfBirth(){
	return dateOfBirth;
 }

  public String  getNic(){
  	return nic;
  }

  public String getEmail(){
	return email;
 }
   
  public String getContactNo(){
  	return contactNo;
  }

  public String getDateOfAppointment(){
  	return dateOfAppointment;
  }

  public String getDesignationAppointment(){
  	return designationAtTheOfAppointment;
  }

  public String getCurrentDesignation(){
  	return currentDesignation;
  }

   public String getRemarks(){
  	return remarks;
  }


public String toString(){
	return teacherName;
}
}