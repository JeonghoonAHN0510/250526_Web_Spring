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
    <link rel="stylesheet" href="/css/member/signup.css">

</head>

<body>
    <!-- header JSP 불러오기 -->
    <jsp:include page="/header.jsp"></jsp:include>

    <div id="container">
        <h3> 회원가입 페이지 </h3>
        <form id="signupForm">
            아이디 : <input type="text" name="mid" onkeyup="idCheck()"> <br> <div class="idCheck"></div>
            <!-- onkeyup : 키보드에서 손을 땠을 때, 발동 -->
            비밀번호 : <input type="password" name="mpwd"> <br>
            이름 : <input type="text" name="mname"> <br>
            연락처 : <input type="text" name="mphone" onkeyup="phoneCheck()"> <br> <div class="phoneCheck"></div>
            프로필 사진 : <input type="file" name="upload"> <br>
            <button type="button" onclick="signup()"> 회원가입 </button>
            <a href="/member/login.jsp"> 로그인하기 </a>
            <a href="/member/find.jsp"> 아이디/비밀번호 찾기 </a>
        </form>
    </div>

    <!-- JS 불러오기 -->
    <script src="/js/member/signup.js"></script>
</body>

</html>