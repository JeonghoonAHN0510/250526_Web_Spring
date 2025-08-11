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
    <!-- 헤더 jsp 불러오기 -->
    <jsp:include page="/exam12/header.jsp"></jsp:include>

    <div>
        <h3> 게시물 등록 페이지 </h3>
        내용 : <textarea class="bcontent"></textarea> <br>
        작성자 : <input type="text" class="bwriter">
        <button type="button" onclick="boardWrite()"> 등록 </button>
    </div>


    <script src="/exam12/write.js"></script>
</body>

</html>