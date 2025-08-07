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
    <jsp:include page = "/waiting/header.jsp"></jsp:include>

    <div>
        <h3> 대기등록 페이지 </h3>
        연락처 : <input type="text" class="wphone"> <br>
        대기인원 : <input type="text" class="wcount"> <br>
        <button type="button" onclick="add()"> 대기등록 </button>
    </div>

    <script src="/waiting/add.js"></script>
</body>
</html>