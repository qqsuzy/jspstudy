package pkg05_redirect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class Destination1 extends HttpServlet {

  private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  /*
	   * redirect (요청이 2번이다.)
     *
     * 1. 이동할 때 요청과 응답 모두 전달하지 않는다.
     * 2. 이동 경로를 작성할 때 contextPath(서버경로) 부터 작성해야 한다.
     * 3. 클라이언트는 forward 경로를 확인할 수 있다. 
     * 4. redirect 하는 경우
     *     1) 쿼리 insert 
     *     2) 쿼리 update
     *     3) 쿼리 delete
     *  
     *  redirect 
     *  1번 요청 : insert 주세요
     *  2번 요청 : select로 이동 
     *  
     *  redirect 예시)
     *  블로그 글 작성
     *  1. 글을 작성하고 게시한다. (insert)
     *  2. 게시 후 블로그 게시글 목록을 보여준다. (select : 목록을 select 하도록)
	   */
	  
	  // 요청 인코딩
	  request.setCharacterEncoding("UTF-8");
	  
	  // Destination2가 보낸 요청을 응답한다.
	  String luggage = request.getParameter("luggage");
	  
	  response.sendRedirect("/servlet/destination2?luggage=" +   URLEncoder.encode(luggage, "UTF-8")); // 한글은 전송이 안되기 때문에 URLEncoder로 UTF-8로 인코딩한다.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
