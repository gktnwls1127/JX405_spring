<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원 가입</title>
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

</head>
<body>
<div class="container">
  <div class="row h-100 align-items-center">
    <div class="row justify-content-center">
      <div class="col-8 justify-content-center">
        <div class="row">
          <div class="col-6">
            <c:if test="${not empty message}">
              <div class="alert alert-dismissible alert-danger">
                <h3>${message}</h3>
              </div>
            </c:if>
          </div>
        </div>
        <form method="post" action="/user/register">
          <div class="row">
            <div class="col justify-content-center">
              <label for="input-username">아이디</label>
              <input type="text" name="username" class="form-control" id="input-username">
            </div>
          </div>
          <div class="row">
            <div class="col">
              <label for="input-password">비밀번호</label>
              <input type="password" name="password" class="form-control" id="input-password">
            </div>
          </div>
          <div class="row">
            <div class="col">
              <label for="input-nickname">닉네임</label>
              <input type="text" name="nickname" class="form-control" id="input-nickname">
            </div>
          </div>
          <div class="row">
            <div class="col">
              <button class="btn btn-primary" type="submit">회원가입</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
