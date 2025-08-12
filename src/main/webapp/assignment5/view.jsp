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
    연락처 : <span class="wphoneBox"></span> <br>
    대기인원 : <span class="wcountBox"></span> <br>

    <button type="button" onclick="updateTrans()"> 수정 </button>
    <button type="button" onclick="waitingDelete()"> 삭제 </button>

    <script src="/assignment5/view.js"></script>
</body>

</html>