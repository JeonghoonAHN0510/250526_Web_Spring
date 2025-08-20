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

        <div id="map" style="width:100%;height:350px;"></div>

    </div>


    <!-- KAKAO 지도 API JS -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=919404b20e4cfe7a95976c5ee731c5de&libraries=clusterer"></script>
    <!-- JS 불러오기 : static 이하 경로부터 작성 -->
    <script src="/js/kakao/position.js"></script>
    <script src="/js/product/list.js"></script>
</body>

</html>