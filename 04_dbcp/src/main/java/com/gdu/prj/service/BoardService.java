package com.gdu.prj.service;

import com.gdu.prj.common.ActionForward;

import jakarta.servlet.http.HttpServletRequest;

public interface BoardService {
  ActionForward addBoard(HttpServletRequest request);     
  ActionForward getBoardList(HttpServletRequest request); // 목록 보기 서비스 
  ActionForward getBoardByNo(HttpServletRequest request); // 목록 상세보기 서비스 (상세보기로 가야 수정,삭제 가능)
  ActionForward editBoard(HttpServletRequest request);    // 수정(편집)화면 서비스,  
  ActionForward modifyBoard(HttpServletRequest request);  // 진짜 수정하는 서비스 (DB 내의 데이터 수정)
  ActionForward removeBoard(HttpServletRequest request);  // 
  
  
}
