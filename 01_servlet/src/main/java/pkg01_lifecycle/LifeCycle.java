package pkg01_lifecycle;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Servlet
 * 
 * 1. 웹 브라우저를 통해서 실행되는 Java 클래스이다.
 * 2. 클라이언트의 요청을 받는 Java 클래스이다. (온갖 종류의 요청을 다 받는다.)
 * 3. jakarta.servlet.http.HttpServlet 클래스를 상속 받는다.
 *    (Tomcat 이 지원하는 클래스이므로 Tomcat 이 없으면 실행할 수 없다.)
 * 4. 동일한 프로젝트(컨텍스트 context )에서는 동일한 이름의 Servlet이 존재할 수 없다. 
 *    (패키지가 다르면 자바의 일반 클래스는 동일한 클래스명을 사용할 수 있었으나 Servlet은 동일한 이름이 불가능하다. )
 *    (Servlet이 저장되는 장소가 JSP/Servlet Container(톰캣) 이기 때문이다.)
 * 
 */

/*
 * 실행 주소 (URL)
 * 1. 형식
 *    protocol://host:port/contextPath/URLMapping
 * 2. contextPath
 *    1) 프로젝트 경로를 의미한다. (슬래시 (/)를 반드시 포함해야 한다.)
 *    2) 프로젝트를 생성할 때 결정한다. 
 *    3) 프로젝트 속성(Properties)에서 변경할 수 있다. 
 *       프로젝트 우클릭 - 속성 - Web Project Settings
 * 3. URLMapping
 *    1) 프로젝트 내부 경로를 의미한다.
 *    2) Servlet을 생성할 때 결정한다.
 *    3) 변경 방법
 *      (1) @WebServlet Annotation
 *          클래스 상단에 Annotation을 추가한다. 
 *      (2) WEB-INF/web.xml  
 *          src -> main -> webapp -> WEB-INF -> xeb.xml 파일을 열어서 <url-pattern>/URLMapping</url-pattern> 의 URLMapping 을 수정한다.
 */

@WebServlet("/life") /* http://localhost:8080/servlet/life 주소를 입력하면 현재 Servlet이 실행된다 (라는 의미를 담고 있는 Annotation 이다.) */ 
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     * 생성자 (Construtor)
     * 1. Servlet 생성할 때 호출된다.
     * 2. 생성자 호출 이후에는 자동으로 init() 메소드가 호출된다.
     * 
     */
    public LifeCycle() { 
        super();
        System.out.println("LifeCycle 생성자 호출");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 * 
	 * 
	 * init() 메소드
	 * 1. Servlet 환경 설정을 담당하는 메소드이다. 
	 * 2. init() 메소드 호출 뒤 자동으로 service() 메소드가 호출된다.
	 */
	public void init(ServletConfig config) throws ServletException {
	  System.out.println("init() 메소드 호출");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * service()   ** 중요! 꼭 외우기! **
	 * 1. 클라이언트로부터 요청을 받을 수 있다. (매개변수 HttpServletRequest request)
	 * 2. 클라이언트에게 응답할 수 있다. (매개변수 HttpServletResponse response)
	 * 3. service() 메소드가 있으면 여기서 요청과 응답을 해결한다.
	 * 4. service() 메소드가 없으면 doGet() 또는 doPost() 메소드가 요청에 따라 호출된다.
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  System.out.println("service() 메소드 호출"); 
	  switch (request.getMethod()) { // 요청 정보가 담겨있는 객체의 메소드 호출
	  case "GET":
	    doGet(request, response);  // service가 받은 응답 정보와 요청 정보를 doGet 메소드에 재전달 하겠다.
	    break;
	  case "POST":
	    doPost(request, response);
	    break;
	  }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * doGet()
	 * 1. GET 방식의 요청이 발생하면 자동으로 호출되는 메소드이다.
	 * 2. GET 방식의 요청
	 *   1) <a href="/servlet/life">
	 *   2) <form method="GET" action="/servlet/life">
	 *   3) location.href='/servlet/life'
	 *   4) window.open ('/servlet/life')  -- 새창으로 열기
	 *   5) $.ajax ({
	 *       type: 'GET',                  -- 비동기 요청
	 *       url: '/servlet/life',   
	 *       ...
	 *      })  
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Post 보다는 Get 방식이 많다. (doGet 메소드 호출이 잦음)
	  System.out.println("doGet() 메소드 호출");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * doPost()
	 * 1. POST 방식의 요청이 발생하면 자동으로 호출되는 메소드이다.
   * 2. POST 방식의 요청
   *   1) <form method="POST" action="/servlet/life">
   *   2) $.ajax ({
   *       type: 'POST',                  -- 비동기 요청
   *       url: '/servlet/life',   
   *       ...
   *      })  
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 메소드 호출");
		doGet(request, response); /* doPost()로 넘어온 정보가 다시 doGet() 메소드로 넘어간다. (요청 처리를 doPost() 에서 하지 않는다.)
		                             결국 POST 방식으로 요청이 들어와도 어차피 호출은 다시 GET 방식으로 넘어가기 때문에 
		                             코드 작업은 doGet()에서만 하면 된다. */ 
	}

}
