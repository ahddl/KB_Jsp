
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //application
    //서브 내부에서 공유되는 객체
    Integer count = (Integer) application.getAttribute("count");

    if (count == null) {
        count = 1;
    } else count++;

    //방문자수 저장
    application.setAttribute("count", count);
%>
<p>현재 까지 방문자 수 : <%=count%>
</p>
</body>
</html>
