<%--
  Created by IntelliJ IDEA.
  User: Beschastniy
  Date: 30.06.2014
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Spring MVC - Ajax</title>
    <script src="js/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%--<div id="container">--%>


<%--
    <div id="error"></div>
    <div id="updateDepartment"></div>

&lt;%&ndash;</div>&ndash;%&gt;
<div id="employeeList"></div>
<div id="addEmployee"></div>
<div id="editEmployee"></div>--%>


<script src="js/SimpleInheritance.js"></script>
<script src="js/eventSupport.js"></script>
<script src="js/dataSource.js"></script>
<script src="js/ajaxDataSource.js"></script>

<script src="js/addDepartmentDialog.js"></script>
<script src="js/updateDepartmentDialog.js"></script>
<script src="js/addEmployeeDialog.js"></script>
<script src="js/updateEmployeeDialog.js"></script>

<script src="js/employeeTable.js"></script>
<script src="js/table.js"></script>

<script src="js/utils.js"></script>
<script src="js/main.js"></script>
<%--<h1>List of departments</h1>
<p>Here you can see the list of the Departments, edit them, add,remove or see list of department employees .</p>
<button><a href="/add.html">Add department </a></button>
<table border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="10%">id</th>
<th width="15%">name</th>
<th width="10%">Emplyee list</th>
<th width="10%">edit department</th>
<th width="10%">delete department</th>
</tr>
</thead>
<tbody>
<c:forEach var="department" items="${departmentList}">
<tr>
<td>${department.dep_id}</td>
<td>${department.name}</td>
<td><button><a href="/employee/${department.dep_id}.html">Show list</a></button></td>
<td><button><a href="/edit/${department.dep_id}.html">edit</a></button></td>
<td>
<spring:form action="/delete.html" commandName="department" method="post">
<spring:input type="hidden" path="dep_id" value="${department.dep_id}"/><br>
<spring:input type="hidden" path="name" value="${department.name}"/><br>
<input type="submit" value="Delete" >
</spring:form>
</td>
</tr>

</c:forEach>
</tbody>
</table>--%>

</body>
</html>
