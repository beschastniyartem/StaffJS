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
    <title>Add department</title>
</head>
<body>
<h1>Add department</h1>
<p>Here you can add a new department.</p>
<spring:form action="/addSubmit.html" commandName="department" method="post">
    <strong style="color:#ff1000">${errors["name"]}</strong>
    <strong>Department name</strong>:<spring:input path="name" value="${department.name}"/><br>
    <input type="submit" value="Add">
</spring:form>

<br>
List of <a href="/department.html">departments</a>.
</body>



</html>
