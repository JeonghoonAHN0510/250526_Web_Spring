<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Page Title</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' href='/assessment/main.css'>
</head>

<body>
    <!-- 헤더 JSP 불러오기 -->
    <jsp:include page="/assessment/header.jsp"></jsp:include>

    <div>
        <h2> 홈쇼핑 회원 정보 수정 </h2>
        <table border="1">
            <tr>
                <th>회원번호(자동발생)</th>
                <td><input type="text" class="custnoInput"></td>
            </tr>
            <tr>
                <th>회원성명</th>
                <td><input type="text" class="custnameInput"></td>
            </tr>
            <tr>
                <th>회원전화</th>
                <td><input type="text" class="phoneInput"></td>
            </tr>
            <tr>
                <th>회원주소</th>
                <td><input type="text" class="addressInput"></td>
            </tr>
            <tr>
                <th>가입일자</th>
                <td><input type="text" class="joindateInput"></td>
            </tr>
            <tr>
                <th>고객등급[A:VIP, B:일반, C:직원]</th>
                <td><input type="text" class="gradeInput"></td>
            </tr>
            <tr>
                <th>도시코드</th>
                <td><input type="text" class="cityInput"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="button" onclick="updateMember()"> 수정 </button>
                    <button type="button" onclick="viewTrans()"> 조회 </button>
                </td>
            </tr>
        </table>
    </div>

    <!-- 푸터 JSP 불러오기 -->
    <jsp:include page="/assessment/footer.jsp"></jsp:include>

    <script src="/assessment/update.js"></script>
</body>

</html>