<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String userId = request.getParameter("userId");
    String password = request.getParameter("password");

    String sessionInfo = String.valueOf(request.getSession());
    String sessionID = String.valueOf(request.getSession().getId());
    System.out.println("sessionInfo= " + sessionInfo);
    System.out.println("sessionID= " + sessionID);
%>

<p>아이디 : <%=userId%>
</p>
<p>비밀번호 : <%=password%>
</p>
<p><a href="logout.jsp">로그아웃</a></p>
</body>
</html>
