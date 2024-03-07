<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- jsp의  contextPath 변수처리  --%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>

  <div class="wrap">
    <select id="type">
      <option value="date">현재날짜</option>
      <option value="time">현재시간</option>
      <option value="datetime">현재날짜와시간</option>
    </select>
    <button id="btn" type="button">요청</button>
  </div>

<%-- jsp의 변수처리  : ${contextPath} => /mvc/ 를 변수처리 함 --%>
  <script src=" ${contextPath}/assets/js/index.js"></script> <!-- <html> 태그 뒤에 <script> 에 경로 추가 -->

</body>
</html>