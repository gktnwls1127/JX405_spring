<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
id : ${result.id} <br>
username : ${result.username} <br>
nickname : ${result.nickname} <br>
<c:if test="${logInId eq result.id}">
  <a href="/user/update/${result.id}">수정하기</a>
  <a href="/user/delete/${result.id}">삭제하기</a>
</c:if>
</body>
</html>
