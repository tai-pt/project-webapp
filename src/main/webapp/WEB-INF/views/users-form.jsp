<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm/Chỉnh Sửa Người Dùng</title>
</head>
<body>
    <h1>${crud == null ? 'Thêm Người Dùng' : 'Chỉnh Sửa Người Dùng'}</h1>
    <form action="${crud == null ? 'insert' : 'update'}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${crud != null ? crud.id : ''}">
        <label for="name">Tên:</label>
        <input type="text" id="name" name="name" value="${crud != null ? crud.name : ''}" required><br>
        <label for="infor">Thông Tin:</label>
        <textarea id="infor" name="infor" required>${crud != null ? crud.infor : ''}</textarea><br>
        <label for="address">Địa Chỉ:</label>
        <input type="text" id="address" name="address" value="${crud != null ? crud.address : ''}" required><br>
        <label for="photo">Ảnh:</label>
        <input type="file" id="photo" name="photo"><br>
        <button type="submit">Lưu</button>
        <a href="list">Hủy</a>
    </form>
</body>
</html>
