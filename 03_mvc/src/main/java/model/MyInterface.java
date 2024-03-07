package model;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public interface MyInterface {

  /*               -------->
   *  controller                model
   *               <--------
   */
  
  // Model 이 반환하는 대상은 controller
  // 추상메소드 생성 (public , abstract 생략 가능)
  ActionForward getDate(HttpServletRequest request); // 본문이 없는 추상메소드
  ActionForward getTime(HttpServletRequest request);
  ActionForward getDateTime(HttpServletRequest request);
  
}
