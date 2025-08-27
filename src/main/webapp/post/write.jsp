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

    <!-- 썸머노트 부트스트랩 : CDN을 이용하여 -->
    <!-- include libraries(jQuery, bootstrap) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" ></script>

    <!-- jquery 최신 버전 -->
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    <!-- include summernote css/js -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.9.1/summernote-bs5.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.9.1/summernote-bs5.min.js"></script>
    <!-- 썸머노트 한국어 패치 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.9.1/lang/summernote-ko-KR.min.js"></script>

</head>

<body>
    <!-- header JSP 불러오기 : webapp 이하 경로부터 작성 -->
    <jsp:include page="/header.jsp"></jsp:include>

    <div class="container">
        <select class="cno">
            <option value="1">뉴스</option>
            <option value="2">이벤트</option>
            <option value="3">FAQ</option>
            <option value="4">튜토리얼</option>
            <option value="5">사용자 리뷰</option>
        </select>
        <input type="text" class="ptitle" placeholder="제목을 입력하세요">
        <textarea class="pcontent" id="summernote" name="editordata"></textarea>
        <button type="button" onclick="onWrite()"> 등록 </button>

    </div>


    <!-- JS 불러오기 : static 이하 경로부터 작성 -->
    <script src="/js/post/write.js"></script>
</body>

</html>