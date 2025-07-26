<%@page import="BeanClasses.BatchBean"%>
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
    
    Vector<BatchBean> vsc = DBManager.getBatch();
    List<BatchBean> batches = new ArrayList<>(vsc);
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Students Management</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" crossorigin="anonymous">
    <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .banner-background {
            clip-path: polygon(30% 0%, 70% 0%, 100% 0, 100% 91%, 63% 100%, 22% 91%, 0 99%, 0 0);
        }

        .btn-group { margin-top: 10px; }
        
        .card { width: 100%; height: auto; }
        .student-card {
            margin-top: 0px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            height:47vh; 
        }
        .btn-info{left: -100px;}
        .btn-secondary { left: -140px; }
        .btn-danger { left: -180px; }
        .btn-warning { left: -220px; height: 50px; width: 80px; }
        .btn-primary { left: -260px; height: 50px; width: 80px; }

        .form-group { margin-bottom: 10px; }
        
        .form-group1{
           left: 27px;    
        }
    </style>
</head>
<body>
    <main class="d-flex align-items-center primary-background banner-background" style="height: 100vh">
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        <div class="card-header primary-background text-white text-center">
                            <br>
                            <h1>Students Management</h1>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <form action="Students" method="post">
                                    <!-- Student Details Card -->
                                    <div id="studentDetailsCard" class="student-card">
                                        <h4>Student Details</h4>
                                        <div class="form-group1">
                                            <label for="studName">STUDENT NAME:</label>
                                            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter Stud Name">
                                        </div>
                                        <div class="form-group1">
                                            <label for="fatherName">FATHER NAME:</label>
                                            <input type="text" class="form-control" id="fatherName" name="fatherName" placeholder="Enter Father Name">
                                        </div>
                                         <div class="form-group1">
                                            <label for="surName">SURNAME:</label>
                                            <input type="text" class="form-control" id="surName" name="surName" placeholder="Enter Surname">
                                        </div>
                                        <div class="form-group1">
                                            <label for="rollNumber">Roll Number:</label>
                                            <input type="text" class="form-control" id="rollNumber" name="rollNumber" placeholder="Enter Roll Number">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    
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
                                            <select id="program" name="program" class="form-control" onchange="filterBatches();">
                                                <option value="">Select Program...</option>
                                            </select>
                                            
                                            <label for="batchYear-display">BATCH YEAR DISPLAY:</label>
                                            <select id="batchYearID" name="batch" onchange="filterStudents()" class="form-control">
                                                <option value="">Select Batch...</option>
                                            </select>                                   
                                           
                                            <label for="student-display">STUDENTS DISPLAY:</label>
                                            <select id="studentID" name="studentID" onchange="handleChange()" class="form-control">
                                                <option value="">Select Students...</option>
                                            </select> 
                                        </div>
                                        
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
            </div>
        </div>
    </main>

<script>
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

    function filterBatches(){
        var progId = document.getElementById("program").value;
        if (progId) {
            $.ajax({
                type: 'POST',
                url: 'GetDepts', // Ensure this matches the URL pattern in web.xml
                data: { progId: progId, action:'getBatches' },
                success: function(response) {
                    console.log("Response from server: ", response);
                    $('#batchYearID').html(response);
                },
                error: function(xhr, status, error) {
                    console.log("Error: " + error);
                    console.log("Status: " + status);
                    console.log("Response: " + xhr.responseText);
                }
            });
        } else {
            $('#batchYearID').html('<option value="">Select batch</option>');
        }
    }
    
    function filterStudents(){
        var batchId = document.getElementById("batchYearID").value;
        if (batchId) {
            $.ajax({
                type: 'POST',
                url: 'GetDepts', // Ensure this matches the URL pattern in web.xml
                data: { batchId: batchId, action:'getStudents' },
                success: function(response) {
                    console.log("Response from server (students): ", response);
                    $('#studentID').html(response);
                },
                error: function(xhr, status, error) {
                    console.log("Error: " + error);
                    console.log("Status: " + status);
                    console.log("Response: " + xhr.responseText);
                }
            });
        } else {
            $('#studentID').html('<option value="">Select students</option>');
        }
    }

function handleChange() {
        var studentId = document.getElementById("studentID").value;
        if (studentId) {
            $.ajax({
                type: 'POST',
                url: 'GetDepts',
                data: { studentID: studentId, action: 'getStudentDetails' },
                dataType: 'json',
                success: function(student) {
                    console.log(student);
                    document.getElementById("firstName").value = student.studName;
                    document.getElementById("fatherName").value = student.fName;
                    document.getElementById("surName").value = student.surName;
                    document.getElementById("rollNumber").value = student.rollNo;

                    // Serialize the student object and store it in the hidden field
                    document.getElementById("studentData").value = JSON.stringify(student);
                },
                error: function(xhr, status, error) {
                    console.log("Error: " + error);
                    console.log("Status: " + status);
                    console.log("Response: " + xhr.responseText);
                }
            });
        }
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
