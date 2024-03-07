package common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
//getter + setter + equals ... 들어있음 (builder 패턴은 들어있지 않으므로 별도로 추가해야 함)
@Data 
@Builder
public class ActionForward {  // 어디로 갈지, 어떻게 갈지 결정
  private String view;        // 응답 view
  private boolean isRedirect; // 초기값 : redirect 하지 않겠다. (디폴트 false)
  
}
