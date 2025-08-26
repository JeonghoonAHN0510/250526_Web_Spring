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

    <div id="container">
        <div>
            <button onclick="location.href='write.jsp'"> 글쓰기 </button>
        </div>
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody class="postTbody">

                <!--
                [ 테스트용 샘플 ]
                <tr>
                    <td> 1 </td>
                    <td> 테스트제목</td>
                    <td> 유재석 </td>
                    <td> 2025-08-26 </td>
                    <td> 5 </td>
                </tr>
                -->

            </tbody>
        </table>
        <!-- 페이지 버튼 구역 -->
        <div style="width: 300px;">
            <ul class="pageBtnBox" style="display: flex; justify-content: space-between;">

                <!--
                [ 테스트용 샘플 ]

                <li><a href="post.jsp?cno=1&page=1">1</a></li>
                <li><a href="post.jsp?cno=1&page=2">2</a></li>
                <li><a href="post.jsp?cno=1&page=3">3</a></li>
                <li><a href="post.jsp?cno=1&page=4">4</a></li>
                <li><a href="post.jsp?cno=1&page=5">5</a></li>
                -->

            </ul>
        </div>
        <!-- 검색 구역 -->
        <div style="display: flex;">
            <div>
                <select class="key">
                    <option value="ptitle"> 제목 </option>
                    <option value="pcontent"> 내용 </option>
                </select>
            </div>
            <div>
                <input type="text" class="keyword" placeholder="검색어를 입력하세요.">
            </div>
            <div>
                <button type="button" onclick="onSearch()"> 검색 </button>
            </div>
        </div>
    </div>


    <!-- JS 불러오기 : static 이하 경로부터 작성 -->
    <script src="/js/post/post.js"></script>
</body>

</html>