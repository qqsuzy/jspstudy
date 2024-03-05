<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% String contextPath = request.getContextPath(); // request : 내장객체 (선언하지 않아도 사용 가능) , getContextPath() 메소드 : /jsp 까지 반환 => 변수에 저장하여 ContextPath 사용 가능  %>

<%-- 
   <경로 : action 속성>
   
   /jsp : webapp 까지 (ContextPath)
   /pkg02_bultin_object
   /save.jsp  
--%>

<%-- 총 3개의 파라미터(name 속성) : created-at , title, contents --%>
<form method="POST"
      action="<%=contextPath%>/pkg02_builtin_object/save.jsp">
  <div>
    <label for="created-at">작성일자</label>
    <input type="text" id="created-at" name="created-at" value="<%=LocalDate.now()%> "readonly"> <!-- value 속성을 사용해서 입력하지 않아도 값이 자동으로 들어오도록 함 -->
  </div>         
  <div>
    <label for="title">제목</label>
    <input type="text" id="title" name="title">
  </div>
  <div>
    <textarea rows="5" cols="50" name="contents" placeholder="내용"></textarea>
  </div>
  <div>
    <button type="submit">작성완료</button>
    <button type="reset">다시작성</button>
  </div>     
 </form>

</body>
</html>