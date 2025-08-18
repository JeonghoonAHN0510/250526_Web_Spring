package Days.Day03;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    // * 매개변수를 가지는 메소드
    // 메소드에서 매개변수란? 메소드 호출 시, 메소드 안으로 들어오는 값을 저장하는 변수
    // + HTTP 요청 시에도 자원을 보낼 수 있다. -> 쿼리스트링으로 매개변수 전달 : http://localhost:8080/day03/param1?매개변수명1=값1@매개변수명2=값2...
    // 주의할 점 : HTTP 매개변수 전달 시, 문자만 가능하다. -> 다행히 자동 타입변환 지원
    @GetMapping("/day03/param1")
    public boolean method2( @RequestParam( name = "name", defaultValue = "기본값", required = false) String name ){
        // @RequestParam : Param(매개변수) + Request(요청) -> 매개변수 요청 매핑 어노테이션, 선택 사항
        // name = "URL상매개변수명", 자바 매개변수와 이름이 같으면 생략가능
        // defaultValue = "기본값", 쿼리스트링 생략되면 기본값 대입
        // required = true/false, 해당 매개변수가 없으면 자동으로 예외 발생 -> HTTP 400 ERROR
        System.out.println("RestController1.method2");
        System.out.println("name = " + name);
        return true;
    } // func end

    @GetMapping("/day03/param2")
    // http://localhost:8080/day03/param2?name=유재석&age=20
    public int method3( String name, @RequestParam( name = "age" ) int 나이 ){
        // 통신확인 방법 : soutm, soutp
        System.out.println("RestController1.method3");
        System.out.println("name = " + name + ", age = " + 나이);
        return 3;
    } // func end

    @DeleteMapping("/day03/param3")
    public String method4( @RequestParam Map<String, String> map ){
        // 일반적으로 정해진 규칙이나 매개변수명이 아닌, 단순 Map(비규칙) 구조일 경우 @RequestParam이 필수이다.
        System.out.println("RestController1.method4");
        // Map : key + value를 한 쌍(entry)으로 구성하여 여러개의 entry를 저장하는 구조
        System.out.println("map = " + map);

        return "리턴";
    } // func end

    @DeleteMapping("/day03/param4")
    // Dto에 자동 매핑을 하기 위해서는 같은 이름의 멤버변수와 생성자 메소드가 필요하다.
    public int method5( TaskDto taskDto ){
        System.out.println("RestController1.method5");
        System.out.println("taskDto = " + taskDto);

        return 3;
    } // func end

    /*
                     @RequestParam          vs          @ModelAttribute             vs       @RequestBody
       역할        단일 파라미터에 변수 저장              복수 파라미터에 객체 저장                본문(Body)을 객체에 저장
     생략기준        변수명이 일치할 경우                  DTO 타입일 때 기본적용
     주요타입     기본형:int,String,List,Map...        개발자가 만든 객체:DTO,VO....                   DTO
    처리가능요청     쿼리스트링,<form:첨부파일X>          쿼리스트링,<form:첨부파일O>                   본문(Body)
    HTTP 메소드     GET/POST/PUT/DELETE                 GET/POST/PUT/DELETE                     POST/PUT

    Body(본문)이란? HTTP 본문에 매개변수 표현, 사용자에게 매개변수 노출 X(보안에 유리), POST/PUT에서 JSON타입으로 객체 지원
    */
    @PostMapping("/day03/method6")
    // [BODY] : { "name" : "유재석", "age" : "40" }
    public boolean method6( @RequestBody TaskDto taskDto ){
        System.out.println("taskDto = " + taskDto);
        System.out.println("RestController1.method6");
        return true;
    } // func end

    @PutMapping("/day03/method7")
    public int method7( @RequestBody Map<String, String> map ){
        System.out.println("RestController1.method7");
        System.out.println("map = " + map);

        return 3;
    } // func end

} // class end
