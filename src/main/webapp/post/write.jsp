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

</head>

<body>
    <!-- header JSP 불러오기 : webapp 이하 경로부터 작성 -->
    <jsp:include page="/header.jsp"></jsp:include>

    <div class="container">
        <select class="cno">
            <option value="1">뉴스</option>
            <option value="2">이벤트</option>
            <option value="3">FAQ</option>
            <option value="4">튜토리얼</option>
            <option value="5">사용자 리뷰</option>
        </select>
        <input type="text" class="ptitle">
        <textarea class="pcontent"></textarea>
        <button type="button" onclick="onWrite()"> 등록 </button>

    </div>


    <!-- JS 불러오기 : static 이하 경로부터 작성 -->
    <script src="/js/post/write.js"></script>
</body>

</html>