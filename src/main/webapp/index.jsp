<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head th:replace="/layout :: head">
    <title>Home page</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="tours">Hello Servlet</a>
</body>
</html>