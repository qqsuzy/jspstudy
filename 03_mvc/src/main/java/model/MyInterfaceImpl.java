package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

// 구현 클래스 이름 : 인터페이스명+Impl 
// 구현 클래스 
public class MyInterfaceImpl implements MyInterface {  

  @Override
  public ActionForward getDate(HttpServletRequest request) {
    /* getDate가 날짜를 반환할 필요가 없음
     * getDate 의 결과를 어떤 jsp에서 보여줄지를 반환함 (return)
     * jsp로 정보를 가져갈 수 있도록 forward를 사용 (request를 가지고 이동)
     * date 자체는 request 에 저장=> 어떤 jsp로 이동할지 return  => forward 하여 request를 가지고 이동함  
     */
    
    request.setAttribute("date", DateTimeFormatter.ofPattern("yyyy. MM. dd.").format(LocalDate.now()));  
    return new ActionForward("view/date.jsp", false); // 날짜 요청은 date.jsp에서 처리할 것 (경로 반환)
  } // forward : false , redirect : true 설정  

  @Override
  public ActionForward getTime(HttpServletRequest request) {
    request.setAttribute("time", DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalTime.now()));
    return new ActionForward("/view/time.jsp", false); // 시간 요청은 time.jsp에서 처리할 것 (경로 반환)
  }

  @Override
  public ActionForward getDateTime(HttpServletRequest request) {
    request.setAttribute("datetime", DateTimeFormatter.ofPattern("yyyy. MM. dd. HH:mm:ss.SSS").format(LocalDateTime.now()));
    return new ActionForward("/view/datetime.jsp", false); // 날짜시간 요청은 datetime.jsp에서 처리할 것 (경로 반환)
  }
  }

