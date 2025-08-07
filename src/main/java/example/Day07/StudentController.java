package example.Day07;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @ : 어노테이션이란? 추가적인 정보를 제공하는 메타데이터 역할
// @Controller : HTTP 요청과 응답을 연결(매핑), @Component가 포함되어있음
// @Component : 스프링 컨테이너(메모리)에 bean(객체)를 등록/생성, 싱글톤과 비슷한 역할 수행
// @ResponseBody : HTTP 응답 자료의 타입을 자동으로 변환
@RequestMapping("/student")
// @RequestMapping("/URL 주소") : 지정한 클래스 내 모든 메소드들의 공통 URL 지정
public class StudentController {

    // 1. 저장 기능
    @PostMapping("/save")
    // @PostMapping("/URL 주소") : HTTP 요청 중에 method가 POST인 요청 매핑 + URL 주소 정의
    public boolean save( @RequestBody StudentDto studentDto ){
        System.out.println("StudentController.save");
        System.out.println("studentDto = " + studentDto);

        return false;
    } // func end

    // 2. 전체조회 기능
    @GetMapping("/find")
    // @GetMapping("/URL 주소") : HTTP 요청 중에 method가 GET인 요청 매핑 + URL 주소 정의
    public List<StudentDto> find(){
        System.out.println("StudentController.find");

        return null;
    } // fund end

} // class end
