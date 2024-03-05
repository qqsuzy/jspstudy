package test03;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/localDate")
public class LocalDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	  
	}
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  
	  request.setCharacterEncoding("UTF-8");
	  
	  
	  String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	  String time = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
	  
	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  
	  out.print(date);
	  
	}

}
