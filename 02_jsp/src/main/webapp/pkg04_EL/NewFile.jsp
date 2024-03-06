<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="pkg04_EL.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 
  EL
  1. Expression Language (표현 언어)
  2. Bind 영역에 저장된 값을 나타낼 수 있다.
  3. JSP script element 중 하나인 표현식 (<%=값%>)을 대체할 수 있다.
  4. 형식
    ${값}
  
 **  javaScript(ES6)의 템플릿 리터럴과 동일함 ${ } => 변수를 나타냄  
 **  EL 로 표현하면 JavaScript와 Java 가 같은 표현을 하기 때문에 파악하기도 수월함 (백/프론트 개발자 모두)

--%>

<%--
  JSP Bind 영역 (데이터 저장 영역)
  1. pageContext : this(NewFile_jsp)    : 현재 JSP 인 NewFile.jsp(현재 파일)에서만 접근 가능하다. (Java 의 this 와 같음)
  2. request     : HttpServletRequest   : 응답 전까지 접근 가능하다. (일회용 저장소)                                       ┐  ▶ 가장 많이 사용됨!        
  3. session     : HttpSession          : 브라우저 닫기 전 까지 접근 가능하다. (디폴트 time = 30분)                        ┘
  4. application : ServletContext       : 애플리케이션 종료 전(서비스 중지)까지 접근 가능하다. (가장 넓은 범위의 Life Cycle) 
 
  ** Bind 영역에 저장되어 있어야 EL 요소 사용가능

   * JSP 를 실행하지만 Java 파일로 바뀌어 실행됨  ( NewFile_jsp --> NewFile_jsp.java ) 
   * this 가 의미하는 것은 파일 자체를 의미하는데 NewFile_jap 를 의미 (Java 의 클래스와 같음)
   * JSP 는 클래스처럼 보이지는 않지만 클래스임 
   
 --%>
 
 <%--
  JSP Bind 영역의 우선 순위
  1. 동일한 이름의 속성 (Attribute)이 여러 영역에 존재하는 경우 우선적으로 사용되는 속성이 있다.
  2.  높은 순위                         낮은 순위
     pageContext > request > session > application (사용 범위가 좁을 수록 순위가 높다.)
  --%>
  
  <%--
    JSP Bind 영역의 EL 내장 객체
    1. pageContext  : pageScope
    2. request      : requestScope
    3. session      : sessionScope
    4. application  : applicationScope
   --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- JSP Bind 영역에 데이터 저장하기 --%>
<%
  pageContext.setAttribute("a", 1);
  request.setAttribute("a", 2);
  session.setAttribute("a", 3);
  application.setAttribute("a", 4);
%>

<%-- JSP Bind 영역의 우선 순위 확인 --%>
<div>${a}</div>
<hr>

<%-- JSP Bind 영역의 EL 내장 객체 확인 --%>
<div>${pageScope.a}</div>
<div>${requestScope.a}</div>
<div>${sessionScope.a}</div>
<div>${applicationScope.a}</div>
<hr>

<%-- JSP Bind 영역에 저장된 Java 객체 정보 확인 --%>
<%
  Book book = Book.builder()
                  .title("소나기")
                  .author("황순원")
                  .price(10000)
                  .build();

  pageContext.setAttribute("book", book);  // 객체를 bind 영역에 저장시킴
%>
<div>${book.title}</div>  <%-- book.getTitle() 방식으로 호출된다. --%>
<div>${book.author}</div> <%-- book.getauthor() --%>
<div>${book.price}</div>  <%-- book.getprice() --%>
<hr>
<%-- 
                                  
<div>${book.getTitle()}</div>       ****** 중요 ******
 =>  ** 잘못된 코드                 * JSP에서 사용할 때는 필드 이름만 사용해야함!! 
                                    * 필드 이름을 부르지만 실제로 부르는 것은 Getter로 반드시 @Getter 생성해야 함!!
                                    * EL 내부에 들어오면서 자동 호출되므로 개발자가 직접 호출할 필요가 없음

--%>   

<%-- JSP Bind 영역에 저장된 Map 정보 확인 --%>
<% 
  Map<String, Object> map = Map.of("title", "어린왕자", "author", "생텍쥐베리", "price", 20000);
  pageContext.setAttribute("map", map);
  
%>
<div>${map.title}</div>       <%-- Map 의 key 값(map)을 그대로 사용하여 호출한다. --%>
<div>${map.author}</div>
<div>${map.price}</div>
<hr>

<%-- List 에 Book 객체를 3개 저장하고 EL로 확인하기 --%>
<%
  List<Book> books = Arrays.asList(
      new Book("소나기", "황순원", 10000),
      new Book("운수좋은날", "현진건", 15000),
      new Book("어린왕자", "생텍쥐베리", 20000)
      );

pageContext.setAttribute("books", books);
%>

<div>${books.get(0).title}</div>  <%-- Java 처럼 get 메소드로 가져올 수 있음 => 접근이 된다는 정도만 확인 (중요도 ↓) --%>
<div>${books.get(0).author}</div>  
<div>${books.get(0).price}</div>  
<div>${books[1].title}</div>
<div>${books[1].author}</div>
<div>${books[1].price}</div>
<hr>

<%-- EL 연산자 --%>
<%
  pageContext.setAttribute("a", 5);
  pageContext.setAttribute("b", 2);
%>
<%-- 산술 연산자 --%> 
<div>${a + b}</div>
<div>${a - b}</div>
<div>${a * b}</div>
<div>${a / b}, ${a div b}</div> <%-- EL 전용 연산자 존재 --%>
<div>${a % b}, ${a mod b}</div> <%-- EL 전용 연산자 존재 --%>
<hr>

<%-- 일반 연산자, EL 전용 연산자 --%>
<%-- 비교 연산자 --%> 
<div>${a > b},  ${a gt b}</div>
<div>${a >= b}, ${a ge b}</div>
<div>${a < b},  ${a lt b}</div>
<div>${a <= b}, ${a le b} </div>
<div>${a == b}, ${a eq b}</div>
<div>${a != b}, ${a ne b}</div>
<hr>

<%-- 논리 연산자 --%>
<div>${a == 5 && b == 5}, ${a eq 5 and b eq 5}</div>
<div>${a == 5 || b == 5}, ${a eq 5 or b eq 5}</div>
<div>${!(a == 5)}, ${not(a eq 5)}</div>
<hr>

<%-- 조건 연산자 --%>
<div>${a > 0 ? "양수" : "음수"}</div>
<hr>

<%-- 
  request 영역 사용 시 주의사항 (EL 상에서 속성, 파라미터는 각각 다르게 처리해야 함)
  
  1. 속성(Attribute)
      request.setAttribute("number" , 10);
      request.getRequestDispather("/URLMapping").forward(request, response);
      
      ${number}, 숫자 타입
      
  2. 파라미터(Parameter)
     response.sendRedirect("/contextPath/URLMapping?number=10");
     
     ${param.number}, 문자열 타입 
      ------
     *  EL의 파라미터 객체 이용해야함 => param
  
  
    속성     10  > 5 : true 
    파라미터 "10" > 5 : false   => 10 자체를 비교하는 것이 아니고 1과 0 각각 크기 비교를 함 (사전 편찬 순과 같음)
    
    
    속성      10 - 5 > 0    ┓  true
    파라미터 "10" - 5 > 0   ┛   => 크기 비교가 다소 까다로운 편이며 크기 비교를 위해 일정한 값을 빼서 비교를 해야함  
  
 --%>
</body>
</html>
