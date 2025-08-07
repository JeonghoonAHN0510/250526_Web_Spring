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
    <!-- 현재 jsp에서 다른 jsp 파일 불러오기 -->
    <jsp:include page = "/waiting/header.jsp"></jsp:include>

    <div>
        <h3> 메인 페이지 </h3>
        <p> localhost:8080/waiting/index.jsp </p>
    </div>
    
    
</body>

</html>