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
    <jsp:include page ="/student/header.jsp"></jsp:include>

    <div>
        <h3> 전체 조회 페이지 </h3>
        <table>
            <thead>
                <tr>
                    <th> 번호 </th>
                    <th> 이름 </th>
                    <th> 국어 </th>
                    <th> 수학 </th>
                </tr>
            </thead>
            <tbody id="studentTbody">

            </tbody>
        </table>
    </div>

    <script src="/student/find.js"></script>
</body>

</html>