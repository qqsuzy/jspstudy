package pkg04_EL;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 *  객체를 생성하는  방법
 *  
 *  1)
 *   Book book = new Book();    =>  @NoArgsConstructor , @Setter  사용
 *   book.settile  
 *   book.setAuthor
 *   book.setprice
 *   
 *   2) 
 *   Book book = new Book (title, author, price);  =>  @AllArgsConstructor 사용
 *   
 *   3)
 *   Book.builder()            =>   @Builder 사용  
 *   .title()
 *   .author()
 *   .price()
 *   .build();
 *   
 *   lombok 이용할 때 builder patrun을 이용하면 스크립트 작성하기 수월함
 *  
 */

@NoArgsConstructor
@AllArgsConstructor  // filed 를 받아서 선언하는 생성자
@Setter
@Getter
@Builder  
public class Book {

  // filed
  private String title;
  private String author;
  private int price;
  
}
