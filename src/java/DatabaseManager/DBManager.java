package DatabaseManager;

import BeanClasses.*;
import java.sql.*;
import java.util.Vector;


public class DBManager {

    private static Connection con;
    private static Statement st=null;
    private static ResultSet rs =null; 
    
    static{
       try{
            init(); 
       }catch(Exception e){
           e.printStackTrace();
       }
       
    }
    
    
     public static String shiftDecode(String shift){

	switch(shift){
	case "M":return "MORNING";
	case "E":return "EVENING";
	case "N":return "NOON"; 	
	}
	return shift;
	}


public static String groupDecode(String group){

	switch (group) {
          case "M":return "MEDICAL";
          case "E":return "ENGINEERING";
          case "C":return "COMMERCE";
          case "G":return "GENERAL"; 			
 
	}
        
	return group;
}

  public static String shiftEncode(String shift){

	switch(shift){
	case "MORNING":return "M";
	case "EVENING":return "E";
	case "NOON":return "N"; 	
	}
	return shift;
	}


public static String groupEncode(String group){

	switch (group) {
           case"MEDICAL":return "M";
          case"ENGINEERING":return "E";
          case"COMMERCE":return "C";
          case"GENERAL":return "G"; 			
 
	}
	return group;
}



    private static void init()throws Exception{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        // Connect to the database
        String dbPath = "E:\\NetBeansProjects\\WebProject_SindhUni\\src\\java\\DatabaseManager\\UniversityOfSindh.accdb";
        String url = "jdbc:ucanaccess://" + dbPath;
        con = DriverManager.getConnection(url);
        System.out.println("Connection successfully ");
    }
    
    
    public static Vector getFaculty()throws Exception{
       try{
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM faculty");
           
            Vector v = new Vector();
         
            while(rs.next()){
            FacultyBean bean=new FacultyBean();
            
            bean.setFacId(rs.getInt("FAC_ID"));
            bean.setFacName(rs.getString("FAC_NAME"));
            bean.setRemarks(rs.getString("REMARKS"));
          
            v.addElement(bean);
          }//end of while
           return v;
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }//end of finally 
    }//end of getfaculty Method
    
    
public static Vector getDepartment() throws Exception{
  
        try{
             st = con.createStatement();
             rs = st.executeQuery("SELECT * FROM department");
             Vector v = new Vector();
           while(rs.next()){
             DepartmentBean bean=new DepartmentBean();
             
             bean.setFacId(rs.getInt("FAC_ID"));
             bean.setDeptId(rs.getInt("DEPT_ID"));
             bean.setDeptName(rs.getString("DEPT_NAME"));
             bean.setRemarks(rs.getString("REMARKS"));
            
             v.addElement(bean);
           }//end of while
            return v; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// end of finally
   
}///end of getDepartment Method



public static Vector getDepartment(int facId) throws Exception{

        String query="SELECT * FROM department where FAC_ID="+facId;
        System.out.println(query);  
        try{
             st = con.createStatement();
             rs = st.executeQuery(query);
             Vector v = new Vector();
           while(rs.next()){
             DepartmentBean bean=new DepartmentBean();
             bean.setFacId(rs.getInt("FAC_ID"));
             bean.setDeptId(rs.getInt("DEPT_ID"));
             bean.setDeptName(rs.getString("DEPT_NAME"));
             bean.setRemarks(rs.getString("REMARKS"));
            
             v.addElement(bean);
           }//end of while
            return v; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// emd of finally
   
}///end of getDepartment Method

public static Vector  getProgram()throws Exception{
    
 String query="SELECT * FROM programs";
        System.out.println(query);  
        try{
             st = con.createStatement();
             rs = st.executeQuery(query);
             Vector v = new Vector();
           while(rs.next()){
             ProgramsBean bean=new ProgramsBean();
             bean.setDeptId(rs.getInt("DEPT_ID"));
             bean.setProgId(rs.getInt("PROG_ID"));
             bean.setProgName(rs.getString("PROG_NAME"));
            bean.setProgDuration(rs.getString("PROG_DURATION_IN_SEM"));
             bean.setRemarks(rs.getString("REMARKS"));
            
             v.addElement(bean);
           }//end of while
            return v; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// emd of finally


}/// end of getProgram Method  


public static Vector  getProgram(int dept)throws Exception{
    
         String query="SELECT * FROM programs where DEPT_ID="+dept;
         System.out.println(query);  
        try{
             st = con.createStatement();
             rs = st.executeQuery(query);
             Vector v = new Vector();
           while(rs.next()){
             ProgramsBean bean=new ProgramsBean();
             bean.setDeptId(rs.getInt("DEPT_ID"));
             bean.setProgId(rs.getInt("PROG_ID"));
             bean.setProgName(rs.getString("PROG_NAME"));
            bean.setProgDuration(rs.getString("PROG_DURATION_IN_SEM"));
             bean.setRemarks(rs.getString("REMARKS"));
            
             v.addElement(bean);
           }//end of while
            return v; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// emd of finally


}/// end of getProgram Method  


public static Vector  getBatch()throws Exception{
    
   
        try{
             st = con.createStatement();
             rs = st.executeQuery("SELECT * FROM batch");
             Vector v = new Vector();
           while(rs.next()){
             BatchBean bean=new BatchBean();
             bean.setProgId(rs.getInt("PROG_ID"));
             bean.setBatchId(rs.getInt("BATCH_ID"));
             bean.setShift(rs.getString("SHIFT"));
             bean.setBatchYear(rs.getString("BATCH_YEAR"));
             bean.setGroupDesc(rs.getString("GROUP_DES"));
             bean.setRemarks(rs.getString("REMARKS"));
            
             v.addElement(bean);
           }//end of while
            return v; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// emd of finally


}/// end of getBatch Method  

public static Vector  getBatch(int progId)throws Exception{
    
 
        try{
             st = con.createStatement();
             rs = st.executeQuery("SELECT * FROM batch where PROG_ID="+progId);
             Vector v = new Vector();
           while(rs.next()){
             BatchBean bean=new BatchBean();
             bean.setProgId(rs.getInt("PROG_ID"));
             bean.setBatchId(rs.getInt("BATCH_ID"));
             bean.setShift(rs.getString("SHIFT"));
             bean.setBatchYear(rs.getString("BATCH_YEAR"));
             bean.setGroupDesc(rs.getString("GROUP_DES"));
             bean.setRemarks(rs.getString("REMARKS"));
            
             v.addElement(bean);
           }//end of while
            return v; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// emd of finally


}/// end of getBatch Method  

 
public static StudentsBean  getStudents(String StudId)throws Exception{
    
 String query="SELECT * FROM students WHERE STUD_ID="+StudId;
        System.out.println(query);
        try{
             st = con.createStatement();
             rs = st.executeQuery(query);
             StudentsBean bean = null;
           if(rs.next()){
             bean = new StudentsBean();
             bean.setBatchId(rs.getInt("BATCH_ID"));
             bean.setStudId(rs.getInt("STUD_ID"));
             bean.setStudName(rs.getString("STUD_NAME"));
             bean.setFname(rs.getString("FATHER_NAME"));
             bean.setSurName(rs.getString("SURNAME"));
             bean.setRollNo(rs.getString("ROLL_NO"));
             bean.setRemarks(rs.getString("REMARKS"));
             bean.setCnic(rs.getString("CNIC"));
             bean.setPhoneNo(rs.getString("PHONE_NO"));
             bean.setEmail(rs.getString("EMAIL"));
             bean.setDataOfBirth(rs.getString("DATE_OF_BIRTH"));
           }//end of while
            return bean; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// emd of finally


}/// end of getStudents Method


public static Vector  getStudent(int batchId)throws Exception{
    
 
        try{
            String query = "SELECT * FROM students where BATCH_ID="+batchId;
             st = con.createStatement();
             rs = st.executeQuery(query);
             System.out.println(query);
             Vector v = new Vector();
           while(rs.next()){
             StudentsBean bean=new StudentsBean();
             bean.setBatchId(rs.getInt("BATCH_ID"));
             bean.setStudId(rs.getInt("STUD_ID"));
             bean.setStudName(rs.getString("STUD_NAME"));
             bean.setFname(rs.getString("FATHER_NAME"));
             bean.setSurName(rs.getString("SURNAME"));
             bean.setRollNo(rs.getString("ROLL_NO"));
             bean.setRemarks(rs.getString("REMARKS"));
             bean.setCnic(rs.getString("CNIC"));
             bean.setPhoneNo(rs.getString("PHONE_NO"));
             bean.setEmail(rs.getString("EMAIL"));
             bean.setDataOfBirth(rs.getString("DATE_OF_BIRTH"));
            
             v.addElement(bean);
           }//end of while
            return v; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// emd of finally


}/// end of getStudents Method


public static Vector getPart(int batchId) throws Exception{
  
        try{
             st = con.createStatement();
             rs = st.executeQuery("SELECT * FROM part where BATCH_ID ="+batchId);
             System.out.print(rs+ "Shoban");
                     
             Vector v = new Vector();
           while(rs.next()){
             PartBean bean=new PartBean();
             bean.setBatchId(rs.getInt("BATCH_ID"));
             bean.setPart(rs.getInt("PART"));
             bean.setBatchYear(rs.getString("PART_YEAR"));
             bean.setRemarks(rs.getString("REMARKS"));
            
             v.addElement(bean);
           }//end of while
            return v; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// emd of finally
   
}///emd of getpart Method

public static Vector getPart() throws Exception{
  
        try{
             st = con.createStatement();
             rs = st.executeQuery("SELECT * FROM part");
             
                     
             Vector v = new Vector();
           while(rs.next()){
             PartBean bean=new PartBean();
             bean.setBatchId(rs.getInt("BATCH_ID"));
             bean.setPart(rs.getInt("PART"));
             bean.setBatchYear(rs.getString("PART_YEAR"));
             bean.setRemarks(rs.getString("REMARKS"));
            
             v.addElement(bean);
           }//end of while
            return v; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// emd of finally
   
}///emd of getpart Method

public static Vector  getTeacher(int deptId)throws Exception{
    
 //String query="SELECT * FROM students";
   //     System.out.println(query);
        try{
             st = con.createStatement();
             rs = st.executeQuery("SELECT * FROM teacher where DEPT_ID="+deptId);
             Vector v = new Vector();
           while(rs.next()){
             TeacherBean bean=new TeacherBean();
             
             bean.setDeptId(rs.getInt("DEPT_ID"));
           
             bean.setTeacherId(rs.getInt("TEACHER_ID"));
             bean.setTeacherName(rs.getString("TEACHER_NAME"));
             bean.setFname(rs.getString("FATHER_NAME"));
             bean.setSurName(rs.getString("SUR_NAME"));
             bean.setDataOfBirth(rs.getString("DATE_OF_BIRTH"));
             bean.setRemarks(rs.getString("REMARKS"));
             bean.setNic(rs.getString("NIC"));
             bean.setEmail(rs.getString("EMAIL"));
             bean.setContactNo(rs.getString("CONTACT_NO"));
             bean.setDateOfAppointment(rs.getString("DATE_OF_APPOINTMENT"));
             bean.setDesignationAppointment(rs.getString("DESIGNATION_AT_THE_TIME_OF_APPOINTMENT"));
             bean.setCurrentDesignation(rs.getString("CURRENT_DESIGNATION"));
             bean.setRemarks(rs.getString("REMARKS"));
            
             v.addElement(bean);
           }//end of while
            return v; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// emd of finally


}/// end of getStudents Method




public static Vector  getTeacher()throws Exception{
    
 //String query="SELECT * FROM students";
   //     System.out.println(query);
        try{
             st = con.createStatement();
            String deptId;
             rs = st.executeQuery("SELECT * FROM teacher");
             Vector v = new Vector();
           while(rs.next()){
             TeacherBean bean=new TeacherBean();
             
             bean.setDeptId(rs.getInt("DEPT_ID"));
           
             bean.setTeacherId(rs.getInt("TEACHER_ID"));
             bean.setTeacherName(rs.getString("TEACHER_NAME"));
             bean.setFname(rs.getString("FATHER_NAME"));
             bean.setSurName(rs.getString("SUR_NAME"));
             bean.setDataOfBirth(rs.getString("DATE_OF_BIRTH"));
             bean.setRemarks(rs.getString("REMARKS"));
             bean.setNic(rs.getString("NIC"));
             bean.setEmail(rs.getString("EMAIL"));
             bean.setContactNo(rs.getString("CONTACT_NO"));
             bean.setDateOfAppointment(rs.getString("DATE_OF_APPOINTMENT"));
             bean.setDesignationAppointment(rs.getString("DESIGNATION_AT_THE_TIME_OF_APPOINTMENT"));
             bean.setCurrentDesignation(rs.getString("CURRENT_DESIGNATION"));
             bean.setRemarks(rs.getString("REMARKS"));
            
             v.addElement(bean);
           }//end of while
            return v; 
        }finally{
        
            if(rs!=null)
                rs.close();
           if(st!=null)st.close();
        }/// emd of finally


}/// end of getStudents Method





 public static int deleteFaculty(int facId)throws Exception{
        String query="delete from faculty where FAC_ID="+facId;
         System.out.println(query);
         Statement st=null;
         try{
             st=con.createStatement();
             int rows=st.executeUpdate(query);
             return rows;
             
        }finally{
             if(st!=null){
                 st.close();
             }
        }//end finally
    }//end DeleteFaculty
    
 public static int deleteDepartment(int deptId)throws Exception{
        String query="delete from department where DEPT_ID="+deptId;
         System.out.println(query);
         Statement st=null;
        try{
             st=con.createStatement();
             int rows=st.executeUpdate(query);
             return rows;
             
        }finally{
             if(st!=null){
                 st.close();
             }
        }//end finally
    }// end DeleteDepartment
 
  public static int deleteProgram(int progId)throws Exception{
        String query="delete from programs where PROG_ID="+progId;
         System.out.println(query);
         Statement st=null;
        try{
             st=con.createStatement();
             int rows=st.executeUpdate(query);
             return rows;
             
         }finally{
             if(st!=null){
                 st.close();
             }
        }//end finally
        
    }//end DeleteProgram
    
  public static int deleteBatch(int batchId)throws Exception{
        String query="delete from batch where BATCH_ID="+batchId;
         System.out.println(query);
         Statement st=null;
        try{
             st=con.createStatement();
             int rows=st.executeUpdate(query);
             return rows;
             
         }finally{
             if(st!=null){
                 st.close();
             }
        } // end finally
    } // end DeleteBatch

   public static int deleteStudent(int studId)throws Exception{
        String query="delete from students where STUD_ID="+studId;
         System.out.println(query);
         Statement st=null;
        try{

            st=con.createStatement();
             int rows=st.executeUpdate(query);
             return rows;
             
         }finally{
            
             if(st!=null){
                 st.close();
             }
             
         } // end finally
        
    } // end DeleteStudent

   
   


public static int deletePart(int batchId, int part) throws SQLException {
    String query = "DELETE FROM part WHERE BATCH_ID = ? AND PART = ?";
    System.out.println(query);
    PreparedStatement ps = null;
    try {
        ps = con.prepareStatement(query);
        ps.setInt(1, batchId);
        ps.setInt(2, part);
        int rows = ps.executeUpdate();
        return rows;
    } finally {
        if (ps != null) {
            ps.close();
        }
    }
}


   
   public static int deleteTeacher(int teacherId)throws Exception{
        String query="delete from teacher where TEACHER_ID="+teacherId;
         System.out.println(query);
         Statement st=null;
        try{

            st=con.createStatement();
             int rows=st.executeUpdate(query);
             return rows;
             
         }finally{
            
             if(st!=null){
                 st.close();
             }
             
         } // end finally
        
    } // end DeleteTeacher
   
   
 public static int updateFaculty(int facId,String facName,String remarks) throws Exception{
        String query= "update faculty set FAC_NAME='"+facName+"',REMARKS='"+remarks+"' where FAC_ID="+facId;
        System.out.println(query);
        Statement st=null;
        try{
            
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null){
                st.close();
            }
        }
    } //end updateFaculty
    
    public static int updateDepartment(int deptId, String deptName, String remarks)throws Exception{ 
        //String query= "update department set DEPT_NAME='"+deptName+"'REMARKS='"+remarks+"' where DEPT_ID="+deptId;
        
        
        String query = "update department set DEPT_NAME='"+deptName+"', REMARKS='"+remarks+"'where DEPT_ID="+deptId;
        
        
        System.out.println(query);
        Statement st=null;
        try{
            
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null){
              st.close();
            }
        }
    }// end update Department
    
    public static int updateProgram(int progId, String progName, String progDuration, String remarks)throws Exception{
      String query= "update programs set PROG_NAME='"+progName+"',PROG_DURATION_IN_SEM='"+progDuration+"',REMARKS='"+remarks+"'where PROG_ID="+progId;
       
      //String query = "update programs set PROG_NAME='"+progName+"',PROG_DURATION_IN_SEM'"+progDuration+"',REMARKS='"+remarks+"' where PROG_ID="+progId;
        
        System.out.println(query);
        Statement st=null;
        try{    
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null){
                st.close();
            }
        }
    }// end upadte Program
    
        public static int updateBatch(int batchId, String shift, String batchYear, String groupDesc, String remarks)throws Exception{
        String query= "update batch set BATCH_YEAR='"+batchYear+"',SHIFT='"+shift+"',GROUP_DES='"+groupDesc+"',REMARKS='"+remarks+"'where BATCH_ID="+batchId;
        System.out.println(query);
        Statement st=null;
        try{    
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null){
                st.close();
            }
        }
    }// end upadte Batch
        
            
        public static int updateStudent(int studId,String studName,String fatherName,String surName,String rollNo,String remarks)throws Exception{
        String query= "update students set STUD_NAME='"+studName+"',FATHER_NAME='"+fatherName+"',SURNAME='"+surName+"',ROLL_NO='"+rollNo+"',REMARKS='"+remarks+"'where STUD_ID="+studId;
        System.out.println(query);
        Statement st=null;
        try{    
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null){
                st.close();
            }
        }
    }// end upadte Student
   
          
        public static int updatePart(int part,String batchYear,String remarks)throws Exception{
        String query= "update part set PART_YEAR='"+batchYear+"',REMARKS='"+remarks+"'where PART="+part;
        System.out.println(query);
        Statement st=null;
        try{    
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null){
                st.close();
            }
        }
    }// end upadte Student
       
    
     public static int updateTeacher(int teacherId,String contactNo,String teacherName,String fatherName,String surName,String dateOfBirth,String  dateOfAppointment,String  designationAtTheOfAppointment,String currentDesignation,String cNic,String email,String remarks)throws Exception{
        String query= "update teacher set CONTACT_NO='"+contactNo+"',TEACHER_NAME='"+teacherName+"',FATHER_NAME='"+fatherName+"',SUR_NAME='"+surName+"',DATE_OF_BIRTH='"+dateOfBirth+"',DATE_OF_APPOINTMENT='"+dateOfAppointment+"',DESIGNATION_AT_THE_TIME_OF_APPOINTMENT='"+designationAtTheOfAppointment+"',CURRENT_DESIGNATION='"+currentDesignation+"',NIC='"+cNic+"',EMAIL='"+email+"',REMARKS='"+remarks+"'where TEACHER_ID="+teacherId;
        System.out.println(query);
        Statement st=null;
        try{    
            st=con.createStatement();
            int rows=st.executeUpdate(query);
            return rows;
        }finally{
            if(st!=null){
                st.close();
            }
        }
    }// end upadte Student
   
        
  public static int addFaculty(String facName,String remarks)throws Exception{
        String query="insert into faculty (FAC_NAME,REMARKS) values ('"+facName+"','"+remarks+"')";
            System.out.println(query);
            Statement st=null;
            try{
                st=con.createStatement();
                int rows=st.executeUpdate(query);
                return rows;
            }finally{
                if(st!=null)st.close();
            }
        }//end add Faculty
        
                
        public static int addDepartment(int facId,String deptName, String remarks)throws Exception{
        String query="insert into department(FAC_ID,DEPT_NAME,REMARKS) values ("+facId+",'"+deptName+"','"+remarks+"')";
            System.out.println(query);
            Statement st=null;
            try{
                st=con.createStatement();
                int rows=st.executeUpdate(query);
                return rows;
            }finally{
                if(st!=null)st.close();
            }
        }// end add Department
                        
        public static int addProgram(int deptId,String progName,String progDuration, String remarks)throws Exception{
        String query="insert into programs (DEPT_ID,PROG_NAME,PROG_DURATION_IN_SEM,REMARKS) values ("+deptId+",'"+progName+"','"+progDuration+"','"+remarks+"')";
            System.out.println(query);
            Statement st=null;
            try{
                st=con.createStatement();
                int rows=st.executeUpdate(query);
                return rows;
            }finally{
                if(st!=null)st.close();
            }
        }// end add Program
        
        public static int addBatch(int progId, String batchYear,String shift,String groupDes,String remarks)throws Exception{
        String query="insert into batch (PROG_ID,BATCH_YEAR,SHIFT,GROUP_DES,REMARKS) values ("+progId+","+batchYear+",'"+shift+"','"+groupDes+"','"+remarks+"')";
            System.out.println(query);
            Statement st=null;
            try{
                st=con.createStatement();
                int rows=st.executeUpdate(query);
                return rows;
            }finally{
                if(st!=null)st.close();
            }
        }// end add Batch
        
        public static int addStudent(int batchId,String studName,String fatherName,String surName,String rollNo,String remarks)throws Exception{
        String query="insert into students (BATCH_ID,STUD_NAME,FATHER_NAME,SURNAME,ROLL_NO,REMARKS) values ("+batchId+",'"+studName+"','"+fatherName+"','"+surName+"','"+rollNo+"','"+remarks+"')";
            System.out.println(query);
            Statement st=null;
            try{
                st=con.createStatement();
                int rows=st.executeUpdate(query);
                return rows;
            }finally{
                if(st!=null)st.close();
            }
        }// end add Stduent
        
         
        public static int addPart(int batchId,int part,String batchYear,String remarks)throws Exception{
        String query="insert into part (BATCH_ID,PART,PART_YEAR,REMARKS) values ("+batchId+","+part+",'"+batchYear+"','"+remarks+"')";
            System.out.println(query);
            Statement st=null;
            try{
                st=con.createStatement();
                int rows=st.executeUpdate(query);
                return rows;
            }finally{
                if(st!=null)st.close();
            }
        }// end add Batch
        
        
        
        public static int addTeacher(int deptId,String contactNo,String teacherName,String fatherName,String surName,String dateOfBirth,String  dateOfAppointment,String  designationAtTheOfAppointment,String currentDesignation,String cNic,String email,String remarks)throws Exception{
        String query="insert into teacher (DEPT_ID,TEACHER_NAME,FATHER_NAME,SUR_NAME,DATE_OF_BIRTH,CONTACT_NO,DATE_OF_APPOINTMENT,DESIGNATION_AT_THE_TIME_OF_APPOINTMENT,CURRENT_DESIGNATION,NIC,EMAIL,REMARKS) values ("+deptId+",'"+teacherName+"','"+fatherName+"','"+surName+"','"+dateOfBirth+"','"+contactNo+"','"+dateOfAppointment+"','"+designationAtTheOfAppointment+"','"+currentDesignation+"','"+cNic+"','"+email+"','"+remarks+"')";
            System.out.println(query);
            Statement st=null;
            try{
                st=con.createStatement();
                int rows=st.executeUpdate(query);
                return rows;
            }finally{
                if(st!=null)st.close();
            }
        }// end add Stduent
        
        
        
       
public  static void main(String arg[]){

    DBManager ob=new DBManager();

}
}// end Manager
