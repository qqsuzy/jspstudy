package pkg09_bind;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DataBind3 extends HttpServlet {

  private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

	  /************ 중요! 통으로 외우기
	   * 0 ***********/
	  // setAttribute 메소드로 request 에 데이터 저장 => forward 해서 데이터를 가지고 이동한다.
    
    // HttpServletRequest에 데이터 저장하기 (이미 doGet 메소드의 파라미터에 생성되어 있어서 선언하지 않아도 된다.)
	  request.setAttribute("c", "일반데이터" ); 
    
    // 데이터 확인 페이지로 이동하기
    // response.sendRedirect("/servlet/dataConfirm"); // sendRedirect 메소드를 사용해서 페이지 이동
	  request.getRequestDispatcher("/dataConfirm").forward(request, response); // "/dataConfirm" : 서버 내부 경로 주소만 적어줌 -> request, response를 넘겨줌 
	} 
	// request에 저장을 시키고 forward 할 것! (request는 데이터를 저장할 때도 사용됨 => 웹의 70~80% 는 request에 저장) 
	// 데이터를 request에 SELECT 결과 (DB의 SELECT , 목록보기/상세보기) 를 저장하고, forward 하게 되면 원하는 페이지로 잘 전달 됨
	// response 에 저장해서 전달하면 데이터가 넘어가지 않는다. (redirect는 불가)

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
