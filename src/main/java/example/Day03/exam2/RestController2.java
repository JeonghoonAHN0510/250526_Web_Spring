package example.Day03.exam2;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class RestController2 {

    @PostMapping("/board")
    public boolean boardWrite( @RequestBody BoardDto boardDto1 ){
        System.out.println("RestController2.boardWrite");
        System.out.println("boardDto1 = " + boardDto1);
        ArrayList<BoardDto> boardDtos = new ArrayList<>();
        BoardDto boardDto = new BoardDto();
        boardDto.setBcontent( boardDto1.getBcontent() ); boardDto.setBwriter(boardDto1.getBwriter() );

        return true;
    } // func end

    @GetMapping("/board")
    public ArrayList<BoardDto> boardPrint(){
        System.out.println("RestController2.boardPrint");
        ArrayList<BoardDto> boardDtos = new ArrayList<>();
        BoardDto boardDto1 = new BoardDto();
        boardDto1.setBno(1); boardDto1.setBcontent("안녕하세요"); boardDto1.setBwriter("유재석");
        BoardDto boardDto2 = new BoardDto();
        boardDto2.setBno(2); boardDto2.setBcontent("안녕하세요2"); boardDto2.setBwriter("강호동");
        boardDtos.add( boardDto1 ); boardDtos.add( boardDto2 );

        return boardDtos;
    } // func end

    @GetMapping("/board/detail")
    public BoardDto boardDetail( int bno ){
        System.out.println("RestController2.boardDetail");
        System.out.println("bno = " + bno);
        ArrayList<BoardDto> boardDtos = new ArrayList<>();
        BoardDto boardDto = new BoardDto();
        BoardDto boardDto3 = new BoardDto();
        boardDto3.setBno(1); boardDto3.setBcontent("안녕하세요"); boardDto3.setBwriter("유재석");
        boardDtos.add( boardDto3 );
        for ( BoardDto boardDto1 : boardDtos ){
            int bno1 = boardDto1.getBno();
            if ( bno == bno1 ){
                boardDto = boardDto1;
            } // if end
        } // for end
        return boardDto;
    } // func end

    @DeleteMapping("/board")
    public boolean boardDelete( int bno ){
        System.out.println("RestController2.boardDelete");
        System.out.println("bno = " + bno);
        ArrayList<BoardDto> boardDtos = new ArrayList<>();
        BoardDto boardDto = new BoardDto();
        boardDto.setBno(1); boardDto.setBcontent("안녕하세요"); boardDto.setBwriter("유재석");
        boardDtos.add( boardDto );
        for ( BoardDto boardDto1 : boardDtos ){
            int bno1 = boardDto1.getBno();
            if ( bno == bno1 ){
                boardDtos.remove( boardDto1 );
                return true;
            } // if end
        } // for end
        return false;
    } // func end

    @PutMapping("/board")
    public boolean boardUpdate( @RequestBody BoardDto boardDto ){
        System.out.println("RestController2.boardUpdate");
        System.out.println("boardDto = " + boardDto);
        ArrayList<BoardDto> boardDtos = new ArrayList<>();
        BoardDto boardDto1 = new BoardDto();
        boardDto1.setBno(1); boardDto1.setBcontent("안녕하세요"); boardDto1.setBwriter("유재석");
        boardDtos.add( boardDto1 );
        for ( BoardDto boardDto2 : boardDtos ){
            int bno2 = boardDto2.getBno();
            if ( boardDto.getBno() == bno2 ){
                boardDto2.setBcontent(boardDto.getBcontent());
                return true;
            } // if end
        } // for end
        return false;
    } // func end

} // class end
