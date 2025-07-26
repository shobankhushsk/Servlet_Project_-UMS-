/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

/**
 *
 * @author Dell
 */
public class ProgServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProgServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String progName =  request.getParameter("prog_name");
            String prog_duration =  request.getParameter("prog_duration");
             String remarks =  request.getParameter("remarks");
          
           Map<String, String[]> parameterMap = request.getParameterMap();
         String deptId = "0"; 
            
              if (parameterMap.containsKey("department")) {
            String[] deptIdValue = parameterMap.get("department");
            if (deptIdValue != null && deptIdValue.length > 0 && deptIdValue[0] != null && !deptIdValue[0].trim().isEmpty()) {
                deptId = deptIdValue[0];
            }
        }
             
             
             int deptIds =  Integer.parseInt(deptId);
             String action = request.getParameter("action"); 
          
             
             Map<String, String[]> parameterMaps = request.getParameterMap();
             String progid = "0"; 
              if (parameterMaps.containsKey("program")) {
            String[] progIdValues = parameterMaps.get("program");
            if (progIdValues != null && progIdValues.length > 0 && progIdValues[0] != null && !progIdValues[0].trim().isEmpty()) {
                progid = progIdValues[0];
            }
        }
                int progsId = Integer.parseInt(progid);
            
              int row = 0; 
            if ("add".equalsIgnoreCase(action)) {
            row = DBManager.addProgram(deptIds , progName, prog_duration, remarks);
            if (row > 0) {
                request.setAttribute("message", "Record added successfully.");
            } else {
                request.setAttribute("message", "Something went wrong.");
            }  
            
           }else if ("update".equalsIgnoreCase(action)) {
            row = DBManager.updateProgram(progsId  , progName, prog_duration, remarks);
            if (row > 0) {
                request.setAttribute("message", "Record updated successfully.");
            } else {
                request.setAttribute("message", "Something went wrong.");
            }
        } else if ("delete".equalsIgnoreCase(action)) {
            row = DBManager.deleteProgram(progsId);
            if (row > 0) {
                request.setAttribute("message", "Record Delete successfully.");
            } else {
                request.setAttribute("message", "Something went wrong.");
            }
        }       
           
        
             request.getRequestDispatcher("program.jsp").forward(request, response);
 
            
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger(ProgServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
