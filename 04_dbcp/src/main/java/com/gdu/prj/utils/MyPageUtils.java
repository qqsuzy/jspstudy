package com.gdu.prj.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

/* paging 처리
 *  1. 전체 게시글 개수 파악하기
 *  2. 화면 하나(page)에 몇 개의 게시글을 보여줄지 정하기
 *  3. 페이지 번호가 없을 때(파라미터가 없을 때)는 1페이지 보여주기 (기본 세팅) 
 *  4. 페이지 번호를 클릭하면 페이지 번호(요청 파라미터)가 넘어감
 *  5. 계산하는 메소드 생성 (setPaging)
 *  6. 화면 하나에 몇 개의 페이지 링크를 보여줄지 정하기 (block, 고정값) 
 *  7. pagePerBlock 보다 페이지 링크수가 작을 경우 처리하기 (존재 하지 않는 페이지 링크 번호까지 나오지 않도록)
 *  8. 이전, 다음 블록 버튼 클릭하면 페이지 링크 블록 변경됨
 *  9. 페이지 블록이 이전, 다음으로 존재하지 않을 경우 버튼(<, >) 색상 변경 처리 ( class="dont-click" ,  color:silver)
 * 10. 현재 페이지를 나타내는 색상 처리 (class="current-page" color:limegreen)
 * 11. 최신 게시글(DESC), 첫 게시글(ASC) 정렬 버튼 생성 -> sort 로 ASC, DESC 전달
 * 12. 한 페이지에 표시할 게시글 개수 (display) 목록상자<option> 추가 -> 목록상자로 display 변경하면 페이지 블록 리셋되어 1페이지로 이동 됨 (display 디폴트값 = 20개)
 * 13. getPaging 에서 주소를 만들 때 requestURI, sort, display 를 모두 BoardServiceImpl로 부터 받아와야 페이지 링크를 클릭하여도 원하는 데이터들(설정한)이 넘어옴
 */

@NoArgsConstructor
@Data
public class MyPageUtils {

  // BoardServiceImpl 에서 확인해야하는 정보들
  private int total;   // 전체 게시글 개수                      (DB에서 구한다.)
  private int display; // 한 페이지에 표시할 게시글 개수        (요청 파라미터로 받는다.)
  private int page;    // 현재 페이지 번호                      (요청 파라미터로 받는다.)
  private int begin;   // 한 페이지에 표시할 게시글의 시작 번호 (계산한다.)
  private int end;     // 한 페이지에 표시할 게시글의 종료 번호 (계산한다.)
  
  private int pagePerBlock = 10; // 한 블록에 표시할 페이지 링크의 개수      (임의로 결정한다.)
  private int totalPage;         // 전체 페이지 개수                         (계산한다.)
  private int beginPage;         // 한 블록에 표시할 페이지 링크의 시작 번호 (계산한다.)
  private int endPage;           // 한 블록에 표시할 페이지 링크의 종료 번호 (계산한다.)
  
  // 계산하는 메소드
  public void setPaging(int total, int display, int page) { 
    this.total = total;
    this.display = display;
    this.page = page;
    
   /*
    * total 1000
    * dispaly 20
    * 
    * page=n begin end
    * page=1   1   20  (begin, end : ROWNUMB 기준)
    * page=2  21   40
    * page=3  41   60 
    * ...
    * 
    */ 
    
    // begin, end 계산
    begin = (page - 1) * display + 1;
    end = begin + display - 1;
    
    /*
     * blockn beginPage endPage
     * block1     1       10
     * block2    11       20
     * block3    21       30
     * ...
     */
    
    // totalPage, beginPage, endPage 계산
    totalPage =  (int)Math.ceil((double)total / display); // 1001(totla) / 20(display) = 50.05 가 나옴 -> 51번으로 지정할 수 있도록 ceil(올림) 처리함
    beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
    endPage = Math.min(totalPage, beginPage + pagePerBlock - 1);
  }
  
  // 페이지 블록(block) 생성 
  public String getPaging(String requestURI, String sort, int display) { // BoardServiceImpl로 부터 URI, sort, display 받아옴
    
    StringBuilder builder = new StringBuilder();
    
    // < (이전 블록)
    if(beginPage == 1) {
      builder.append("<div class=\"dont-click\">&lt;</div>"); // &lt : 엔티티 코드 
    } else {
      builder.append("<div><a href=\"" + requestURI + "?page=" + (beginPage - 1) + "&sort=" + sort + "&display=" + display + "\">&lt;</a></div>");
    }
    
    // 1 2 3 4 5 6 7 8
    for(int p = beginPage; p <= endPage; p++) {  // p : 현재 페이지  
      if(p == page) {
        builder.append("<div><a class=\"current-page\" href=\"" + requestURI + "?page=" + p + "&sort=" + sort + "&display=" + display + "\">" + p + "</a></div>");
      } else {
        builder.append("<div><a href=\"" + requestURI + "?page=" + p + "&sort=" + sort + "&display=" + display + "\">" + p + "</a></div>");
      }
    }
    
    // > (다음 블록)
    if(endPage == totalPage) { // endPage == totalPage 페이지 넘어가지 않음
      builder.append("<div class=\"dont-click\">&gt;</div>");
    } else {
      builder.append("<div><a href=\"" + requestURI + "?page=" + (endPage + 1) + "&sort=" + sort + "&display=" + display + "\">&gt;</a></div>");
    }
    
    return builder.toString();
    
  }
}
