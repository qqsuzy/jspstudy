package pkg06_upload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Download extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    // 다운로드 쪽에서 전달하는 파라미터 : filename
     
    request.setCharacterEncoding("UTF-8");
    String filename = request.getParameter("filename");
    
    String uploadPath = request.getServletContext().getRealPath("upload");
    
    File file = new File(uploadPath, filename);
   
    // 원본 파일(서버) 입력 스트림 생성
    BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
    
    // 다운로드용 응답 헤더("Content-Disposition=attachment")
    response.setHeader("Content-Disposition", "attachment"); // 다운로드 대화상자 나온다. (다운로드 하려는 파일명 입력하는 대화상자)
    response.setHeader("Content-Disposition", "attachment; filename=" + filename); // 다운로드 대화상자 없이 지정한 filename으로 곧바로 진행된다.
    
    // 복사 파일(클라이언트) 출력 스트림 생성 , 클라이언트 측으로 보내는 스트림은 응답을 통해서 받는다.
    BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream()); 
    
    // 복사 (서버 -> 클라이언트)
    byte[] b = new byte[1024]; // 원본을 1MB씩 읽어온다.
    int readByte = 0;
    while((readByte = in.read(b)) != -1) {
      out.write(b, 0, readByte);
    }
    
    // 스트림 닫기
    out.close();
    in.close();
    
  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  doGet(request, response);
	}

}
