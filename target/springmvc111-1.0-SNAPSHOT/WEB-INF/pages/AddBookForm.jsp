<%--
  Created by IntelliJ IDEA.
  User: pprashant
  Date: 6/8/2022
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<html>
<head>
    <title>Book</title>
</head>
<body>
<h1>${msg}-${tempBook.name}-${tempBook.author}</h1>
<form method="post" action="<%=request.getContextPath()%>/books/addbook">
    <input type="text" name="name"/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
