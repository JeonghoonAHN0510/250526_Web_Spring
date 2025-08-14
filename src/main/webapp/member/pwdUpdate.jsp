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
    <link rel="stylesheet" href="/css/member/pwdUpdate.css">

</head>

<body>
    <!-- header JSP 불러오기 -->
    <jsp:include page="/header.jsp"></jsp:include>

    <div id="container">
        <h3> 비밀번호 수정 페이지 </h3>
        <div> 기존 비밀번호 : <input type="password" class="oldPwd"></div>
        <div> 새로운 비밀번호 : <input type="password" class="newPwd"></div>
        <button type="button" onclick="onPwdUpdate()"> 비밀번호 수정 </button>
    </div>

    <!-- JS 불러오기 -->
    <script src="/js/member/pwdUpdate.js"></script>
</body>

</html>