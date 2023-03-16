<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="row h-100 align-items-center">
        <div class="col-10">
            <div class="row">
                <div class="col">
                    <table class="table table-hover table-striped">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>수정일</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="item">
                            <tr onclick="location.href = '/board/showOne/${item.id}'">
                                <td>${item.id}</td>
                                <td>${item.title}</td>
                                <td>${item.writerNickname}</td>
                                <td>${item.entryDate}</td>
                                <td>${item.modifyDate}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="5">

                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <a class="btn btn-primary" href="/board/write">글 작성하기</a>
                    <a class="btn btn-outline-info" href="/user/showOne/${logIn.id}">회원정보</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
