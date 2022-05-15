<%@ page import="com.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/valiemp.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>Employee Service</h1>
				<form id="formEmployee" name="formEmployee" method="post" action="EmployeeUI.jsp">  
					Name:  
 	 				<input id="empName" name="empName" type="text"  class="form-control form-control-sm">
					<br>Address:   
  					<input id="empAddress" name="empAddress" type="text" class="form-control form-control-sm">   
  					<br>Email:   
  					<input id="empEmail" name="empEmail" type="text"  class="form-control form-control-sm">
					<br>Phone Number:   
  					<input id="empPhone" name="empPhone" type="text"  class="form-control form-control-sm">
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidEmployeeIDSave" name="hidEmployeeIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divEmployeeGrid">
					<%
						Employee EmployeeObj = new Employee();
						out.print(EmployeeObj.readEmployee());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>