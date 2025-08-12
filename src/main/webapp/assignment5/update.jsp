<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Page Title</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='main.css'>
    <script src='main.js'></script>
</head>

<body>
    <jsp:include page="/assignment5/header.jsp"></jsp:include>

    대기번호 : <span class="wnoBox"></span> <br>
    연락처 : <input type="text" class="wphoneInput" value=""> <br>
    대기인원 : <input type="text" class="wcountInput" value=""> <br>
    <button type="button" onclick="waitingUpdate()"> 수정완료 </button>

    <script src="/assignment5/update.js"></script>
</body>

</html>