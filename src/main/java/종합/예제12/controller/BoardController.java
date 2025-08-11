package 종합.예제12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import 종합.예제12.model.dto.BoardDto;
import 종합.예제12.service.BoardService;

import java.util.ArrayList;

@RestController
// 공통 URL 설정
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    // 1. 게시물 등록 메소드
    @PostMapping("") // -> localhost:8080/board
    public boolean boardWrite( @RequestBody BoardDto boardDto ){
        System.out.println("BoardController.boardWrite");
        System.out.println("boardDto = " + boardDto);
        // Service에게 객체 전달하고 결과받기
        boolean result = boardService.boardWrite( boardDto );
        // 결과 반환하기
        return result;
    } // func end

    // 2. 게시물 전체조회
    @GetMapping("")
    public ArrayList<BoardDto> boardRead(){
        System.out.println("BoardController.boardRead");
        return boardService.boardRead();
    } // func end

    // 3. 게시물 단일조회


    // 4. 게시물 삭제


    // 5. 게시물 수정



} // class end
