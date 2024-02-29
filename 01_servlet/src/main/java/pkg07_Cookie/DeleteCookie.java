package pkg07_Cookie;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteCookie extends HttpServlet {

  private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  // 요청 & 요청 파라미터
	  request.setCharacterEncoding("UTF-8");
	  String cookieName = request.getParameter("cookieName");  // ReadCookie.java 에서 cookieName을 파라미터로 받아온다.
	  
	  // 쿠키 삭제 (쿠키 유지 시간이 0인 쿠키를 만들어서 덮어쓰기한다. 지우는 기능 X)
	  Cookie cookie = new Cookie(cookieName, "아무의미없는값");
	  cookie.setMaxAge(0);        // 쿠키 유지시간 0 설정
	  response.addCookie(cookie);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
