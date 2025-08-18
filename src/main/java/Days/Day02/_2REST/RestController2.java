package Days.Day02._2REST;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller // @Component가 내장되어있음
public class RestController2 {
    // 싱글톤 생략 -> @Component를 이용한 인스턴스 자동 생성(@Controller에 내장되어있음)

    // [ 반환타입이 있는 REST ]
    // @ResponseBody : HTTP는 int 타입을 모른다. -> 자바에서 사용하는 타입을 자동으로 HTTP이 이해하는 타입(JSON)으로 변환
    // Talend API : http://localhost:8080/정의한 URL

    // 1. int 반환하는 메소드
    @GetMapping("/day02/task1")      // @XXXMapping("URL주소")
    @ResponseBody                    // 반환값을 HTTP가 이해하는 타입으로 자동 변환
    public int method1(){
        System.out.println("RestController2.method1");  // println 자주 찍어야한다!!
        return 100;
    } // func end

    // 2. String 반환하는 메소드
    @GetMapping("/day02/task2")     // 주의할 점 : 서버 내 동일한 주소 불가능
    @ResponseBody                   // 반환값을 HTTP가 이해하는 타입으로 자동 변환
    public String method2(){
        System.out.println("RestController2.method2");
        return "스프링을 통한 메시지";
    } // func end

    // 3. Map 반환하는 메소드
    // 주의할 점 : 서버 내 동일한 주소가 불가능하지만, REST가 다르면 가능
    @PostMapping("/day02/task1")
    @ResponseBody
    public Map< String, Integer > method3(){
        System.out.println("RestController2.method3");
        // 샘플데이터
        Map<String, Integer> map = new HashMap<>();
        map.put("강호동", 100);
        map.put("유재석", 95 );
        return map;
    } // func end

    // 4. boolean 반환하는 메소드
    @PutMapping("/day02/task1")
    @ResponseBody
    public boolean method4(){
        System.out.println("RestController2.method4");
        return false;
    } // func end

    // 5. DTO 반환하는 메소드
    @DeleteMapping("/day02/task1")
    @ResponseBody
    public TaskDto method5(){
        System.out.println("RestController2.method5");
        // 샘플데이터
        TaskDto taskDto = new TaskDto();
        return taskDto;
    } // func end

    // 6. ArrayList 반환 메소드
    @GetMapping("/day02/task3")
    @ResponseBody
    public List<TaskDto> method6(){
        System.out.println("RestController2.method6");
        // 샘플데이터
        ArrayList<TaskDto> list = new ArrayList<>();
        list.add(new TaskDto());
        list.add(new TaskDto());
        return list;
    } // func end
} // class end
