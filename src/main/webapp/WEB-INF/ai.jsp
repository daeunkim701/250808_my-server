<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- 한글 사용 가능하게 해주는 것 -->
<html>
<head>
    <title>AI 너무 좋아!</title>
</head>
<body>

<p>
    <!-- req(request)안에 set된 'data'라는 attribute가 있다면 출력 -->
    <%= request.getAttribute("data") %> <!-- 값을 출력해주는 문법 -->
</p>

</body>
</html>