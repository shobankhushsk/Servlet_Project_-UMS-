/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.Servlets;

import DatabaseManager.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Students extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Students</title>");            
            out.println("</head>");
            out.println("<body>");
            String action =  request.getParameter("action");
            String StudName =  request.getParameter("firstName");
            String fName =  request.getParameter("fatherName");
            String surName =  request.getParameter("surName");
            String rollNo =  request.getParameter("rollNumber");
            String btachId =  request.getParameter("batch");
            int  bId = Integer.parseInt(btachId); 
           String stud = request.getParameter("studentID");
           int sId = Integer.parseInt(stud);
            String remarks  = "GOOD";
 
//            out.println("<h1>action:"+action+"</h1>");
//            out.println("<h1>StudName:"+StudName+"</h1>");
//            out.println("<h1>fName:"+fName+"</h1>");
//            out.println("<h1>surName:"+surName+"</h1>");
//            out.println("<h1>rollNo:"+rollNo+"</h1>");
//            out.println("<h1>bId:"+bId+"</h1>");


              switch (action) {
                case "add":
                    int rowAdd = DBManager.addStudent(bId, StudName, fName, surName, rollNo, remarks);
                    if (rowAdd > 0) {
                        request.setAttribute("message", "Record added successfully.");
                    } else {
                        request.setAttribute("message", "Failed to add record.");
                    }
                    break;
                case "update":
                    int rowUpdate = DBManager.updateStudent(sId, StudName, fName, surName, rollNo, remarks);
                    if (rowUpdate > 0) {
                        request.setAttribute("message", "Record updated successfully.");
                    } else {
                        request.setAttribute("message", "Failed to update record.");
                    }
                    break;
                case "delete":
                    int rowDelete = DBManager.deleteStudent(sId);
                    if (rowDelete > 0) {
                        request.setAttribute("message", "Record deleted successfully.");
                    } else {
                        request.setAttribute("message", "Failed to delete record.");
                    }
                    break;
                    
                default:
                    request.setAttribute("message", "Invalid action.");
                    break;
            }
            request.getRequestDispatcher("Students.jsp").forward(request, response);
            out.println("</body>");
            out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        }
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
