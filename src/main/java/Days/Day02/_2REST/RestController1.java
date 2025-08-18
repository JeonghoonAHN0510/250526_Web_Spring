package Days.Day02._2REST;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
// 해당클래스에 @Controller 어노테이션 주입
// 1. @Component : Spring 컨테이너(메모리)에 bean(객체)를 등록/생성한다. -> 싱글톤 대신 사용
// 2. Spring Controller는 기본적으로 HTTP(웹 서블릿) 통신을 지원한다.
public class RestController1 {
    // 싱글톤 생략 -> @Controller -> @Component : 자동으로 싱글톤처럼 하나의 객체를 생성해준다.
    // REST란? CRUD 기능을 HTTP로 제공하는 구조(아키텍처)
    // -> @PostMapping, @GetMapping, @PutMapping, @DeleteMapping와 같은 웹기술 제공
    // REST 테스터 : Talend API Tester - Free Edition(크롬 확장프로그램)
        // Talend API : http://localhost:8080/
    // 1) 등록 : CREATE -> @PostMapping
    @PostMapping
    public void 등록하기(){
        System.out.println("RestController1.등록하기");
    } // func end
    // 2) 조회 : READ -> @GetMapping
    @GetMapping
    public void 조회하기(){
        System.out.println("RestController1.조회하기");
    } // func end
    // 3) 수정 : UPDATE -> @PutMapping
    @PutMapping
    public void 수정하기(){
        System.out.println("RestController1.수정하기");
    } // func end
    // 4) 삭제 : DELETE -> @DeleteMapping
    @DeleteMapping
    public void 삭제하기(){
        System.out.println("RestController1.삭제하기");
    } // func end


} // class end
