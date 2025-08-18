package Days.Day01.controller;

import Days.Day01.model.dao.BoardDao;
import Days.Day01.model.dto.BoardDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BoardController {
    // 싱글톤 만들기
    private BoardController(){}
    private static final BoardController instance = new BoardController();
    public static BoardController getInstance() {
        return instance;
    } // func end

    // boardDao 인스턴스 가져오기
    private BoardDao boardDao = BoardDao.getInstance();

    // 1. 등록 기능 구현
    // 메소드명 : boardWrite
    // 매개변수 : String bcontent, String bwriter
    // 반환값 : true(성공)/false(실패) -> boolean
    @PostMapping
    public boolean boardWrite( String bcontent, String bwriter ){
        // 1. 유효성 검사 -> 지금은 패스
        // 2. boardDto 객체 생성
        // bno을 안받기 때문에, bcontent와 bwriter만 받는 생성자 필요
        BoardDto boardDto = new BoardDto( bcontent, bwriter );
        // 3. 생성한 객체를 dao에게 전달 후, 결과를 받는다.
        boolean result = boardDao.boardWrite( boardDto );
        // 4. 결과를 view에게 전달한다.
        return result;
    } // func end

    // 2. 전체조회 기능 구현
    // 메소드명 : boardPrint
    // 매개변수 : X
    // 반환값 : ArrayList<BoardDto>
    @GetMapping
    public ArrayList<BoardDto> boardPrint(){
        // 1. 유효성 검사
        // 2. dao에게 전달 후, 결과 받기
        ArrayList<BoardDto> result = boardDao.boardPrint();
        // 3. 결과를 view에게 전달
        return result;
    } // func end

} // class end
