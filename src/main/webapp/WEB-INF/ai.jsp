<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>내가 만든 챗봇</title>
    <!-- OG Tag -->
    <meta property="og:title" content="내가 만든 챗봇">
    <meta property="og:description" content="Gemini 2.0 Flash로 구현한 챗봇">
    <!-- Web Font -->
    <style>
        /* 기본 폰트 설정 */
        @font-face {
            font-family: 'Paperlogy-8ExtraBold';
            src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2408-3@1.0/Paperlogy-8ExtraBold.woff2') format('woff2');
            font-weight: 600;
            font-style: normal;
        }
        body {
            font-family: 'Paperlogy-8ExtraBold';
            background: linear-gradient(135deg, #d0e8ff, #f6f7ff);
            padding: 20px;
            max-width: 600px;
            margin: 50px auto;
            font-size: 1.2rem;
            border-radius: 16px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.15);
        }
        p {
            background: white;
            padding: 12px 18px;
            border-radius: 12px;
            margin-bottom: 15px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
        }
        p:first-of-type {
            border-left: 6px solid #3a8df5; /* 질문 블록 포인트 색 */
        }
        p:nth-of-type(2) {
            border-left: 6px solid #4CAF50; /* 답변 블록 포인트 색 */
        }
        form {
            display: flex;
            gap: 10px;
            margin-top: 20px;
        }
        input[name="question"] {
            flex: 1;
            padding: 10px 14px;
            border: 2px solid #ccc;
            border-radius: 8px;
            font-size: 1rem;
            outline: none;
            transition: border-color 0.3s ease;
        }
        input[name="question"]:focus {
            border-color: #3a8df5;
        }
        button {
            background: #3a8df5;
            color: white;
            border: none;
            padding: 10px 18px;
            border-radius: 8px;
            cursor: pointer;
            font-size: 1rem;
            transition: background 0.3s ease;
        }
        button:hover {
            background: #1e6fe0;
        }
    </style>
</head>
<body>
<p>
    질문 : <%= request.getAttribute("question") %>
</p>
<p>
    답변 : <%= request.getAttribute("data") %>
</p>
<form method="post">
    <input name="question" placeholder="무엇이든 물어보세요...">
    <button>질문하기</button>
</form>
</body>
</html>
