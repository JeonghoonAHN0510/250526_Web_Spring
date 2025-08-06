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

        <!-- 헤더 파일 -->
        <jsp:include page = "/example/header.jsp"></jsp:include>

        <h4> JSP 파일 내 JSP 파일 포함하기 -> HTML는 안되는 기능 </h4>
        <h3> 메인페이지 : localhost:8080/example/home.jsp </h3>
        <h3> home.jsp </h3>

        <!-- 푸터 파일 -->
        <jsp:include page = "/example/footer.jsp"></jsp:include>
    </body>

    </html>