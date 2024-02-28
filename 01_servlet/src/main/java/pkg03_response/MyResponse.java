package pkg03_response;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyResponse extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // 1. 응답 Content-Type 설정 + UTF-8 인코딩
    //    1) HTML  : text/html         : HTML 데이터
    //    2) XML   : application/xml   : XML 데이터
    //    3) JSON  : application/jason : JSON 데이터
    // HttpServletResponse response : 응답에 사용되는 객체
    
    response.setContentType("text/html; charset=UTF-8"); // 응답의 데이터 타입과 인코딩을 한번에 설정한다.  
                                                         // 세미콜론(;) 으로 항목을 구분한다.
    
    // 2. 응답 출력 스트림 알아내기 (문자 출력 스트림)
    // response 객체가 제공하는 출력 스트림은 PrintWriter 이다.
    PrintWriter out = response.getWriter();  // 출력 스트림은 반드시 try-catch 가 필요한데 이미 예외 처리가 되어 있어 오류가 나지 않는다. (servlet은 자동으로 예외 처리(회피)를 해주나 java 에서는 자동 예외처리가 안되어 직접 개발자가 처리 해주어야한다.)
    out.println("<!DOCTYPE html>");
    out.println("<html lang=\"ko\">"); // Java in HTML(servlet) -> HTML in Java(JSP) (자바 페이지에서 개발 할 경우 왼쪽과 같이 태그를 한땀 한땀 넣어야해서 HTML in Java(HTML 스크립트 내에 Java) 을 선호한다.) 
    out.println("<head>");  
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>Document</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>안녕하세요</h1>");
    out.println("</body>");
    out.println("</html>");
    out.flush();
    out.close();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
