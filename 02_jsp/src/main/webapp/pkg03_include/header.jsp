<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
 request.setCharacterEncoding("UTF-8");
 String title = request.getParameter("title");
%>
<title><%=title%></title> <%-- 받아낸 파라미터를 <title>에서 보여준다. --%>

<!--  custom css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/header.css?dt=<%=System.currentTimeMillis()%>"> <%-- 의미 없는 파라미터를 추가(? 뒤에) => 실행 할 때마다 경로가 달라짐(캐시를 쓰지 않음) => 개발 중에만 추가 후 개발 완료 후에는 삭제해준다. --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/body.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/footer.css">
</head>
<body>

<div class="header-wrap">
  <div>
  
  <a href="<%=request.getContextPath()%>/pkg03_include/main1.jsp">main1</a>
  <a href="<%=request.getContextPath()%>/pkg03_include/main2.jsp">main2</a>
  </div>
</div>
  
  <div class="body-wrap">
  </div>