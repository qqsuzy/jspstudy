package pkg09_bind;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DataBind2 extends HttpServlet {

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
    
    // HttpSession에 데이터 저장하기
    HttpSession session = request.getSession();
    session.setAttribute("b", "로그인정보");     // HttpSession를 사용하여 페이지 이동을 해도 로그인 정보(상태) 유지, 브라우저를 닫으면 자동로그아웃 됨.
    
    // 세션 유지시간 설정하기 (디폴트 30분 => 작업을 따로 안해주면 적용   ex) 은행 : 10분)
    session.setMaxInactiveInterval(60 * 60);  // 초 단위 (1시간), -1 과 같은 음수를 전달하면 무한으로 활용 가능 
    
    /*
     * web.xml 파일을 이용한 세션 유지시간 설정하기 (30분)
     * 
     * <web-app xmlns="" version="">
     * <session-config>
     *    <session-timout>30</session-timeout>
     * </session-config>
     * </web-app>
     */
    
    // 세션 정보 삭제하기 (세션의 모든 정보가 지워진다.)
    // session.invalidate();
    
    // 데이터 확인 페이지로 이동하기
    response.sendRedirect("/servlet/dataConfirm"); // sendRedirect 메소드를 사용해서 페이지 이동
    
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
