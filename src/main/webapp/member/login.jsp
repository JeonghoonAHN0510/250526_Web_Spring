<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Page Title</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>

    <!-- CSS 불러오기 : static 이하 경로부터 작성 -->
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/member/login.css">

</head>

<body>
    <!-- header JSP 불러오기 -->
    <jsp:include page="/header.jsp"></jsp:include>

    <div id="container">
        <h3> 로그인 </h3>
        아이디 : <input type="text" class="idInput" id="idInput" placeholder="아이디 입력"> <br>
        비밀번호 : <input type="password" class="pwdInput" id="pwdInput" placeholder="비밀번호 입력"> <br>
        <button type="button" onclick="login()"> 로그인 </button> <br>
        <a href="/member/signup.jsp"> 회원가입 </a>
        <a href="/member/find.jsp"> 아이디/비밀번호 찾기 </a>
    </div>

    <!-- JS 불러오기 -->
    <script src="/js/member/login.js"></script>
</body>

</html>