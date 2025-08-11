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
        <h3> 게시물 전체 조회 페이지 </h3>
        <a href="/exam12/write.jsp"> 글쓰기 </a>
        <table border="1">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>내용</th>
                    <th>작성자</th>
                </tr>
            </thead>
            <tbody id="boardTbody">
            </tbody>
        </table>
    </div>

    <!-- Spring과 통신할 JS 불러오기 -->
    <script src="/exam12/list.js"></script>
</body>

</html>