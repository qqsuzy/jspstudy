<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<c:set var = "contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
  <%-- 메인페이지 : 게시판으로 이동하는 링크 --%>
  <%-- .brd로 BoardController가 동작하도록 설정해둠 --%>
  <div>
    <a href="${contextPath}/board/list.brd">게시판</a> <%-- mapping 값 => board로 고정, 해당 요청을 BoardController가 받음 --%>
  </div>
  

</body>
</html>