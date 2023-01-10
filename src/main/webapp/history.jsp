<%@ page import="com.example.mission1.Controllers.HistoryDbController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.mission1.Models.History" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="index.css">
    <title>History</title>
    <script type="text/javascript">
        function btnDeleteClicked(id){
            document.location = 'HistoryServlet?delete_id=' + id;
        }
    </script>
</head>
<body>
    <h2>위치 히스토리 목록</h2>
    <div class="links">
        <a href="index.jsp">홈</a>
        <i>|</i>
        <a href="history.jsp">위치 히스토리 목록</a>
        <i>|</i>
        <a href="IndexServlet?command=save">Open API 와이파이 정보 가져오기</a>
    </div>
    <div class="API_table">
        <table>
            <tr>
                <th>ID</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>조회일자</th>
                <th>비고</th>
            </tr>
            <%HistoryDbController historyDbController = new HistoryDbController();
            List<History> list = new ArrayList<>(historyDbController.getAll());
            for(History element : list){%>
            <tr>
                <td><%=element.getId()%></td>
                <td><%=element.getLat()%></td>
                <td><%=element.getLnt()%></td>
                <td><%=element.getDate_time()%></td>
                <td><button id="btn_delete" onclick="btnDeleteClicked(<%=element.getId()%>)">삭제</button></td>
            </tr>
           <%}%>
        </table>
    </div>

</body>
</html>
