<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <form method="post" action="/board/write">
    제목 : <input type="text" name="title">
    내용 : <input type="text" name="content">
    <br>
    <br>
    <button type="submit">작성하기</button>
  </form>
</body>
</html>
