package test01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Join extends HttpServlet {
	
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
request.setCharacterEncoding("UTF-8");
    
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    String name = request.getParameter("name");
    String birthYear = request.getParameter("birth-year");
    String birthMonth = request.getParameter("birth-month"); 
    String birthDay = request.getParameter("birth-day");
    String gender = request.getParameter("gender"); 
    String email = request.getParameter("email");
    String phone1 = request.getParameter("phone1"); 
    String phone2 = request.getParameter("phone2");
	  
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html lang=\"ko\">");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>Insert title here</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<div><ul>");
    out.println("<li>아이디 : "   + id + "</li>");
    out.println("<li>비밀번호 : " + pw + "</li>");
    out.println("<li>이름 : "     + name + "</li>");
    out.println("<li>생년월일 : " + birthYear + "년" + birthMonth  + "월" + birthDay + "일" + "</li>");
    out.println("<li>성별 : "   + gender + "</li>");
    out.println("<li>휴대전화 : " + phone1 + phone2 + "</li>");
    out.println("<li>이메일 : "   + email + "</li>");
    out.println("</ul></div>");
    out.println("</body>");
    out.println("</html>");
    
 
	}

}
