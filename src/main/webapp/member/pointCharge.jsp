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
        
        <input type="radio" name="point" value="1000" id="1000">1,000원
        <input type="radio" name="point" value="5000" id="5000">5,000원
        <input type="radio" name="point" value="10000" id="10000">10,000원

        <button type="button" onclick="payment()"> 결제 </button>
        
    </div>

    <!-- 포트원 API JS -->
    <script src="https://cdn.portone.io/v2/browser-sdk.js"></script>
    <!-- JS 불러오기 : static 이하 경로부터 작성 -->
    <script src="/js/member/pointCharge.js"></script>
</body>

</html>