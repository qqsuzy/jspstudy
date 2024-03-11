package com.gdu.prj.dao;

import java.util.List;
import java.util.Map;

import com.gdu.prj.dto.BoardDto;

public interface BoardDao {
  int insertBoard(BoardDto board); // insert 된 게시물이 몇개가 있는지 int로 반환
  int updateBoard(BoardDto board); // update 된 게시물이 몇개가 있는지 int로 반환
  int deleteBoard(int board_no);   // delete 할 게시물의 번호만 받아오면 됨
  List<BoardDto> selectBoardList(Map<String, Object> params); // 게시글 목록을 몇번부터 몇번까지 받아올지, Map으로 받아온다. 반환타입 : List<> => boardDto가 여러개 들어 있는 list
  int  getBoardCount();                   // 게시글 전체 개수 (SELECT *)
  BoardDto selectBoardByNo(int board_no); // 상세보기 (번호를 받아서 반환), 반환타입 : BoardDto
}
