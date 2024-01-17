<%--
  Created by IntelliJ IDEA.
  User: 東芝
  Date: 1/17/2024
  Time: 5:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/home">Management Page</a>
<h1>Danh sách bài hát nghe nhạc:</h1>
<c:if test="${not empty musicList}">
    <ul>
        <c:forEach items="${musicList}" var="song">
            <li>
                <strong>Tên bài hát:</strong> ${song.songName}<br>
                <strong>Tác giả:</strong> ${song.author}<br>
                <strong>Mô tả:</strong> ${song.description}<br>
                <strong>Thời lượng:</strong> ${song.duration} phút<br>
                <img src="${song.imageUrl}" alt="Hình ảnh bài hát"><br>
                <a href="${song.videoUrl}" target="_blank">Watch Video</a>
            </li>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${empty musicList}">
    <p>Không có bài hát nào.</p>
</c:if>
</body>
</html>
