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
        <h3> [1] 인천 부평구 주유소 현황 API </h3>
        <table border="1" style="text-align: center;">
            <thead>
                <tr>
                    <th> 연번 </th>
                    <th> 상호 </th>
                    <th> 업종 </th>
                    <th> 전화번호 </th>
                    <th> 주소</th>
                </tr>
            </thead>
            <tbody id="dataTbody">

            </tbody>
        </table>

        <h3> [2] 사업자등록정보 상태조회 </h3>
        <input type="text" placeholder="'-'없이 사업자번호 입력" class="b_no">
        <button type="button" onclick="dataAPI2()"> 조회 </button>
    </div>

    <!-- JS 불러오기 : static 이하 경로부터 작성 -->
    <script src="/js/datago/data.js"></script>
</body>

</html>