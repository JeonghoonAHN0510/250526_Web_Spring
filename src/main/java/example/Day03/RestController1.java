package example.Day03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// AppStart : 스프링 실행과 동시에, 같은/하위 패키지 내 모든 컴포넌트 스캔 후 자동 bean(객체) 등록

// @Controller
// 스프링에게 해당 클래스는 Controller라고 알리는 역할 + HTTP(웹 통신) 지원 + @Component 포함(@SpringBootApplication이 스캔할 대상)
@RestController // @Controller( + @Component ) + @ResponseBody
public class RestController1 {
    // 싱글톤 생략 : @Component을 사용했기 때문에
    @GetMapping("/day03")   // HTTP(웹 통신 규약) 중에서 Get 메소드를 사용 + BaseURL 뒤 URL 정의
    // @ResponseBody        // 응답 반환 시, 자바 타입을 주로 JSON 타입으로 자동 변환 -> @RestController면 생략 가능
    // -> URL : http://localhost:8080/day03
    public String method1(){
        return "자바에서 보내온 메시지";
    } // func end

} // class end
