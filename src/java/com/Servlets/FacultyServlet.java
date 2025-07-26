package com.Servlets;

import DatabaseManager.DBManager;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FacultyServlet", urlPatterns = {"/FacultyServlet"})
public class FacultyServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        // String facId = request.getParameter("fac_name");
        String facName = request.getParameter("fac_name");
        String remarks = request.getParameter("remarks");
        //String facId = request.getParameter("facId");
        Map<String, String[]> parameterMap = request.getParameterMap();
        String facId = "0"; // Default value

        if (parameterMap.containsKey("facId")) {
            String[] facIdValues = parameterMap.get("facId");
            if (facIdValues != null && facIdValues.length > 0 && facIdValues[0] != null && !facIdValues[0].trim().isEmpty()) {
                facId = facIdValues[0];
            }
        }

// facId will be either the parameter value or "0"
        System.out.println("facId: " + facId);

        int id = Integer.parseInt(facId);
        int row = 0;
        if ("add".equalsIgnoreCase(action)) {
            row = DBManager.addFaculty(facName, remarks);
            if (row > 0) {
                request.setAttribute("message", "Record added successfully.");
            } else {
                request.setAttribute("message", "Something went wrong.");
            }
        } else if ("update".equalsIgnoreCase(action)) {
            row = DBManager.updateFaculty(id, facName, remarks);
            if (row > 0) {
                request.setAttribute("message", "Record updated successfully.");
            } else {
                request.setAttribute("message", "Something went wrong.");
            }
        } else if ("delete".equalsIgnoreCase(action)) {
            row = DBManager.deleteFaculty(id);
            if (row > 0) {
                request.setAttribute("message", "Record Delete successfully.");
            } else {
                request.setAttribute("message", "Something went wrong.");
            }
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FacultyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FacultyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "FacultyServlet";
    }
}
