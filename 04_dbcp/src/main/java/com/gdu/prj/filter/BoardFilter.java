package com.gdu.prj.filter;

import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.Arrays;
import java.util.Map;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class BoardFilter
 */
public class BoardFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

	  // HttpServletRequest / HttpServletResponse
	  HttpServletRequest req = (HttpServletRequest) request;
	  HttpServletResponse res = (HttpServletResponse) response;
	  
    // 요청 UTF-8 인코딩
    req.setCharacterEncoding("UTF-8");
    
    // 요청 방식 확인 + 요청 주소 확인
    System.out.print(String.format("%-4s", req.getMethod()));
    System.out.print(" | ");
    System.out.println(req.getRequestURI());
    
    // 요청 파라미터 확인
    Map<String, String[]> params = req.getParameterMap();
    for(Map.Entry<String, String[]> param : params.entrySet()) {
      System.out.print(String.format("%7s", " "));
      System.out.print(String.format("%-10s", param.getKey()) + " : "); // 파라미터 이름 10자의 폭을 가지고 출력
      System.out.print(Arrays.toString(param.getValue()));
    }
    
	  chain.doFilter(request, response); // controller 가 돌아가기 전에 
	}

}
