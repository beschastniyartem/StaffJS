<%--
  Created by IntelliJ IDEA.
  User: artemb
  Date: 02.07.14
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>Add employee</title>
</head>
<body>
<h1>Add employee</h1>
<p>Here you can add a new employee.</p>
<spring:form action="/addEmployeeSubmit.html" commandName="employee" method="post">
    <strong style="color:#ff1000">${errors["email"]}</strong>
    <strong>Employee Email</strong>:<spring:input type="text" path="email" value="${employee.email}"/><br>
    <strong style="color:#ff1000">${errors["salary"]}</strong>
    <strong>Employee Salary</strong>:<spring:input type="text" path="salary" value="${employee.salary}"/><br>
    <strong style="color:#ff1000">${errors["birthday"]}</strong>
    <strong>Employee Birthday</strong>:<spring:input type="date" path="birthday" value="${employee.birthday}"/><br>
    <spring:input type="hidden" path="dep_id" value="${employee.dep_id}"/><br>
    <input type="submit" value="Add">
</spring:form>
<br>
List of <a href="/employee/${employee.dep_id}.html">employees</a>.
</body>



</html>
