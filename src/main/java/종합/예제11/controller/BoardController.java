package 종합.예제11.controller;

import org.springframework.web.bind.annotation.*;
import 종합.예제11.model.dao.BoardDao;
import 종합.예제11.model.dto.BoardDto;

import java.util.ArrayList;

@RestController // @Component(싱글톤 역할 수행) + @Controller + @ResponseBody
public class BoardController {

    // (*) boardDao 싱글톤 가져오기
    private BoardDao boardDao = BoardDao.getInstance();

    // (1) 등록 기능 구현 : 등록은 보통 POST
    @PostMapping("/board")
    public boolean boardWrite( @RequestBody BoardDto boardDto ){
        // 3. 객체화 된 dto를 dao에게 전달후 결과를 받는다.
        boolean result = boardDao.boardWrite( boardDto );
        // 4. 결과를 view에게 리턴한다.
        return result;
    } // func end

    // (2) 전체조회 기능 구현 : 조회는 보통 GET
    @GetMapping("/board")
    public ArrayList<BoardDto> boardPrint( ){
        // - 유효성검사 ~ // - 매개변수 ~
        // 3. dao에게 전달후 결과를 받는다.
        ArrayList<BoardDto> result = boardDao.boardPrint( );
        // 4. 결과를 view에게 리턴한다.
        return result;
    } // func end

    // (3) 삭제 기능 구현 : 삭제는 보통 DELETE -> 쿼리스트링
    @DeleteMapping("/board")
    public boolean boardDelete( @RequestParam int bno ){
        // 1.유효성검사2.객체화
        // 3. dao에게 삭제할번호 전달후 결과를 받는다.
        boolean result = boardDao.boardDelete( bno );
        // 4. 결과를 view에게 리턴한다.
        return result;
    } // func end

    // (4) 수정 기능 구현 : 수정은 보통 PUT -> BODY
    @PutMapping("/board")
    public boolean boardUpdate( @RequestBody BoardDto boardDto ){
        // 3. dao에게 삭제할번호 전달후 결과를 받는다.
        boolean result = boardDao.boardUpdate( boardDto );
        // 4. 결과를 view에게 리턴한다.
        return result;
    } // func end

} // class end
