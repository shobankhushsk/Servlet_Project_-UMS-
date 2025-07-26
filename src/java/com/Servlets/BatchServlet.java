package com.Servlets;

import BeanClasses.BatchBean;
import DatabaseManager.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
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
public class BatchServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");

            int pogid = Integer.parseInt(request.getParameter("program"));
            String batchYear = request.getParameter("batch_Year");
            String group = request.getParameter("group");
            String shift = request.getParameter("shift");
            String remarks = request.getParameter("remarks");

            shift = DBManager.shiftEncode(shift);
            group = DBManager.groupEncode(group);

            int bId = Integer.parseInt(request.getParameter("batchId"));

            switch (action) {
                case "add":
                    int rowAdd = DBManager.addBatch(pogid, batchYear, shift, group, remarks);
                    if (rowAdd > 0) {
                        request.setAttribute("message", "Record added successfully.");
                    } else {
                        request.setAttribute("message", "Failed to add record.");
                    }
                    break;
                case "update":
                    int rowUpdate = DBManager.updateBatch(bId, shift, batchYear, group, remarks);
                    if (rowUpdate > 0) {
                        request.setAttribute("message", "Record updated successfully.");
                    } else {
                        request.setAttribute("message", "Failed to update record.");
                    }
                    break;
                case "delete":
                    int rowDelete = DBManager.deleteBatch(bId);
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
            request.getRequestDispatcher("Batchs.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BatchServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BatchServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BatchServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
