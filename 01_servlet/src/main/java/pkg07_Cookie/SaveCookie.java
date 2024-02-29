package pkg07_Cookie;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SaveCookie extends HttpServlet {

  private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  // 요청 및 요청 파라미터
	  request.setCharacterEncoding("UTF-8");
	  String name = request.getParameter("name");
	  String email = request.getParameter("email");
	  
	  // 쿠키 이름과 값   
	  Cookie cookie1 = new Cookie("name",  URLEncoder.encode(name,"UTF-8"));   // 쿠키 값은 String 타입이다. 공백 등 쿠키 값으로 사용할 수 없는 문자가 있어서 인코딩 후 저장한다. (쿠키 값만 인코딩한다.)
	  Cookie cookie2 = new Cookie("email", URLEncoder.encode(email,"UTF-8"));
	  
	  // 쿠키 유지 시간
	  cookie1.setMaxAge(60 * 60 * 24 * 15); // 15일 (단위 : 초) 
	  // cookie2.setMaxAge(60);             // 생략하면 세션 쿠키가 된다.(세션 쿠키 : 브라우저를 닫으면 지워지는 쿠키를 말한다.) 
	  
	  // 쿠키 저장 경로 (생략하면 ContextPath 가 경로로 사용된다.) (응답 : 서버 -> 클라이언트) 
	  cookie1.setPath("/servlet");            // ContextPath, cookie1.setPath(request.getContextPath());
	  cookie2.setPath("/servlet/saveCookie"); // URLMapping,  cookie2.setPath(request.getRequestURI());
	  
	  // 쿠키 저장(응답)
	  response.addCookie(cookie1);
	  response.addCookie(cookie2);
	  
	  // ReadCooke 서블릿으로 이동하기
	  response.sendRedirect(request.getContextPath() + "/readCookie");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		Cookie cookie1 = new Cookie("name", name);
		
	}
	
}
