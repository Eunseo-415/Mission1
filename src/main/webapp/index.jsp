<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.mission1.Models.Wifi" %>
<%@ page import="com.example.mission1.Controllers.WifiDbController" %>
<%@ page import="com.example.mission1.Controllers.WifiDbController" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="index.css">
    <title>Main</title>
    <script type="text/javascript">
        function getLocation(){
            navigator.geolocation.getCurrentPosition(success, fail);
        }
        function success(position){
            document.getElementById("inLat").value = position.coords.latitude;
            document.getElementById("inLng").value = position.coords.longitude;
        }
        function fail(){
            console.log("Cannot find the location");
        }

        function getNearBy(){
            let lat = document.getElementById('inLat').value;
            let lng = document.getElementById('inLng').value;
            let s = lat+'/'+lng;
            let url = 'index.jsp?param=' + encodeURI(s);

            window.location.href = url;
        }

    </script>
</head>
<body>
<h2>와이파이 정보 구하기</h2>
<div class="links">
    <a href="index.jsp">홈</a>
    <i>|</i>
    <a href="history.jsp">위치 히스토리 목록</a>
    <i>|</i>
    <a href="IndexServlet?command=save">Open API 와이파이 정보 가져오기</a>
</div>
<div class="buttons">
    <label>LAT: </label>
    <%if(request.getParameter("param") != null){%>
        <input id="inLat" type="text" value=<%=request.getParameter("param").split("/")[0]%>>
        <label>LNT: </label>
        <input id="inLng" type="text" value=<%=request.getParameter("param").split("/")[1]%>>
    <% } else{%>
        <input id="inLat" type="text" value="0.0">
        <label>LNT: </label>
        <input id="inLng" type="text" value="0.0">
    <%}%>
    <button type="button" id="bt_myposition" onclick="getLocation()">내 위치 가져오기</button>
    <button id="bt_nearby" onclick="getNearBy()">근처 WIFI 정보 보기</button>
</div>
<div class="API_table">
    <table>
        <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        <%if(request.getParameter("param") != null){
                String[] str = request.getParameter("param").split("/");
                WifiDbController wifiDbController = new WifiDbController();
                double lat = Double.parseDouble(str[0]);
                double lng = Double.parseDouble(str[1]);
                List<Wifi> list = new ArrayList<>(wifiDbController.getNearBy(lat, lng));
                for(Wifi wifi: list){%>
                <tr>
                    <td><%=Math.round(wifi.getDistance()*(double)1000)/(double)1000%></td>
                    <td><%=wifi.getX_SWIFI_MGR_NO()%></td>
                    <td><%=wifi.getX_SWIFI_WRDOFC()%></td>
                    <td><%=wifi.getX_SWIFI_MAIN_NM()%></td>
                    <td><%=wifi.getX_SWIFI_ADRES1()%></td>
                    <td><%=wifi.getX_SWIFI_ADRES2()%></td>
                    <td><%=wifi.getX_SWIFI_INSTL_FLOOR()%></td>
                    <td><%=wifi.getX_SWIFI_INSTL_TY()%></td>
                    <td><%=wifi.getX_SWIFI_INSTL_MBY()%></td>
                    <td><%=wifi.getX_SWIFI_SVC_SE()%></td>
                    <td><%=wifi.getX_SWIFI_CMCWR()%></td>
                    <td><%=wifi.getX_SWIFI_CNSTC_YEAR()%></td>
                    <td><%=wifi.getX_SWIFI_INOUT_DOOR()%></td>
                    <td><%=wifi.getX_SWIFI_REMARS3()%></td>
                    <td><%=wifi.getLAT()%></td>
                    <td><%=wifi.getLNT()%></td>
                    <td><%=wifi.getWORK_DTTM()%></td>
                </tr>
                <%}
            } else {%>
        <tr>
            <td id="default" colspan="17" >위치정보를 입력한 후에 조회해 주세요.</td>
        </tr>
        <%}%>
    </table>
</div>
<br/>
</body>
</html>