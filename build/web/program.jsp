<%@page import="BeanClasses.ProgramsBean"%>
<%@page import="java.util.Vector"%>
<%@page import="BeanClasses.FacultyBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BeanClasses.DepartmentBean"%>
<%@page import="DatabaseManager.DBManager"%>
<%@page import="java.util.List" %>

<%
    List<DepartmentBean> departments = DBManager.getDepartment();
    String message = (String) request.getAttribute("message");

    Vector<FacultyBean> v = DBManager.getFaculty();
    List<FacultyBean> faculties = new ArrayList<>(v);
    
    Vector<ProgramsBean> vs = DBManager.getProgram();
    List<ProgramsBean> programs = new ArrayList<>(vs);

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Programs Management</title>

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
    <main class="d-flex align-items-center primary-background banner-background" style="height: 105vh">
        <div class="container">
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <div class="card">
                        <div class="card-header primary-background text-white text-center">
                            <br>
                            <h1>PROGRAMS Management</h1>
                        </div>
                        <div class="card-body">
                            <form action="ProgServlet" method="post">
                                <div class="form-group">
                                   <label for="facName-display">Faculty DISPLAY:</label>
                                    <select id="faculty" name="faculty" class="form-control">
                                        <option value="">Select Faculty...</option>
                                        <% for (FacultyBean faculty : faculties) { %>
                                            <option value="<%= faculty.getFacId() %>">
                                                <%= faculty.getFacName() %>
                                            </option>
                                        <% } %>
                                    </select>

                                    <label for="deptName-display">DEPT NAME DISPLAY:</label>
                                    <select id="department" name="department" onChange="getPrograms()" class="form-control">
                                        <option value="">Select Department...</option>
                                    </select>

                                    <label for="progName-display">PROG NAME DISPLAY:</label>
                                    <select id="program" name="program" class="form-control" onchange=" handleChange()">
                                        <option value="">Select Program...</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="progName">Program Name</label>
                                    <input name="prog_name" required type="text" class="form-control" id="progName" placeholder="Enter Program Name">
                                </div>

                                <div class="form-group">
                                    <label for="progDuration">Program Duration (in semesters)</label>
                                    <input name="prog_duration" required type="number" class="form-control" id="progDuration" placeholder="Enter Program Duration">
                                </div>
                                
                                <div class="form-group">
                                    <label for="remarks">Remarks</label>
                                    <textarea name="remarks" class="form-control" id="remarks" placeholder="Enter Remarks"></textarea>
                                </div>
                                  <input type="hidden" name="deptId" id="deptId">
                                   <input type="hidden" name="progId" id="progId">

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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7H9UibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script>
    // JavaScript to update departments based on selected faculty
     $(document).ready(function() {
        $('#faculty').change(function() {
            var fac_id = $(this).val();
            if (fac_id) {
                $.ajax({
                    type: 'POST',
                    url: 'GetDepts', // Ensure this matches the URL pattern in web.xml
                    data: { fac_id: fac_id, action:'getDepartments' },
                    success: function(response) {
                        console.log("Response from server: ", response);
                        $('#department').html(response);
                    },
                    error: function(xhr, status, error) {
                        console.log("Error: " + error);
                        console.log("Status: " + status);
                        console.log("Response: " + xhr.responseText);
                    }
                });
            } else {
                $('#department').html('<option value="">Select department</option>');
            }
        });
    });

    function getPrograms() {
        var deptId = document.getElementById("department").value;
        if (deptId) {
            $.ajax({
                type: 'POST',
                url: 'GetDepts', // Ensure this matches the URL pattern in web.xml
                data: { deptId: deptId, action:'getPrograms' },
                success: function(response) {
                    console.log("Response from server: ", response);
                    $('#program').html(response);
                },
                error: function(xhr, status, error) {
                    console.log("Error: " + error);
                    console.log("Status: " + status);
                    console.log("Response: " + xhr.responseText);
                }
            });
        } else {
            $('#program').html('<option value="">Select program</option>');
        }
    }

    // JavaScript to update program details on selection
    function handleChange() {
        var selectElement = document.getElementById("program");
        var selectedOption = selectElement.options[selectElement.selectedIndex];

        var progName = selectedOption.text;
        var progDuration = selectedOption.getAttribute("data-duration");
        var progId = selectedOption.value;

        // Set program name and duration in the respective text fields
        document.getElementById("progName").value = progName;
        document.getElementById("progDuration").value = progDuration;
        document.getElementById("progId").value = progId;
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
