package pkg06_upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

//첨부 파일을 가지고 있음을 명시한다.
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,  // 첨부할 때 메모리 2MB
                maxFileSize = 1024 * 1024 * 5,         // 최대 첨부 파일 사이즈 5MB
                maxRequestSize = 1024 * 1024 * 50)     // 최대 

public class Upload extends HttpServlet {
	
  private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
    // /src/main/webapp/ 에 있는 경로는 소스코드만 저장되어 있다. (/src/main/webapp/ => (/) 와 동일한 경로)
    // Java로 폴더 하나를 만들어서 사용자가 업로드 한 파일을 저장한다. (real path 경로)

    // 업로드 경로 (톰캣 내부 경로)
    String uploadPath = request.getServletContext().getRealPath("upload"); // getServletContext() : 서비스 시작 ~ 끝까지의 가장 넓은
                                                                           // 라이프 사이클
    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) { // 디렉토리가 없다면,
      uploadDir.mkdir(); // 디렉토리를 생성한다.
    }
    String filesystemName = null;
    String originalFilename = null;

    // 첨부된 파일 정보
    Collection<Part> parts = request.getParts(); // getParts() : 반환 타입 (Collection<E>) -> 반복문 돌릴 수 있다.
    for (Part part : parts) {
      // System.out.println(part.getName() + "," + part.getContentType() + "," +
      // part.getSize() + "," + part.getSubmittedFileName());
      // System.out.println(part.getHeader("Content-Disposition"));

      // 첨부된 파일의 원본 파일명을 확인하기
      // 웹 개발할 때 사용자가 지정한 파일명 그대로 DB에 저장하지 않는다. (중복된 파일이 있을 경우 파일이 덮어쓰기 되기 때문이다.)
      // 원래 이름은 DB에 저장, 서버에 저장할 이름을 따로 만들어서 DB에 따로 저장한다.
      if (part.getHeader("Content-Disposition").contains("filename")) {
        if (part.getSize() > 0) { // 파일 사이즈가 0보다 큰 파일만 확인한다.
          originalFilename = part.getSubmittedFileName();
        }
        // 파일 이름 가공 (중복 파일명 존재하지 않도록 함)
        if (originalFilename != null) {
          int point = originalFilename.indexOf(".");
          String extName = originalFilename.substring(point); // .jpg (확장자)
          String fileName = originalFilename.substring(0, point); // animal.1 (파일명)
          filesystemName = fileName + "_" + System.currentTimeMillis() + extName;
        }
        if (filesystemName != null) {
          part.write(uploadPath + File.separator + filesystemName); // .write() : 데이터 저장 , 플래폼 마다 확장자 구분이 다르기 때문에
                                                                    // File.separator 를 사용해서 알아서 플랫폼에 맞게 적용해준다. (슬래시,
                                                                    // 백슬래시)
        }
        // System.out.println(filesystemName); // filesystemName 이 null 이 아니면 저장한다.
      }

    }
    // 응답
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<div><a href=\"/servlet/pkg06_upload/NewFile.html\">입력폼으로 돌아가기</a></div>");
    out.println("<hr>");
    out.println("<div>첨부파일명 : " + originalFilename + "</div>");
    out.println("<div> 저장된파일명 : " + filesystemName + "</div>");
    out.println("<div> 저장경로 : " + uploadPath + "</div>");
    out.println("<hr>");
    
    
    File[] files = uploadDir.listFiles();
    for(File file : files) {
     String fileName1 = file.getName();  // 파일명_1234567890.jpg
     String ext = fileName1.substring(fileName1.lastIndexOf("."));           // .jpg (확장자)
     String fileName2 = fileName1.substring(0, fileName1.lastIndexOf("_"));  // animail.1(파일명) : 파일명 마지막 밑줄 이전까지 (파일명 내에 밑줄이 포함되어 있을 수 있으니 이전까지로 설정)
     out.println("<div><a href=\"/servlet/download?filename=" + URLEncoder.encode(fileName1, "UTF-8") + "\">"  + fileName2 + ext + "</a></div>"); // 한글이 들어왔을 경우 주소창에 오류없이 뜰 수 있도록 인코딩한다.
     
    }

    out.flush();
    out.close();

    // 각 part가 첨부인지 아닌지는 contentType 으로 확인 가능하다. (null 체크가 필요하다.)
    // Content-Disposition 값으로도 첨부 여부를 확인할 수 있다. (포함 여부를 확인하는 메소드인 contents? 사용한다.
    // getSize() : 바이트(byte) 로 반환된다.
    // getSubmittedFileName() : submit 된 파일 이름 (파일이 존재할 때만 확인 가능하다.)
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
