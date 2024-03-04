package pkg08_ajax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetData2 extends HttpServlet {

  private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // 예외 회피가 되어 있어도 예외 처리가 추가로 필요할 경우 try-catch 코드를 넣는다.

	  StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552061/AccidentDeath/getRestTrafficAccidentDeath"); // 요청 주소 뒤에 ?를 붙여 파라미터를 계속 추가해주어야 함 => StringBuilder 로 추가추가 해준다.
	  
	  // 도로교통공단_사망교통사고정보서비스 : 사망교통사고정보 Rest 조회
	  urlBuilder.append("?serviceKey=").append(URLEncoder.encode("8E4t9bsYHNOU3pVnkEWhcPcU6X1djLiP68aLh5buKrsZRhU/Ymp+nD/J8sT7qCf6wvQ+Sm1YsDDfj+uE0p3opw==", "UTF-8")); //  첫번째 파라미터(serviceKey) : serviceKey , encode 메소드에 값(인증키)을 넣어준다.
	  urlBuilder.append("&searchYear=").append(URLEncoder.encode("2022", "UTF-8")); // 두번째 파라미터 부터는 & 로 데이터를 이어준다. 
	  urlBuilder.append("&siDO=").append(URLEncoder.encode("1100", "UTF-8"));       // 
	  urlBuilder.append("&guGun").append(URLEncoder.encode("116", "UTF-8"));        // 
	  urlBuilder.append("&type=").append(URLEncoder.encode("json", "UTF-8"));      // 
	  urlBuilder.append("&numOfRows=").append(URLEncoder.encode("10", "UTF-8")); // 
	  urlBuilder.append("&pageNo=").append(URLEncoder.encode("1", "UTF-8")); // 
	  
	  // 요청 URL
	  String spec = urlBuilder.toString();
	  
	  // 응답 코드
	  int responseCode = 0;

	  try { 
	  // URL 객체
	  URL url = new URL(spec);
	  
	  // HttpURLConnection 객체
	  HttpURLConnection con = (HttpURLConnection)url.openConnection();
	  
	  // 응답 코드 확인
    responseCode = con.getResponseCode();
    if(responseCode != HttpURLConnection.HTTP_OK) { // HttpURLConnection.HTTP_OK = 200
      throw new RuntimeException("API 응답 실패");
      }  
	  
	  // 문자 입력 스트림 (사망 사고 정보 읽기)
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    
    // StringBuilder 객체 (전체 JSON)
    StringBuilder builder = new StringBuilder();
    
    // String 객체 (한 줄)
    String line = null;
    
    // 문자 입력 스트림을 통해 사망 사고 정보 읽어 오기
    while((line = in.readLine()) != null) {  // readLine() : 한 줄씩 읽는 메소드 => BufferedReader (버퍼링을 지원하는 문자 입력 스트림) 의 메소드   
      builder.append(line);                  // 한 줄씩 읽어서 builder 에 추가한다.
    }
	  
    // 응답 데이터 타입 & 인코딩
    response.setContentType("application/json; charset=UTF-8");
    
    // 응답 
    PrintWriter out = response.getWriter();
    out.print(builder.toString());
    out.flush();
    out.close();
	  
	  // AJAX에서는 catch와 fail()이 연결되어 catch 블록이 동작하면 fail()로 데이터가 넘어간다. (catch 블록에 응답코드 생성)
    } catch (Exception e) { // 예외가 발생해야 동작한다. 
      // $.ajax().fail() 메소드로 전달되는 응답 만들기
      // 1) 응답 코드 만들기
      response.setStatus(responseCode); // 예외가 발생한 코드를 그대로 화면단으로 넘긴다.
      // 2) 응답 메시지 만들기
      response.setContentType("text/plain; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.print(e.getMessage()); // API 응답 실패 메시지를 getMessage 메소드로 꺼내서 화면으로 보낸다. API 응답 실패 -> catch 파라미터 e로 메시지 넘어감 ->
      out.flush();
      out.close();
    }

 }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
