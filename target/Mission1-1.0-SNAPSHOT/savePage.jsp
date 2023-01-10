<%@ page import="com.example.mission1.ApiExplorer" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <style>
        body{
            text-align: center;
            font-family: Arial, Helvetica, sans-serif;
        }
    </style>
</head>
<body>
    <%ApiExplorer apiexplorer = new ApiExplorer();%>
    <h1><%=apiexplorer.getTotalNum()%>개의 와이파이정보를 정상적으로 저장하였습니다.</h1>
    <a href="index.jsp">홈으로 가기</a>
</body>
</html>
