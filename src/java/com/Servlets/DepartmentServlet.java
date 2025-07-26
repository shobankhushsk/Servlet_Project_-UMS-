package com.Servlets;

import DatabaseManager.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DepartmentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
           // String facId = request.getParameter("faculty");
            String deptName = request.getParameter("dept_name");
            String remarks = request.getParameter("remarks");
         
         Map<String, String[]> parameterMap = request.getParameterMap();
         String facId = "0"; // Default value

         if (parameterMap.containsKey("faculty")) {
            String[] facIdValues = parameterMap.get("faculty");
            if (facIdValues != null && facIdValues.length > 0 && facIdValues[0] != null && !facIdValues[0].trim().isEmpty()) {
                facId = facIdValues[0];
            }
        }

            
            int fcid = Integer.parseInt(facId);
            
            //int deptId = Integer.parseInt(request.getParameter("deptId"));

         Map<String, String[]> parameterMaps = request.getParameterMap();
         String deptId = "0"; 
            
              if (parameterMaps.containsKey("deptId")) {
            String[] deptIdValues = parameterMaps.get("deptId");
            if (deptIdValues != null && deptIdValues.length > 0 && deptIdValues[0] != null && !deptIdValues[0].trim().isEmpty()) {
                deptId = deptIdValues[0];
            }
        }
            int dept= Integer.parseInt(deptId);
            int row = 0;
            if ("add".equalsIgnoreCase(action)) {
                
                row = DBManager.addDepartment(fcid, deptName, remarks);
                if (row > 0) {
                    request.setAttribute("message", "Record added successfully.");
                } else {
                    request.setAttribute("message", "Something went wrong.");
                }
            }else if ("update".equalsIgnoreCase(action)) {
            row = DBManager.updateDepartment(dept, deptName, remarks);
            if (row > 0) {
                request.setAttribute("message", "Record updated successfully.");
            } else {
                request.setAttribute("message", "Something went wrong.");
            }
        }else if ("delete".equalsIgnoreCase(action)) {
            row = DBManager.deleteDepartment(dept);
            if (row > 0) {
                request.setAttribute("message", "Record Delete successfully.");
            } else {
                request.setAttribute("message", "Something went wrong.");
            }
        }
            
            
            
            request.getRequestDispatcher("department.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DepartmentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
