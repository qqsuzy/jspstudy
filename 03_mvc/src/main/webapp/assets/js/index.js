/**
 * 
 * mvc pattern
 *
 * jsp(html) ------> servlet ------> java(pojo) 
 * --------          -------         ----------
 *   view           controller         model 

 * 
 * 하나의 서블릿으로 요청 처리 방법 
 * 주소로 요청을 구분하기 위해 suffix 값(prefix의 반대말로 뒤에 붙이는 고정값)을 붙여준다. ( .do => 관습적으로 가장 많이쓰임)
 * URL Mapping 을 *.do 로 바꾸어주면 모든 요청을 하나의 서블릿이 받을 수 있음
 * controller 패키지 내에 jsp 파일 추가 -> *.do
 * 
 */

/* ContextPath 를 변수처리하는 것은 필수 작업!!!!!  */
 const getContextPath = ()=>{ /* ContextPath 가져오는 함수 */
  const host = location.host; /* localhost:8080  => host */
  const url = location.href;  /* http://localhost:8080/mvc/getDate.do  => 전체 주소 */  
  
 // contextpath의 시작 위치를 구하기 :  호스트 시작위치에 호스트 길이를 더해줌 
  const begin = url.indexOf(host) + host.length; // 21
 // /getDate.do 
  const end = url.indexOf('/', begin + 1);
  return url.substring(begin, end); // /mvc 반환
}

 const getDateTime = ()=>{
   const type = document.getElementById('type');
   if(type.value === 'date'){
     location.href = getContextPath() + '/getDate.do';
   } else if(type.value === 'time'){
     location.href = getContextPath() + '/getTime.do';
   } else if(type.value === 'datetime'){
     location.href = getContextPath() + '/getDateTime.do';
   }
 }
 document.getElementById('btn').addEventListener('click',getDateTime);
 
 
 