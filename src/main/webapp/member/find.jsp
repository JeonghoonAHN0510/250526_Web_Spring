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
    <link rel="stylesheet" href="/css/member/find.css">

</head>

<body>
    <!-- header JSP 불러오기 -->
    <jsp:include page="/header.jsp"></jsp:include>

    <div id="container">
        <h3> 아이디 찾기 </h3>
        이름 : <input type="text" class="findIdmname" placeholder="이름 입력"> <br>
        연락처 : <input type="text" class="findIdmphone" placeholder="연락처 입력">
        <button type="button" onclick="findId()"> 아이디 찾기 </button>
        <br>
        <h3> 비밀번호 찾기 </h3>
        아이디 : <input type="text" class="findPwdmid" placeholder="아이디 입력"> <br>
        연락처 : <input type="text" class="findPwdmphone" placeholder="연락처 입력">
        <button type="button" onclick="findPwd()"> 비밀번호 찾기 </button>
    </div>

    <!-- JS 불러오기 -->
    <script src="/js/member/find.js"></script>
</body>

</html>