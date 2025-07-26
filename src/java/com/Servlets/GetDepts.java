package com.Servlets;

import BeanClasses.BatchBean;
import BeanClasses.DepartmentBean;
import BeanClasses.ProgramsBean;
import BeanClasses.StudentsBean;
import DatabaseManager.DBManager;
import com.google.gson.Gson;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class GetDepts extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Check if fac_id, dept_id, progId, or batchId parameter is present
        String action = request.getParameter("action");
        
        String facIdParam = request.getParameter("fac_id");
        String deptIdParam = request.getParameter("deptId");
        String progIdParam = request.getParameter("progId");
        

        try {
            if (action.equals("getDepartments")) {
                // If fac_id parameter is present, fetch departments for that faculty
                int facId = Integer.parseInt(facIdParam);
                Vector<DepartmentBean> departments = DBManager.getDepartment(facId);
                
                StringBuilder htmlBuilder = new StringBuilder();
                
                htmlBuilder.append("<option value=\"\">Select Department...</option>");
                for (DepartmentBean dept : departments) {
                    htmlBuilder.append("<option value=\"")
                               .append(dept.getDeptId())
                               .append("\">")
                               .append(dept.getDeptName())
                               .append("</option>");
                }
                out.print(htmlBuilder.toString());
            } else if (action.equals("getPrograms")) {
                // If dept_id parameter is present, fetch programs for that department
                int deptId = Integer.parseInt(deptIdParam);
                Vector<ProgramsBean> programs = DBManager.getProgram(deptId);
                         
                StringBuilder htmlBuilder = new StringBuilder();
                htmlBuilder.append("<option value=\"\">Select Program...</option>");
                for (ProgramsBean program : programs) {
                    htmlBuilder.append("<option value=\"")
                               .append(program.getProgId())
                               .append("\">")
                               .append(program.getProgName())
                               .append("</option>");
                }
                out.print(htmlBuilder.toString());
            } else if (action.equals("getBatches")) {
                // If progId parameter is present, fetch batches for that program
                int progId = Integer.parseInt(progIdParam);
                Vector<BatchBean> batches = DBManager.getBatch(progId);
                
                StringBuilder htmlBuilder = new StringBuilder();
                htmlBuilder.append("<option value=\"\">Select Batch...</option>");
                for (BatchBean batch : batches) {
                    htmlBuilder.append("<option value=\"")
                               .append(batch.getBatchId())
                               .append("\">")
                               .append(batch.getBatchYear())
                               .append(" - ")
                               .append(DBManager.groupDecode(batch.getGroupDesc()))
                               .append(" - ")
                               .append(DBManager.shiftDecode(batch.getShift()))
                               .append("</option>");
                }
                out.print(htmlBuilder.toString());
            } else if (action.equals("getStudents")) {
                String batchIdParam = request.getParameter("batchId");
                int batchId = Integer.parseInt(batchIdParam);
                Vector<StudentsBean> students = DBManager.getStudent(batchId);
                
                StringBuilder htmlBuilder = new StringBuilder();
                htmlBuilder.append("<option value=\"\">Select Students...</option>");
                for (StudentsBean student : students) {
                     System.out.print(student.getStudName());
                    htmlBuilder.append("<option value=\"")
                               .append(student.getStudId())
                               .append("\">")
                               .append(student.getStudName())
                           
                               .append("</option>");
                }
                out.print(htmlBuilder.toString());
            }
            else if (action.equals("getStudentDetails")) {
            String studId = request.getParameter("studentID");
            StudentsBean student = DBManager.getStudents(studId); 
            System.out.println(student);
            if (student != null) {
                String json = new Gson().toJson(student);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
}

    

            
            
            else {
                // Handle the case where none of the expected parameters are provided
                out.print(""); // Or any appropriate response
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions and return appropriate error response
            out.print("Error fetching data: " + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
