<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- ** 모든 JSP 파일에 taglib 필요하여 반드시 추가해야함 ** --%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  <%-- 가져다 쓰고 싶은 태그 라이브러리에 따라 uri 다름, prefix 값은 태그 라이브러리에 따라 고정되어 있음 ex) core => c --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 
  <c:set> 태그
  1. Bind 영역에 데이터를 저장할 때 사용한다.
  2. 형식
   <c:set var="속성명" value="값" scope="Bind 영역"></c:set>
 --%>
 
 <c:set var="a" value="1" scope="page"></c:set>
 <c:set var="a" value="2" scope="request"></c:set>
 <c:set var="a" value="3" scope="session"></c:set>
 <c:set var="a" value="4" scope="application"></c:set>
 <hr>
 <%-- 
  
  아래 코드(Java)를 대체하는 코드 ↑     => Java 코드를 태그화함
  ---------------------------------
  pageContext.setAttribute("a", 1);
  request.setAttribute("a", 2);
  session.setAttribute("a", 3);
  application.setAttribute("a", 4);
 
  --%>
 
 <div>${pageScope.a}</div>
 <div>${requestScope.a}</div>
 <div>${sessionScope.a}</div>
 <div>${applicationScope.a}</div>
 
 <%-- 자주 사용하게 될 <c:set>  => value 는 둘 다 사용가능 --%>
 <%-- contextPath 변수에 저장하기 --%>
 <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page"></c:set>
 <c:set var="contextPath" value=" <%=request.getContextPath()%>"></c:set> <%-- scope="page" 는 디폴트 => 생략가능 --%>
 
 <%-- 나홀로 태그로 바꾸기 => 열어주는 태그 끝에 슬래시(/) 추가 --%>
 <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page"/>
 
 
 <div>${contextPath}</div>
 
</body>
</html>