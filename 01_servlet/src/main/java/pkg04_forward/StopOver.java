package pkg04_forward;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StopOver extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  /*
	   * 출발(NewFile.html) ----->  경유지(StopOver.java) -----> 도착지(Destination.java)
	   * 
	   * forward
	   *
	   * 1. 이동할 때 요청과 응답 모두 전달한다.
	   * 2. 이동 경로를 작성할 때 contextPath(서버경로)는 제외하고 작성해야 한다. (URLMapping만 작성한다.)
	   *    서버 안에서의 이동은 서버 주소를 작성할 필요가 없이(이미 서버로 들어 왔기 때문에) URLMapping(회사의 내선 번호 같은 것)만 작성하면 된다.
	   * 3. 클라이언트는 forward 경로를 확인할 수 없다.
	   * 4. forward 하는 경우
	   *     1) 단순 이동 (페이지 이동)
	   *     2) 쿼리 select (쿼리문 SELECT)
	   * 
	   * forward 예시) 
	   *
	   *   고객이 외부에서 전화를 건다.    (NewFile.html  | contextPath + URLMapping | /servlet/stopover | 회사 대표번호) 
	   *   담당자가 유관부서로 연결해준다. (StopOver.java | URLMapping               | /destination      | 회사 내선번호) 
	   *   해당 유관부서에서 요청을 처리한다.     (Destination.java)  - 클라이언트는 서버 내 URLMapping 을 알 수 없다. (내선 번호 모르듯이)
	   */
	  
	   request.getRequestDispatcher("/destination").forward(request, response); // request(요청)와 response(응답) 자체를 전달한다. 
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
