/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseManager;

/**
 *
 * @author Dell
 */
import java.sql.*;
public class ConnectivityTest {
 
    public static void main(String arg[]){
   
       Connection conn = null;
      try{
            // Load the UCanAccess JDBC driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            // Connect to the database
            String dbPath = "E:\\JAVA_Programs_A_To_Z\\DataBase\\UniversityOfSindh.accdb";
            String url = "jdbc:ucanaccess://" + dbPath;
            conn = DriverManager.getConnection(url);
            
            // Perform some operations on the database
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM faculty");
            while (rs.next()) {
                System.out.println(rs.getString("FAC_NAME"));
                 System.out.println(rs.getInt("FAC_ID"));
                 System.out.println(rs.getString("REMARKS"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        
        
        catch (SQLException e) {
            e.printStackTrace();
        } 
        
    
    
    
    }
    
    
}
