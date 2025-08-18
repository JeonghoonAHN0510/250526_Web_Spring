package Days.Day02.exam1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {
    // 1. 글쓰기           POST       "/exam1/board"
    // 요청자료 : x , 응답자료 : true/false
    @PostMapping("/exam1/board")
    @ResponseBody
    public boolean method1(){
        System.out.println("BoardController.method1");
        return true;
    } // func end

    // 2. 전체 글 조회      GET        "/exam1/board"
    // 요청자료 : x , 응답자료 : 임의의 List 타입 ,  [ {bno:'',btitle:''} ,  {bno:'',btitle:''}  ] , 아래 참고
    @GetMapping("/exam1/board")
    @ResponseBody
    public List<BoardDto> method2(){
        System.out.println("BoardController.method2");
        // 임의데이터
        List<BoardDto> list = new ArrayList<>();
        list.add( new BoardDto() );
        list.add( new BoardDto() );
        return list;
    } // func end

    // 3. 개별 글 조회      GET        "/exam1/board/view"
    // 요청자료 : x , 응답자료 : 임의의 MAP  타입  ,  {bno:'',btitle:''} , 아래 참고
    @GetMapping("/exam1/board/view")
    @ResponseBody
    public Map<String, String> method3(){
        System.out.println("BoardController.method3");
        // 임의 자료
        Map<String, String> map = new HashMap<>();
        map.put("bno", "");
        map.put("btitle", "");
        return map;
    } // func end

    // 4. 개별 글 수정      PUT        "/exam1/board"
    // 요청자료 : x , 응답자료 : true 또는 false
    @PutMapping("/exam1/board")
    @ResponseBody
    public boolean method4(){
        System.out.println("BoardController.method4");
        return true;
    } // func end

    // 5. 개별 글 삭제      DELETE     "/exam1/board"
    // 요청자료 : x , 응답자료 : 임의의 삭제한 번호 , 3
    @DeleteMapping("/exam1/board")
    @ResponseBody
    public int method5(){
        System.out.println("BoardController.method5");
        return 3;
    } // func end

} // class end
