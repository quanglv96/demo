<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        .abc table td,th {
            border: 1px solid black;
            margin: 5px;
        }

        td {
            padding: 10px;
        }
    </style>
</head>
<body>
<div align="center">
    <div><h1>Manager Employment </h1></div>
    <table style="border: none">
        <tr>
            <td>
                <form action="/home?action=formAddEmploy" method="post">
                    <button style="background-color: forestgreen;width: 200px;height: 40px" type="submit">ADD NEW
                        EMPLOYMENT
                    </button>
                </form>
            </td>
            <td>
                <form action="/home?action=search" method="post"><input TYPE="text" name="search"
                                                                        placeholder="Enter Name Employment">
                    <button type="submit">Search</button>
                </form>
            </td>
        </tr>
    </table>
</div>
<div align="center" class="abc">
    <table>
        <tr>
            <th>STT</th>
            <th>Name</th>
            <th>Address</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Salary</th>
            <th>Department</th>
            <th colspan="2">Action</th>
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
                    <form action="/home?action=formEdit&idEmploy=${employ.getId_employment()}" method="post">
                        <button type="submit">Sửa</button>
                    </form>
                </td>
                <td>
                    <form onsubmit="return(checkDelete())" action="/home?action=deleteEmploy&idEmploy=${employ.getId_employment()}" method="post">
                        <button style="background-color: red" type="submit">Xóa</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<script>
    function checkDelete(){
        let result=confirm("Bạn chắc chắn muốn xoá");
        if(result){
            alert("successful delete");
            return true;
        }
        return  false;
    }
</script>
</html>