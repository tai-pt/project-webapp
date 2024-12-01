<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách người dùng</title>
</head>
<body>
    <h1>Danh sách Người Dùng</h1>
    <a href="new">Thêm Người Dùng Mới</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Thông Tin</th>
                <th>Địa Chỉ</th>
             
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${list}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.infor}</td>
                    <td>${user.address}</td>
                    <td>
                        <a href="edit?id=${user.id}">Chỉnh Sửa</a> | 
                        <a href="delete?id=${user.id}" onclick="return confirm('Bạn có chắc muốn xóa?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
