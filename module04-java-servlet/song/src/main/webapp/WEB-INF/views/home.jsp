<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Trang Chủ</title>
</head>
<body>
<h1>${message}</h1>
<a href="${pageContext.request.contextPath}/musicPlay">Home</a>
<%--${pageContext.request.contextPath} là một biểu thức EL (Expression Language) trong JSP thường được sử dụng để lấy đường dẫn gốc của ứng dụng web.nó không thay đổi trong quá trình chạy ứng dụng.--%>
<%--Tên "addSongForm" không cần phải giống tên của trang view, nhưng nó cần phải trùng khớp với URL đã ánh xạ trong controller.--%>
<a href="${pageContext.request.contextPath}/addSongForm">Add New Song</a>
<h2>Danh sách bài hát:</h2>
<c:if test="${not empty songs}">
    <ul>
<%--${songs} sẽ tham chiếu đến danh sách songList trong controller,mỗi lần lặp qua danh sách, biến "song" sẽ tham chiếu đến một bài hát trong danh sách--%>
        <c:forEach items="${songs}" var="song">
            <li>
                <strong>Tên bài hát:</strong> ${song.songName}<br>
                <strong>Tác giả:</strong> ${song.author}<br>
                <strong>Mô tả:</strong> ${song.description}<br>
                <strong>Thời lượng:</strong> ${song.duration} phút<br>
                <strong>Trạng thái:</strong> ${song.status ? 'Hiển thị' : 'Không hiển thị'}<br>
                <img src="${song.imageUrl}" alt="Hình ảnh bài hát" style="width: 200px; height: auto;"><br>
                <a href="${song.videoUrl}" target="_blank">Watch Video</a>
                <a href="${pageContext.request.contextPath}/deleteSong/${song.id}">Delete</a>
                <a href="${pageContext.request.contextPath}/editSongForm/${song.id}">Edit</a>
            </li>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${empty songs}">
    <p>Không có bài hát nào.</p>
</c:if>
</body>
</html>
