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
        <h3> 제품 등록 </h3>
        <form id="productForm">
            제품명 : <input type="text" name="pname">                  <br>
            제품가격 : <input type="text" name="pprice">               <br>
            제품설명 : <input type="text" name="pcomment">             <br>
            제품이미지 : <input type="file" multiple name="uploads">   <br>
            판매위치(카카오지도API)
            <div id="map" style="width:100%;height:350px;"></div>
            <p><em>지도를 클릭해주세요!</em></p>
            <div id="clickLatlng"></div>
            <button type="button" onclick="onCreate()"> 제품등록 </button>
        </form>
    </div>
    <!-- KAKAO 지도 API JS -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=919404b20e4cfe7a95976c5ee731c5de"></script>
    <!-- JS 불러오기 : static 이하 경로부터 작성 -->
    <script src="/js/product/create.js"></script>
</body>

</html>