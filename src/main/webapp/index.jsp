<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        td, th{
            border: 1px solid black;
            margin: 5px;
        }
    </style>
</head>
<body>
<div align="center">
    <div><h1>Manager Employment </h1></div>
    <form action="/home?action=formAddEmploy" method="post">
        <button type="submit">ADD NEW EMPLOYMENT</button>
    </form>
    <table>
        <tr>
            <th>STT</th>
            <th>Name</th>
            <th>Address</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Salary</th>
            <th>Department</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${listEmployment}" var="employ">
        <tr>
            <td><c:out value="${employ.getSTT()}"/></td>
            <td><c:out value="${employ.getName_employment()}"/></td>
            <td><c:out value="${employ.getAddress()}"/></td>
            <td><c:out value="${employ.getEmail()}"/></td>
            <td><c:out value="${employ.getPhone()}"/></td>
            <td><c:out value="${employ.getSalary()}"/></td>
            <td><c:out value="${employ.getDepartment().getName_depart()}"/></td>
            <td>
                <a href="/home?action=formEdit&idEmploy=${employ.getId_employment()}">Sửa</a>
                <a href="/home?action=deleteEmploy&idEmploy=${employ.getId_employment()}">Xóa</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>