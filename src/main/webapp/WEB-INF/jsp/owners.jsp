<%--
  Created by IntelliJ IDEA.
  User: furkan
  Date: 7.12.2021
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type"  content="text/html "  charset=UTF-8">
    <title>Title</title>
</head>
<body>

    <table>
        <thead>
        <tr style="font-weight: bold"  bgcolor="#add8e6">
            <td>Id</td>
            <td>FirstName</td>
            <td>LAstNAme</td>
        </tr>
        </thead>
        <c:forEach  items="${owners}" var="owner"  varStatus="status">
        <tr bgcolor=${status.index % 2 == 0?'white':'lightgray'}>
            <td>
                ${owner.id}
            </td>
            <td>
                    ${owner.firstname}
            </td>
            <td>
                    ${owner.lastname}
            </td>


        </tr>
        </c:forEach>
    </table>
</body>
</html>
