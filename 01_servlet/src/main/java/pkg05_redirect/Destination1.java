package pkg05_redirect;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Destination1 extends HttpServlet {

  private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  /*
	   * redirect (요청(request)이 2번이다.)
     *
     * 1. 이동할 때 요청과 응답 모두 전달하지 않는다.
     * 2. 이동 경로를 작성할 때 contextPath(서버경로) 부터 작성해야 한다.
     * 3. 클라이언트는 forward 경로를 확인할 수 있다. 
     * 4. redirect 하는 경우 
     *     1) 쿼리 insert  
     *     2) 쿼리 update          => DB가 변경되는 작업 후에는 redirect 진행
     *     3) 쿼리 delete
     *  
     *  redirect 
     *  1번 요청 : insert 주세요
     *  2번 요청 : select로 이동 
     *  
     *  
     *  redirect 예시)
     *  
     *  블로그 글 작성
     *  글을 작성하고 게시글 업로드 (insert) => 게시 완료(업데이트 된 새목록 보여달라 요청) => 블로그 게시글 목록 보여줌 (select) => 새목록엔 insert 된 데이터 확인 가능 
     *  -----------------------------------     -----------------------------------------      -----------------------------------
     *   ⓛ request(요청)   ② redirect                   ③ request (요청)                            ④ response (응답)            
     *  
     *  2. 게시 후 블로그 게시글 목록을 보여준다. (select : 목록을 select 하도록)
     *  
     *  ** 목적지 2곳 => 요청 2번 이루어짐
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
