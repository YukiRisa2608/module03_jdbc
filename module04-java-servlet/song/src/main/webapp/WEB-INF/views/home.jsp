<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách bài hát</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1 class="text-center">Danh sách bài hát</h1>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                <a class="navbar-brand" href="#">Hidden brand</a>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                </ul>
                <form class="d-flex" action="/search" method="get">
                    <input class="form-control me-2" type="text" name="search" value="${search}" placeholder="Tìm kiếm" aria-label="Tìm kiếm">
                    <button class="btn btn-outline-success" type="submit">Tìm kiếm</button>
                </form>
            </div>
        </div>
    </nav>
    <c:forEach items="${songs}" var="song">
        <div class="card w-100 my-3">
            <h1 class="fs-6"><fmt:formatDate value="${song.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" /></h1>
            <img src="${song.imageUrl}" class="card-img-top img-thumbnail w-50" alt="...">
            <div class="card-body">
                <h3 class="card-title">${song.title}</h3>
                <p class="card-text">${song.content}</p>
                <span class="btn btn-success">${song.author}</span>
            </div>
            <form action="/deleteSong" method="post">
                <input type="hidden" name="id"  value="${song.id}">
                <button type="submit" class="btn btn-danger">Xóa</button>
            </form>
            <div>
                <a href="#editSongModal" class="btn btn-warning w-20" data-toggle="modal">Sửa</a>
            </div>
            <div id="editSongModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="/updateSong" method="post">
                            <div class="modal-header">
                                <h4 class="modal-title">Chỉnh sửa bài hát</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" name="id"  value="${song.id}">
                                <div class="form-group">
                                    <label>Tác giả</label>
                                    <input type="text" name="author" class="form-control" value="${song.author}" required>
                                </div>
                                <div class="form-group">
                                    <label>Tiêu đề</label>
                                    <input type="text" name="title" class="form-control" value="${song.title}" required>
                                </div>
                                <div class="form-group">
                                    <label>Nội dung</label>
                                    <textarea class="form-control" name="content" required> ${song.content} </textarea>
                                </div>
                                <div class="form-group">
                                    <label>URL Hình ảnh</label>
                                    <input type="text" class="form-control" name="imageUrl" value="${song.imageUrl}" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
                                <input type="submit" class="btn btn-success" value="Chỉnh sửa">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
