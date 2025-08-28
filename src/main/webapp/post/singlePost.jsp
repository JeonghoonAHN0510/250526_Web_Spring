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
        <div class="content">

        </div>
        <div class="reply">
            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>댓글내용</th>
                        <th>작성자</th>
                        <th>작성일</th>
                    </tr>
                </thead>
                <tbody class="replyTbody">

                </tbody>
            </table>

            <textarea class="rcontent"></textarea>
            <button type="button" onclick="onWriteReply()">댓글등록</button>
        </div>
    </div>

    <!-- JS 불러오기 : static 이하 경로부터 작성 -->
    <script src="/js/post/singlePost.js"></script>
</body>

</html>