package Days.Day04;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController     // @Controller(+ @Component) + @ResponseBody
// HTTP 요청과 응답처리
public class FetchController {
    // 1. HTTP 정의, METHOD : Get, URL : /day04/method1
    @GetMapping("/day04/method1")
    public void method1(){
        System.out.println("FetchController.method1");
    } // func end
    // 2. HTTP 정의, METHOD : Post, URL : /day04/method2
    @PostMapping("day04/method2")
    public void method2(){
        System.out.println("FetchController.method2");
    } // func end
    // 3. HTTP 정의, METHOD : Put, URL : /day04/method3
    @PutMapping("day04/method3")
    public void method3(){
        System.out.println("FetchController.method3");
    } // func end
    // 4. HTTP 정의, METHOD : Delete, URL : /day04/method4
    @DeleteMapping("day04/method4")
    public void method4(){
        System.out.println("FetchController.method4");
    } // func end

    // 5. 매개변수 : 쿼리스트링, 반환타입 : JSON
    @GetMapping("day04/method5")
    public boolean method5( @RequestParam String name, @RequestParam int age ){
        System.out.println("FetchController.method5");
        System.out.println("name = " + name + ", age = " + age);
        boolean result =  true;
        System.out.println("result = " + result);
        return result;
    } // func end
    // 6. 매개변수 : HTTP본문, 반환타입 : JSON
    @PostMapping("day04/method6")
    public int method6( @RequestBody Map<String, String> map ){
        System.out.println("FetchController.method6");
        System.out.println("map = " + map);
        return 20;
    } // func end
    // 7. 매개변수 : HTTP본문, 반환타입 : JSON
    @PutMapping("day04/method7")
    public TaskDto method7( @RequestBody TaskDto taskDto ){
        System.out.println("FetchController.method7");
        System.out.println("taskDto = " + taskDto);
        TaskDto result = new TaskDto("강호동", 40);
        return result;
    } // func end
    // 8. 매개변수 : 쿼리스트링, 반환타입 : JSON
    @DeleteMapping("day04/method8")
    public List<TaskDto> method8( @RequestParam String name, int age ){
        System.out.println("FetchController.method8");
        System.out.println("name = " + name + ", age = " + age);
        List<TaskDto> result = new ArrayList<>();
        result.add( new TaskDto("강호동", 40) );
        result.add( new TaskDto("유재석", 30) );
        return result;
    } // func end
} // class end
