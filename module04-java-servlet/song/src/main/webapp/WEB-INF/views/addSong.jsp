<%--
  Created by IntelliJ IDEA.
  User: 東芝
  Date: 1/16/2024
  Time: 6:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Thêm Bài Hát Mới</h2>
<form action="${pageContext.request.contextPath}/addSong" method="post">
    <label for="songName">Tên Bài Hát:</label>
    <input type="text" id="songName" name="songName"><br>

    <label for="author">Tác Giả:</label>
    <input type="text" id="author" name="author"><br>

    <label for="description">Mô Tả:</label>
    <textarea id="description" name="description"></textarea><br>

    <label for="imageUrl">URL Hình Ảnh:</label>
    <input type="text" id="imageUrl" name="imageUrl"><br>

    <label for="videoUrl">URL Video:</label>
    <input type="text" id="videoUrl" name="videoUrl"><br>

    <label for="duration">Thời Lượng (phút):</label>
    <input type="number" id="duration" name="duration"><br>

    <input type="submit" value="Thêm Bài Hát">
</form>

</body>
</html>
