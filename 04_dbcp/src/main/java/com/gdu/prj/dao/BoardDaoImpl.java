package com.gdu.prj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.gdu.prj.dto.BoardDto;

/*
 * view - filter - controller - service - dao - db
 * 
 * Connection, ResultSet, PreparedStatement (dao가 가지고 있는 것 삼총사)
 */

public class BoardDaoImpl implements BoardDao {

  // dao 는 db 를 처리한다.
  // filed로 선언해두어야 모든 메소드에서 사용 가능하다. ( Java 에 jdbc dao 코드 확인해보기)
  private Connection con;
  private PreparedStatement ps;
  private ResultSet rs;
  
  // Connection Pool 관리를 위한 DataSource 객체 선언
  private DataSource dataSource;
  
  // SingletonPattern
  private static BoardDao boardDao = new BoardDaoImpl(); // BoardDao 타입의 BoardDaoImpl를 생성
  private BoardDaoImpl() { // 외부에서 생성할 수 없도록 private 처리
    // META-INF\context.xml 파일에 명시된 Resource 를 이용해 DataSource 객체 생성하기
    try {
      Context context = new InitialContext(); // 메이밍 조작
      Context env = (Context)context.lookup("java:comp/env"); // tomcat 을 읽어 들일 때 사용하는 기본적인 네이밍
      dataSource = (DataSource)env.lookup("jdbc/myoracle"); // lookup 으로 특정한 리소스를 가져옴
    } catch (NamingException e) {                           // 여러 개의 connection 을 만들어서 성능 향상을 기대할 수 있음  
      System.out.println("관련 자원을 찾을 수 없습니다."); 
    }
    
  }
  public static BoardDao getInstance() {  // BoardDao를 getInstance() 메소드로 가져올 수 있음
    return boardDao;
  }

  
  @Override
  public int insertBoard(BoardDto board) { // 사용자가 insert 한 정보가 파라미터에 들어 있음
    int insertCount = 0;
    try {
      // connection 얻기를 가장 먼저 해야함
      con = dataSource.getConnection(); // dataSource : connection pool 관리 객체 (connection 얻어오기)
   // TITLE , CONTENTS 는 매개변수에 있으며 변수(?)처리함 
      String sql = "INSERT INTO BOARD_T(BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, CURRENT_DATE, CURRENT_DATE)";
      // 쿼리문 실행 담당
      ps = con.prepareStatement(sql);
      ps.setString(1, board.getTitle()); // 1번째 물음표(변수)에 Title
      ps.setString(2, board.getContents()); // 2번째 물음표(변수)에 Contents
      insertCount = ps.executeUpdate(); // 수행 결과는 insertCount가 받음, DML 수행은 executeUpdate로 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close(); // 닫아주는 메소드인 close() 호출 => this.close() 도 가능
    }
    return insertCount;
  } 

  @Override
  public int updateBoard(BoardDto board) { // 파라미터 => no(어떤 게시글을 수정할지) , title(제목수정) , contents(내용수정)
    int updateCount = 0;
    try {
      con = dataSource.getConnection(); // dataSource 으로부터 받아오기
      String sql = "UPDATE BOARD_T SET TITLE = ?, CONTENTS = ?, MODIFIED_AT = CURRENT_DATE WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setString(1, board.getTitle());
      ps.setString(2, board.getContents());
      ps.setInt(3, board.getBoard_no());
      updateCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return updateCount;
  }

  @Override
  public int deleteBoard(int board_no) {
    int deleteCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "DELETE FROM BOARD_T WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, board_no);
      deleteCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return deleteCount;
  }

  @Override
  public List<BoardDto> selectBoardList(Map<String, Object> params) {
    List<BoardDto> boardList = new ArrayList<>();
    try {
      con = dataSource.getConnection();
      String sql = "SELECT BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT"
                 + "  FROM (SELECT ROW_NUMBER() OVER(ORDER BY BOARD_NO " + params.get("sort") + ") AS RN, BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT"
                 + "          FROM BOARD_T)"
                 + " WHERE RN BETWEEN ? AND ?"; // 변수(?) 에 begin, end 전달
      ps = con.prepareStatement(sql);
      ps.setInt(1, (int)params.get("begin"));   // Object로 저장되어 있기 때문에 맵에서 꺼내 쓸 때는 캐스팅 필요 (Object -> in)
      ps.setInt(2, (int)params.get("end")); 
      rs = ps.executeQuery();                   // SELETE의 실행 결과는 boardList 가 직접 받는 것이 아닌 무조건 rs가 받음 (SELETE는 executeQuery로 호출) 
      while (rs.next()) {                       // rs.next() 는 읽어들이는 데이터만큼 생성해야함 (데이터 존재하면 true , 아니면 false) => 한줄씩 읽어들여서 builder()로  
        BoardDto board = BoardDto.builder()
                                 .board_no(rs.getInt(1)) // 1번째 칼럼은 board_no
                                 .title(rs.getString(2))
                                 .contents(rs.getString(3))
                                 .modified_at(rs.getDate(4)) // 4번째 칼럼
                                 .created_at(rs.getDate(5))
                                .build();
        boardList.add(board); // boardList에 추가
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally { 
      close();
    }
    return boardList;
  }

  @Override
  public int getBoardCount() {
    int boardCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "SELECT COUNT(*) FROM BOARD_T"; // 
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      if(rs.next()) { // row는 무조건 COUNT가 1이 나옴(게시글이 없으면 0 으로 COUNT = 1) => 데이터가 1개라고 판단되면 while 말고 if로 처리
        boardCount = rs.getInt(1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return boardCount;
  }

  @Override
  public BoardDto selectBoardByNo(int board_no) {
    BoardDto board = null;
    try {
      con = dataSource.getConnection();
      String sql = "SELECT BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT FROM BOARD_T WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, board_no);
      rs = ps.executeQuery();
      if(rs.next()) {              // 검색 결과는 0 or 1 로 반드시 if로 처리 => board_no는 PK로 중복값이 1개가 최대값 (0 = 데이터 없음, 1 = 데이터 있음)
        board = BoardDto.builder()
                        .board_no(rs.getInt(1))
                        .title(rs.getString(2))
                        .contents(rs.getString(3))
                        .modified_at(rs.getDate(4))
                        .created_at(rs.getDate(5))
                       .build();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return board;
  }

  @Override
  public void close() {
    try {
      if(rs != null) rs.close();
      if(ps != null) ps.close();
      if(con != null) con.close(); // 닫는 것이 아닌 Connection 반납으로 동작
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public int deleteBoards(String param) {
    int deleteCount = 0;
    try {
      con = dataSource.getConnection();
      String sql="DELETE FROM BOARD_T WHERE BOARD_NO IN(" + param + ")";
      ps = con.prepareStatement(sql);
      deleteCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return deleteCount;
  }
}
