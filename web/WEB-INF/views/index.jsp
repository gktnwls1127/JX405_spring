<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>인덱스</title>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</head>
<body>
<diV class="container">
    <div class="row h-100 align-items-center">
        <div class="row">
            <div class="col-10 align-items-center">
                <form method="post" action="/user/auth">
                    <label for="input-username">아이디</label>
                    <input type="text" name="username" class="form-control" id="input-username">
                    <label for="input-password">비밀번호</label>
                    <input type="password" name="password" class="form-control" id="input-password">
                    <input type="checkbox" name="remember-me">
                    <button type="submit" class="btn btn-primary">로그인</button>
                    <a class="btn btn-outline-info" href="/user/register">회원가입 하기</a>
                </form>
                ${message}
            </div>
        </div>
    </div>
</diV>
</body>
</html>
