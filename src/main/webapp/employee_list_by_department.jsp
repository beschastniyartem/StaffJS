<%--
  Created by IntelliJ IDEA.
  User: Beschastniy
  Date: 30.06.2014
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Employee</title>
</head>
<body>
<h1>List of Employees</h1>
<p>Here you can see the list of the Employees, edit them, add, or remove.</p>

   <%-- <fmt:formatDate value="" pattern="dd-MM-yyyy" var="test"/>
<c:out value="${test}"/>

    <c:out value="${errors}"></c:out>--%>
<%--
<spring:form action="/addEmployee.html" commandName="employeeTemp" method="post">
    <spring:input type="hidden" path="dep_id" value="${dep_id}"/><br>
    <input type="submit" value="Add new employee" >
</spring:form>--%>
<button><a href="/addEmployee/${dep_id}.html">Add</a></button>
<%--    <spring:form action="/delete.html" commandName="department" method="post">
                    <spring:input type="hidden" path="dep_id" value="${department.dep_id}"/><br>
                    <spring:input type="hidden" path="name" value="${department.name}"/><br>
                    <input type="submit" value="Delete" >
                </spring:form>--%>
<strong style="color:#ff1000">${error}</strong>
<table border="1px" cellpadding="0" cellspacing="0" >
  <thead>
  <tr>
      <th width="10%">id</th>
      <th width="15%">email</th>
      <th width="10%">salary</th>
      <th width="10%">birthday</th>
      <th width="10%">edit player</th>
      <th width="10%">delete player</th>
  </tr>
  </thead>

  <tbody>
    <c:forEach var="employee" items="${employeeList}">
            <tr>
                <td>${employee.empl_id}</td>
                <td><c:out value="${employee.email}" escapeXml="true"/></td>
                <td>${employee.salary}</td>
                <td>${employee.birthday}</td>
                <td><button><a href="/editEmployee/${employee.empl_id}.html">Edit</a></button></td>
                <td>
                   <form action="/deleteEmployee.html" method="post">
                       <input type="hidden" path="empl_id" value="${employee.empl_id}"/><br>
                       <input type="hidden" path="dep_id" value="${employee.dep_id}"/><br>
                       <input type="submit" value="Delete" >
                   </form>
                </td>
            </tr>
    </c:forEach>
  </tbody>
</table>
List of <a href="/department.html">departments</a>.
</body>
</html>
