<%--
  Created by IntelliJ IDEA.
  User: artemb
  Date: 02.07.14
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>Edit employee</title>
</head>
<body>
<h1>Edit employee</h1>
<p>Here you can edit the employee.</p>
<spring:form action="/editEmployeeSubmit.html" commandName="employee" method="post">
<%--    <strong style="color:#ff1000">${errors.email}</strong>--%>
    <strong style="color:#ff1000">${errors["email"]}</strong>
    <strong>Employee Email</strong>:<spring:input type="text" path="email" value="${param['email'] ne null ? param['email'] : employee.email}"/><br>
    <strong style="color:#ff1000">${errors["salary"]}</strong>
    <strong>Employee Salary</strong>:<spring:input type="text" path="salary" value="${employee.salary}"/><br>
    <strong style="color:#ff1000">${errors["birthday"]}</strong>
    <strong>Employee Birthday</strong>:<spring:input type="date" path="birthday" value="${employee.birthday}"/><br>
    <spring:input type="hidden" path="empl_id" value="${employee.empl_id}"/><br>
    <spring:input type="hidden" path="dep_id" value="${employee.dep_id}"/><br>
    <input type="submit" value="Edit">
    <br>
    List of <a href="/employee/${employee.dep_id}.html">employees</a>.
</spring:form>
</body>
</html>
