<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: QuangMax
  Date: 08/12/2022
  Time: 11:33 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADD Employment</title>
</head>
<body>
<div align="center">
  <form action="/home?action=saveAdd" method="post">
    <table>
      <tr>
        <th>Name:</th>
        <td><input type="text" placeholder="Enter name" name="name"/></td>
      </tr>
      <tr>
        <th>Email:</th>
        <td><input type="text" placeholder="Enter Email" name="email"/></td>
      </tr>
      <tr>
        <th>Address:</th>
        <td><input type="text" placeholder="Enter address" name="address"/></td>
      </tr>
      <tr>
        <th>Phone Number:</th>
        <td><input type="text" placeholder="Enter Phone Number" name="phone"/></td>
      </tr>
      <tr>
        <th>Salary:</th>
        <td><input type="text" placeholder="Enter Salary" name="salary"/></td>
      </tr>
      <tr>
        <th>Department</th>
        <td><select name="depart">
          <option>---------------</option>
          <c:forEach items="${listDepartment}" var="department">
            <option value="${department.getId_depart()}"><c:out value="${department.getName_depart()}"/></option>
          </c:forEach>
        </select></td>
      </tr>
      <tr>
        <td><button type="submit">Save</button></td>
        <td><button type="button" onclick="backHome()">Back</button></td>
      </tr>
    </table>
  </form>
</div>
</body>
<script>
  function backHome(){
    window.location="http://localhost:8081/home?";
  }
</script>
</html>
