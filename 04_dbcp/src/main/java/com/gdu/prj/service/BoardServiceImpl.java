package com.gdu.prj.service;

import java.util.List;
import java.util.Optional;

import com.gdu.prj.common.ActionForward;
import com.gdu.prj.dao.BoardDao;
import com.gdu.prj.dao.BoardDaoImpl;
import com.gdu.prj.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;

/* 
 * view - filter - controller - service - dao - db 
 */

public class BoardServiceImpl implements BoardService {

  // service는 dao 를 호출한다.
  // singleton patturn 작업으로 인해 BoardDaoImpl를 가져다 사용할 수 있도록 처리 (BoardDaoImpl => private 처리되어 있음)
  private BoardDao boardDao = BoardDaoImpl.getInstance();
  
  @Override
  public ActionForward addBoard(HttpServletRequest request) { // 파라미터에 title , contents가 들어있음
    String title = request.getParameter("title");
    String contents = request.getParameter("contents"); // 해당 값을 insertBoard로 넘겨야 함 -> 받는 타입이 BoardDto -> BoardDto로 변환 필요
    BoardDto board = BoardDto.builder()
                             .title(title)
                             .contents(contents)
                            .build();
    int insertCount = boardDao.insertBoard(board); // insert 가 성공하면 1, 실패하면 0 값이 insertCount에 저장
    // redirect 경로는 URLMapping 으로 작성한다. (forward 는 ooo.jsp 이나 redirect 는 URLMapping 을 작성해야함) 
    String view = null;
    if(insertCount == 1) {        // 성공 -> list.brd로 이동 
      view = request.getContextPath() + "/board/list.brd"; // redirect 는 contextPath 부터 주소 작성해야함! , jsp로 가자고 하면 DB로 가지도 않음 => 새로 작성된 목록을 가져오지 못함 => jsp가 아닌 brd로 가자해야 서비스가 돌아감
    } else if(insertCount == 0) { // 실패 -> index.brd로 이동
      view = request.getContextPath() + "/main.brd";
    }
    // INSERT 이후 이동은 redirect
    return new ActionForward(view, true); // ActionForward로 이동
  }

  @Override
  public ActionForward getBoardList(HttpServletRequest request) {
    int boardCount = boardDao.getBoardCount(); // 목록을 구할 때 목록카운트, 목록 자체를 dao에게 받아옴
    List<BoardDto> boardList = boardDao.selectBoardList(null);
    request.setAttribute("boardCount", boardCount);     // 전달 할 수 있도록 request에 저장
    request.setAttribute("boardList", boardList);
    return new ActionForward("/board/list.jsp", false); // (forward 경로, 전달 방법) :  forward 경로는 contextPath를 적지 않음 => 이후 경로를 작성
  }                                                    // false => forward , true => redirect 설정해둠

  @Override
  public ActionForward getBoardByNo(HttpServletRequest request) {
    Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
    int board_no = Integer.parseInt(opt.orElse("0"));
    BoardDto board = boardDao.selectBoardByNo(board_no);
    String view = null;
    if(board != null) { // selectBoardByNo 가 null 이면 게시글이 없다는 것 (시퀀스로 board_no 제작하기 때문에 0이 존재하지 않음)
      view = "/board/detail.jsp";
      request.setAttribute("board", board);
    } else {
      view = "/index.jsp";  // null 이면 index.jsp로 이동
    }
    return new ActionForward(view, false); // fowrard = request에 저장하는 코드 + jsp 이동 코드 (2가지 조합)
  }

  @Override
  public ActionForward editBoard(HttpServletRequest request) {
    String param = request.getParameter("board_no");
    int board_no = 0; 
    if(!param.isEmpty()) {
      board_no = Integer.parseInt(param);
    }
    BoardDto board = boardDao.selectBoardByNo(board_no);
    String view = null;
    if(board != null) {                 // board_no 는 null값 확률 0% , name 값 설정되어 있기 때문 => ' ' (빈문자열) 넘어옴
      view ="/board/edit.jsp";
      request.setAttribute("board", board); 
    } else {
      view = "/index.jsp";
    }
    return new ActionForward(view, false); // request로 받았기 때문에 forward
  }

  @Override
  public ActionForward modifyBoard(HttpServletRequest request) {
    int board_no = Integer.parseInt(request.getParameter("board_no"));
    String title = request.getParameter("title");
    String contents = request.getParameter("contents");
    BoardDto board = BoardDto.builder()
                          .title(title)
                          .contents(contents)
                          .board_no(board_no)
                        .build();
    int updateCount = boardDao.updateBoard(board);
    String view = null;
    if(updateCount == 0) {
      view = request.getContextPath() + "/main.brd";
    } else {
      view = request.getContextPath() + "/board/detail.brd?board_no=" + board_no;
    }
    return new ActionForward(view, true);
  }

  @Override
  public ActionForward removeBoard(HttpServletRequest request) {
    String param = request.getParameter("board_no");
    int board_no = 0;
    if(!param.isEmpty()) {
      board_no = Integer.parseInt(param);
    }
    int deleteCount = boardDao.deleteBoard(board_no);
    String view = null;
    if(deleteCount == 0) {
      view = request.getContextPath() + "/main.brd";
    } else {
      view = request.getContextPath() + "/board/list.brd";
    }
    return new ActionForward(view, true);
  }
  
  @Override
  public ActionForward removeBoards(HttpServletRequest request) {
    String param = request.getParameter("param");
    int deleteCount = boardDao.deleteBoards(param);
    String view = null;
    if(deleteCount == 0) {
      view = request.getContextPath() + "/main.brd";
    } else {
      view = request.getContextPath() + "/board/list.brd";
    }
    return new ActionForward(view, true);
  }

}
