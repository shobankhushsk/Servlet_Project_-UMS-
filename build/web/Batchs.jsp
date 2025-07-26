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
    <title>Batch Management</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" crossorigin="anonymous">
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
        .card { width: 100%; height: 800px; }
        
        
    </style>
</head>
<body>
    <main class="d-flex align-items-center primary-background banner-background" style="height: 100vh">
        <div class="container">
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <div class="card">
                        <div class="card-header primary-background text-white text-center">
                            <br>
                            <h1>Batch Management</h1>
                        </div>
                        <div class="card-body">
                            <form action="BatchServlet" method="post">
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
                                    <select id="batchYearID" name="batch" onchange="handleChange()" class="form-control">
                                        <option value="">Select Batch...</option>
                                    </select>

                                    <div class="form-group row">
                                        <div class="col-md-6">
                                            <label for="shift-display">SHIFT:</label>
                                            <select id="shift-display" name="shift" class="form-control">
                                                <option value="">Select...</option>
                                                <optgroup label="Shift">
                                                    <option value="MORNING">MORNING</option>
                                                    <option value="EVENING">EVENING</option>
                                                    <option value="NOON">NOON</option>
                                                </optgroup>
                                            </select>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="group-display">GROUP:</label>
                                            <select id="group-display" name="group" class="form-control">
                                                <option value="">Select...</option>
                                                <optgroup label="Group">
                                                    <option value="MEDICAL">MEDICAL</option>
                                                    <option value="ENGINEERING">ENGINEERING</option>
                                                    <option value="COMMERCE">COMMERCE</option>
                                                    <option value="GENERAL">GENERAL</option>
                                                </optgroup>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="batchYear">Batch Year</label>
                                    <input name="batch_Year" required type="text" class="form-control" id="batchYear" placeholder="Enter Batch Year">
                                </div>
                               
                                <div class="form-group">
                                    <label for="remarks">Remarks</label>
                                    <textarea name="remarks" class="form-control" id="remarks" placeholder="Enter Remarks"></textarea>
                                </div>
                                 <input type="hidden" name="batchId" id="batchId">                                                                                                            
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

    function handleChange() {
        var selectElement = document.getElementById("batchYearID");
        var selectedOption = selectElement.options[selectElement.selectedIndex];

        var batchId = selectedOption.value;
        var batch_Year = selectedOption.text;
        var remarks = selectedOption.getAttribute("remarks");

        // Set batch year and remarks in the respective text fields
        document.getElementById("batchId").value = batchId;
        document.getElementById("batchYear").value = batch_Year;
        document.getElementById("remarks").value = remarks;
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
