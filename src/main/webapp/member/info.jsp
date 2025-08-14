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
    <link rel="stylesheet" href="/css/member/info.css">

</head>

<body>
    <!-- header JSP 불러오기 -->
    <jsp:include page="/header.jsp"></jsp:include>

    <div id="container">
        <h3> 마이페이지 </h3>
        <div class="mnoBox"></div>
        <div class="midBox"></div>
        <div class="mnameBox"></div>
        <div class="mphoneBox"></div>
        <div class="mdateBox"></div>
        <a href="/member/update.jsp"> 회원정보 수정 </a>     <br>
        <a href="/member/pwdUpdate.jsp"> 비밀번호 수정 </a>  <br>
        <a href="#" onclick="onDelete()"> 회원 탈퇴 </a>     <br>
    </div>

    <!-- JS 불러오기 -->
    <script src="/js/member/info.js"></script>
</body>

</html>