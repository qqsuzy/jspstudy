package pkg02_request;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyRequest extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  // 1. 요청 UTF-8 인코딩
	  request.setCharacterEncoding("UTF-8");
	  
	  // 2. 요청 파라미터 (String 타입)
	  //   1) 모든 파라미터는 String 타입인다.
	  //   2) 파라미터가 없으면 null 이다.
	  //   3) 파라미터의 값이 없으면 ""(빈 문자열) 이다. 
	  //  isEmpty
	  
	  // null이 넘어 오는 경우
	  // NewFile 의 link 에서 number 만 보낼 경우 number2(파라미터)는 아무 값을 받지 못하고 strNumber2는 null이 저장된다.
	  
	  
	  
	  /* if 문을 이용한 null 처리 */
	  String strNumber = request.getParameter("number"); // 파라미터 이름 : number(문자열)
	  int number = 0; 
	  if(strNumber != null && !strNumber.isEmpty())    // null이 아니고, 빈 문자열이 아니면 parsing을 하겟다.
	    number = Integer.parseInt(strNumber);          // strNumber 을 int 타입으로 변경해서 number에 저장한다.
	  System.out.println(number);
	    
	  /* Optional<T> 클래스를 이용한 null 처리 */
	  String strNumber2 = request.getParameter("number2"); 
	  if(!strNumber2.isEmpty()) {
	    Optional<String> opt = Optional.ofNullable(strNumber2); //strNumber2를 opt에 저장한다. ofNullable() 메소드는 strNumber2 값이 null이 아니면 strNumber2 값을 가지는 Optional 객체(opt)를 반환한다. strNumber2 값이 null이면 비어있는 Optional 객체(opt)를 반환한다.
	    double number2 = Double.parseDouble(opt.orElse("0").isEmpty() ? "0" : opt.orElse("0")); // orElse() 메소드로 opt를 꺼내는데, 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 값("0")을 반환한다. strNumber2가 null이면 "0"을 꺼낸다. null 은 타입 변환이 안되며, NullPointerException이 발생된다. -> null 처리가 필요하다.
	    System.out.println(number2);                                                       // 빈 문자열이면 (isEmpty) "0"을 반환하고, 아니면 저장된 값(strNumber2)을 반환한다.
	                                                                                       //  orElse() 의 "0" 은 null 일 때, isEmpty() 의 "0" 은 빈 문자열일 때 
	 }
 }
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // 1. 요청 UTF-8 인코딩 (항상 먼지 진행하기)
    request.setCharacterEncoding("UTF-8");

    // 2. 요청 파라미터
    // 1) name 속성이 파라미터이다.
    // 2) 동일한 name 속성을 가진 입력 요소들은 다음과 같이 처리한다.
    // (1) type="radio" : 변수 처리한다. (radio는 하나만 선택할 수 있기 때문이다.)
    // (2) 이외의 경우 : 배열 처리한다. (checkbox와 같은 요소들...)

    // 파라미터 받아오기
    // 변수 3개
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String gender = request.getParameter("gender"); // radio 타입

    // 배열 2개
    String[] hobbies = request.getParameterValues("hobbies");
    String[] mobile = request.getParameterValues("mobile");

    // 입력이 안되었을 때 (입력 필수) 의 경우는 front 에서 막아야한다.
    System.out.println(name);   // 입력하는 입력 상자(text)들은 ""(빈 문자열)이 넘어온다.
    System.out.println(email); 
    System.out.println(gender); // 체크하는 체크 요소들은 null이 넘어온다. (radio, checkbox ...)
    System.out.println(Arrays.toString(hobbies));
    System.out.println(Arrays.toString(mobile));
   
    
  }

}
