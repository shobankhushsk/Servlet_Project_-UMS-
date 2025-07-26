<%@page import="java.util.ArrayList"%>
<%@page import="BeanClasses.FacultyBean"%>
<%@page import="java.util.Vector"%>
<%@page import="DatabaseManager.DBManager"%>
<%@page import="java.util.List" %>

<%
    Vector<FacultyBean> v = DBManager.getFaculty();
    List<FacultyBean> faculties = new ArrayList<>(v);
    String message = (String) request.getAttribute("message");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Menu</title>

    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .banner-background {
            clip-path: polygon(30% 0%, 70% 0%, 100% 0, 100% 91%, 63% 100%, 22% 91%, 0 99%, 0 0);
        }
        .btn {
            margin: 10px;
            width: 200px;
            transition: all 0.3s ease;
        }
        .btn:hover {
            transform: scale(1.1); /* Increase size by 10% on hover */
            box-shadow: 0 0 20px rgba(0,0,0,0.2); /* Add shadow on hover */
        }
        .card {
            margin-top: 20px;
            background-color: rgba(255, 255, 255, 0.9); /* Off-white background color */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0,0,0,0.1); /* Soft shadow */
        }
        .card-header {
            background-color: #007bff; /* Primary color for header */
            color: #fff; /* White text */
            border-radius: 10px 10px 0 0; /* Rounded corners */
            padding: 15px;
        }
        .card-body {
            padding: 20px;
        }
    </style>
</head>
<body>
    <main class="d-flex align-items-center primary-background banner-background" style="height: 90vh">
        <div class="container">
            <div class="row">
                <div class="col-md-5 offset-md-4">
                    <div class="card">
                        <div class="card-header text-center">
                            <h1>Main Menu</h1>
                        </div>
                        <div class="card-body text-center">
                            <div class="row">
                                <div class="col-12">
                                    <button class="btn btn-primary mt-5" onclick="navigateTo('Faculty')">Faculty</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <button class="btn btn-secondary mt-5" onclick="navigateTo('department')">Department</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <button class="btn btn-warning mt-5" onclick="navigateTo('program')">Program</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <button class="btn btn-danger mt-5" onclick="navigateTo('Batchs')">Batch</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <button class="btn btn-info mt-5" onclick="navigateTo('Students')">Students</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <!-- JavaScript -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="js/myjs.js" type="text/javascript"></script>

    <script>
        function navigateTo(page) {
            window.location.href = page + ".jsp"; // Ensure each frame (Faculty, Department, Program, Batch, Students) has a corresponding .jsp file
        }

        <% if (message != null) { %>
            alert("<%= message %>");
        <% } %>
    </script>
</body>
</html>
