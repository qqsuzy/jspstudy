package com.gdu.prj.controller;

import java.io.IOException;

import com.gdu.prj.service.BoardService;
import com.gdu.prj.service.BoardServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/* 
 * view - controller - service - dao - db 
 * controller 에서는 service 밖에 못씀, controller 에서 dao 를 부를 수 없다.
 */

public class BoardController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  // controller 는 service 를 호출한다.
  private BoardService boardService = new BoardServiceImpl();   // 해당 작업을 해야 controller 가 BoardServiceImpl 를 사용할 수 있음 
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  doGet(request, response);
	}

}
