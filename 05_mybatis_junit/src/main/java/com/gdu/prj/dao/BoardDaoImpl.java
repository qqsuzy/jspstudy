package com.gdu.prj.dao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gdu.prj.dto.BoardDto;

public class BoardDaoImpl implements BoardDao {


  // SqlSession (Connection/PreparedStatment/ResultSet 처리) 만드는 sqlSessionFactory
  // SqlSession 이라는 공장을 세우고 필요한 것을 뽑아서 각 메소드에서 사용 
  private SqlSessionFactory factory = null;
   
  // SingletonPattern
  private static BoardDao boardDao = new BoardDaoImpl();
  private BoardDaoImpl() {
    
    // mybatis-config.xml 파일을 이용한 sqlSessionFactory 객체 생성
    try {
     String resource = "com/gdu/prj/config/mybatis-config.xml";
     InputStream in = Resources.getResourceAsStream(resource); // Resources : org.apache.ibatis.io  (ibatis는 mybatis 예전 이름)
     factory = new SqlSessionFactoryBuilder().build(in); // in : 입력스트림으로 -> SqlSessionFactoryBuilder() : 공장을 짓는다
   } catch (Exception e) {
     e.printStackTrace();
   }
  }
  
  @Override
  public int insertBoard(BoardDto board) {
    SqlSession sqlSession = factory.openSession(false); // boolean autoCommit => false 전달 => autoCommit을 하지 않는다. (내가 직접 commit 한다)
    int insertCount = sqlSession.insert("com.gdu.prj.dao.board_t.insertBoard", board ); // insert(쿼리문 id , 쿼리문 파라미터) => 쿼리문 id는 mapper namespace + id로 작성 =>  이 mapper의 insertBoard를 실행해라
    if(insertCount == 1) {
      sqlSession.commit(); // commit 실행
    }
    sqlSession.close();
    return insertCount;
  }

  @Override
  public int updateBoard(BoardDto board) {
    SqlSession sqlSession = factory.openSession(false);
    int updateCount = sqlSession.update("com.gdu.prj.dao.board_t.updateBoard", board);
    if(updateCount == 1) {
      sqlSession.commit();
    }
    sqlSession.close();
    return updateCount;
  }

  @Override
  public int deleteBoard(int board_no) {
    SqlSession sqlSession = factory.openSession(false);
    int deleteCount = sqlSession.delete("com.gdu.prj.dto.int.deleteBoard", board_no);
    if(deleteCount == 1) {
      sqlSession.commit();
    }
    sqlSession.close();
    return deleteCount;
  }

  @Override
  public List<BoardDto> selectBoardList(Map<String, Object> params) {
   SqlSession sqlSession = factory.openSession();
   List<BoardDto> boardList = sqlSession.selectList("com.gdu.prj.dao.board_t.selectBoardList", params); // selectList : 셀렉트 결과가 여러 줄 , selectOne : 셀렉트 결과가 한 줄
   sqlSession.close();
    return boardList;
  }

  @Override
  public int getBoardCount() {
    SqlSession sqlSession = factory.openSession();
    int total = sqlSession.selectOne("com.gdu.prj.dao.board_t.getBoardCount");
    sqlSession.close();
    return total;
  }

  @Override
  public BoardDto selectBoardByNo(int board_no) {
    SqlSession sqlSession = factory.openSession();
    BoardDto board = sqlSession.selectOne("com.gdu.prj.dao.board_t.selectBoardByNo", board_no);
    sqlSession.close();
    return board;
  }

}
