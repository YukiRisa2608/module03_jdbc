<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<br/>
<a href="/home">Management Page</a>
<br/>
<a href="${pageContext.request.contextPath}/musicPlay">Music Play</a>
</body>
</html>