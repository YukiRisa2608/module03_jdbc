<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="/hello-servlet">Hello Servlet</a>
<a href="/time-servlet?action=time">Display Time</a>
<a href="/time-servlet?action=day">Display Day</a>

<br>

<form action="/time-servlet" method="post">
  <input type="text" name="id">
  <input type="text" name="name">
  <input type="text" name="age">
  <input type="submit" value="show" name="action">
</form>

<%--<h1><c:out value="Hồ Hùng"></c:out></h1>--%>
<%--<%!--%>
<%--    int a = 100;--%>
<%--%>--%>
<%--<c:set var="a" value="100" scope="page" ></c:set>--%>

<%--<p><c:out value="${a}"></c:out></p>--%>


</body>
</html>