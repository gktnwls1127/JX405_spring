<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <form method="post" action="/user/update">
    <input type="hidden" name="id" value="${result.id}">
    새로운 닉네임 : <input type="text" name="nickname" value="${result.nickname}"> <br>
    현재 비밀번호 : <input type="password" name="oldPassword" > <br>
    새로운 비밀번호 : <input type="password" name="newPassword" > <br>
    <button type="submit">수정하기</button>
  </form>
${message}
</body>
</html>
