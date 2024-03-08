<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<style>
  .row > span {  
    display: inline-block;
  }
  .row > span:nth-of-type(2) {
    width: 150px;
  }
  .row > span:nth-of-type(3) {
    width: 100px;
  }
</style>
</head>
<body>

  <div>
    <a href="${contextPath}/board/write.brd">새게시글작성</a>
  </div>

  <hr>
  
  <div>
    <span>게시글 개수</span>
    <span>${boardCount}</span> 
  </div>
  
  
  <div>
    <c:if test = "${empty boardList}">  <%-- boardList가 비어있는지 체크 --%>
    <div>작성된 게시글이 없습니다. 첫 게시글의 주인공이 되어보세요.</div>
   </c:if>
     <c:if test="${not empty boardList}">
      <c:forEach items="${boardList}" var="board">    <%-- for문 안에 id 속성을 넣을 때 주의! id는 유일한 식별자이기에 class로 식별자 구분 --%>
        <div class="row">
       <span><input type="checkbox" class="chk-each" value="${board.board_no}"></span>
          <span><a href="${contextPath}/board/detail.brd?board_no=${board.board_no}">${board.title}</a></span>
          <span>${board.created_at}</span>
        </div>
      </c:forEach>
      <div>
         <button type="button" id="btn-remove">선택삭제</button>
      </div>
      <script>
      const chkEach = $('.chk-each'); // jQuery wrapper로 묶었기 때문에 chkEach 는 jQuery 객체
      const btnRemove = $('#btn-remove'); 
      btnRemove.on('click', (evt)=>{
    	  if(!confirm('선택한 게시글을 삭제할까요?')){
    		  return;
        	}
    	  let array = [];
    	  $.each(chkEach, (i, elem)=>{
    		  if(elem.checked){ // if($(elem).is(':checked')) => JQuery  elem 은 chkEach에서 꺼낸 요소이지만 jQuery가 아닌 JS로 JQuery 
    			  array.push(elem.value);
        		}
        	})
        	// array           === [3, 2, 1] => 배열을 서버로 넘기는 방법은 없음
        	// array.join(',') ===  '3,2,1'  => array 를 String 타입으로 바꾼다(파라미터는 String 타입으로 넘어가기 때문)
        	location.href = '${contextPath}/board/removes.brd?param=' + array.join(',');
        	// console.log(array);
        })
      </script>
    </c:if>
  </div>

</body>
</html>