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

    <button type="button" onclick="writeTrans()"> 대기등록 </button> <br><br>

    <table border="1">
        <thead>
            <tr>
                <th>대기번호</th>
                <th>연락처</th>
                <th>대기인원</th>
                <th>등록일시</th>
            </tr>
        </thead>
        <tbody id="waitingTbody">

        </tbody>
    </table>

    <script src="/assignment5/list.js"></script>
</body>

</html>