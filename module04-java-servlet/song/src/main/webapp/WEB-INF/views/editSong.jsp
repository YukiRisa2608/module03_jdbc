<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chỉnh Sửa Bài Hát</title>
    <!-- Thêm CSS hoặc JavaScript nếu cần -->
</head>
<body>
<h2>Chỉnh Sửa Bài Hát</h2>
<form action="${pageContext.request.contextPath}/editSong" method="post">
    <input type="hidden" name="id" value="${song.id}">

    <label for="songName">Tên Bài Hát:</label>
    <input type="text" id="songName" name="songName" value="${song.songName}"><br>

    <label for="author">Tác Giả:</label>
    <input type="text" id="author" name="author" value="${song.author}"><br>

    <label for="description">Mô Tả:</label>
    <textarea id="description" name="description">${song.description}</textarea><br>

    <label for="imageUrl">URL Hình Ảnh:</label>
    <input type="text" id="imageUrl" name="imageUrl" value="${song.imageUrl}"><br>

    <label for="videoUrl">URL Video:</label>
    <input type="text" id="videoUrl" name="videoUrl" value="${song.videoUrl}"><br>

    <label for="duration">Thời Lượng (phút):</label>
    <input type="number" id="duration" name="duration" value="${song.duration}"><br>

    <label for="status">Trạng Thái:</label>
    <select id="status" name="status">
        <option value="true" ${song.status ? 'selected' : ''}>Hoạt động</option>
        <option value="false" ${!song.status ? 'selected' : ''}>Không hoạt động</option>
    </select><br>

    <input type="submit" value="Cập Nhật Bài Hát">
</form>
</body>
</html>
