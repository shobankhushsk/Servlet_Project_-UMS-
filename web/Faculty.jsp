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
    <title>Faculty Management</title>

    <!-- CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .banner-background {
            clip-path: polygon(30% 0%, 70% 0%, 100% 0, 100% 91%, 63% 100%, 22% 91%, 0 99%, 0 0);
        }
        .btn-secondary { 
            left: 260px;
        } 
        .btn-danger {
            left: 180px;
        }
        .btn-warning {
            left: 100px;
            height: 50px;
            width: 80px;
        }
        .btn-primary {
            left: 20px;
            height: 50px;
            width: 80px;
        }
        .btn-info{
            left: 320px;
        }
        
    </style>
</head>
<body>
    <main class="d-flex align-items-center primary-background banner-background" style="height: 90vh">
        <div class="container">
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <div class="card">
                        <div class="card-header primary-background text-white text-center">
                            <br>
                            <h1>Faculty</h1>
                        </div>
                        <div class="card-body">
                            <form action="FacultyServlet" method="post">
                                <div class="form-group">
                                    <label for="facName-display">FAC NAME DISPLAY:</label>
                                    <select id="facName-display" name="faculty" class="form-control" onchange="handleChange();">
                                        <option value="0">Faculties</option>
                                        <% for (FacultyBean faculty : faculties) { %>
                                            <option value="<%= faculty.getFacId() %>" data-name="<%= faculty.getFacName() %>" data-remarks="<%= faculty.getRemarks() %>">
                                                <%= faculty.getFacName() %>
                                            </option>
                                        <% } %>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="textValue">Faculty Name</label>
                                    <input name="fac_name" required type="text" class="form-control" id="textValue" placeholder="Enter Faculty Name">
                                </div>

                                <div class="form-group">
                                    <label for="remarks">Remarks</label>
                                    <textarea name="remarks" class="form-control" id="remarks" placeholder="Enter Remarks"></textarea>
                                </div>

                                <input type="hidden" name="facId" id="facId">

                                <div class="btn-group" role="group" aria-label="Basic example">
                                    <button type="submit" name="action" value="add" class="btn btn-primary">Add</button>
                                    <button type="submit" name="action" value="update" class="btn btn-warning">Update</button>
                                    <button type="submit" name="action" value="delete" class="btn btn-danger">Delete</button>
                                    <button type="reset" class="btn btn-secondary">Clear</button>
                                         <button type="button" class="btn btn-info" onclick="goBack()">Back</button>

                                </div>
                            </form>
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
        function handleChange() {
            var selectElement = document.getElementById("facName-display");
            var selectedOption = selectElement.options[selectElement.selectedIndex];
            
            var facName = selectedOption.getAttribute("data-name");
            var remarks = selectedOption.getAttribute("data-remarks");
            var facId = selectedOption.value;
            
            document.getElementById("textValue").value = facName;
            document.getElementById("remarks").value = remarks;
            document.getElementById("facId").value = facId;
        }
       function goBack() {
        window.location.href = 'index.jsp'; // Replace 'homePageURL' with the actual URL of the home page
    }

        <% if (message != null) { %>
            alert("<%= message %>");
        <% } %>
    </script>
</body>
</html>
