package pkg09_bind;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DataBind1 extends HttpServlet {

  private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  /*
	   * Session : 서버 메모리에 저장되는 정보   => 비교적 보안에 강하다 (웹 페이지들에서 참조해야 하는 공유 정보를 저장해두고 사용하기 위해 세션을 활용함)
	   *           ex) 사용자 로그인 정보, 장바구니 등
	   *            
	   * Cookie : 클라이언트 측 PC에 저장된 정보 => 보안에 취약 (비교적 중요하지 않는 데이터들이 저장됨)
	   * 
	   */
	  
	  /*
	   * 데이터 저장 영역
	   * 
	   * 1. ServletContext      : 컨텍스트 종료(애플리케이션 실행 종료) 전까지 데이터를 유지한다. => 가장 넓은 범위 (극소수 (3))
	   * 2. HttpSession         : 세션 종료(웹 브라우저 종료) 전까지 데이터를 유지한다. (10~20% (2))
	   * 3. HttpServletRequest  : 요청 종료(응답) 전까지 데이터를 유지한다.             (70~80% 사용 (1))
	   */
	  
	  /*
	   * 데이터 처리 메소드
	   * 1. setAttribute(속성, 값) : Object 타입의 값을 저장한다.
	   * 2. getAttribute(속성)     : Object 타입의 값을 반환한다. (꺼내서 쓸 때는 캐스팅이 필요할 수 있다.) 
	   * 3. removeAttribute(속성)  : 제거한다.
	   */
	  
	  // ServletContext에 데이터 저장하기
	  ServletContext servletContext = request.getServletContext(); // servletContext에 데이터를 저장하게 되면 프로젝트 내에 모든 Servlet을 돌아다니면서 데이터를 조회할 수 있다. 
	  servletContext.setAttribute("a", "전체방문자수" ); // a : ServletContext에 저장한 이름
	  
	  // 데이터 확인 페이지로 이동하기
	  response.sendRedirect("/servlet/dataConfirm"); // sendRedirect 메소드를 사용해서 페이지 이동
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
